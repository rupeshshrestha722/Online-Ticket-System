package com.interview.project.onlineticketsystem_backend.services;

import com.interview.project.onlineticketsystem_backend.entities.IdentifiableEntity;
import com.interview.project.onlineticketsystem_backend.entities.requests.BaseSearchRequest;
import com.interview.project.onlineticketsystem_backend.entities.responses.SearchResponse;
import com.interview.project.onlineticketsystem_backend.repositories.BaseRepository;
import com.interview.project.onlineticketsystem_backend.utils.OffsetLimitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The base service provides CRUD operations.
 *
 * @param <T> the entity type
 * @param  <S> the search request
 */
@Transactional
@Service
public abstract class BaseService<T extends IdentifiableEntity, S extends BaseSearchRequest> {
    /**
     * The default value for search query offset.
     */
    protected static final int DEFAULT_SEARCH_OFFSET = 0;

    /**
     * The default value for search query limit.
     */
    public static final int DEFAULT_SEARCH_LIMIT = 15;

    /**
     * The default sort order.
     */
    protected static final String DEFAULT_SORT_ORDER = "ASC";

    /**
     * The valid sort orders.
     */
    protected static final List<String> VALID_SORT_ORDERS = Arrays.asList("asc", "desc", "ASC", "DESC");

    /**
     * The valid sort columns.
     */
    private final List<String> validSortColumns;

    /**
     * The default sort columns.
     */
    private final String defaultSortColumn;

    /**
     * The repository instance.
     */
    @Autowired
    protected BaseRepository<T> repository;

    /**
     * Create a new instance.
     *
     * @param defaultSortColumn the default sort column
     * @param validSortColumns  the valid sort columns
     */
    public BaseService(String defaultSortColumn, String... validSortColumns) {
        this.defaultSortColumn = defaultSortColumn;
        this.validSortColumns = Arrays.asList(validSortColumns);
    }

    /**
     * Search entities.
     *
     * @param criteria the search criteria
     * @return the results with pagination
     */
    @Transactional(readOnly = true)
    public SearchResponse<T> search(S criteria) {
        Pageable pageable = createPageRequest(criteria);

        // Copy the criteria to the search response
        SearchResponse<T> searchResponse = new SearchResponse<>();
        searchResponse.setFilter(criteria);

        // Search
        Page<T> page = searchWithPageable(criteria, pageable);

        searchResponse.setTotal(page.getTotalElements());
        searchResponse.setResults(page.getContent());

        return searchResponse;
    }

    /**
     * Search entities with pageable.
     *
     * @param criteria the search criteria
     * @param pageable the pageable
     * @return the paged results
     */
    protected abstract Page<T> searchWithPageable(S criteria, Pageable pageable);

    /**
     * Create Spring page request.
     *
     * @param criteria the criteria
     * @return the Spring page request
     */
    protected Pageable createPageRequest(S criteria) {
        // Set default offset
        if (criteria.getOffset() == null) {
            criteria.setOffset(DEFAULT_SEARCH_OFFSET);
        }

        // Set default limit
        if (criteria.getLimit() == null) {
            criteria.setLimit(DEFAULT_SEARCH_LIMIT);
        }

        // Set default sort order
        if (StringUtils.isEmpty(criteria.getSortOrder())) {
            criteria.setSortOrder(DEFAULT_SORT_ORDER);
        } else {
            // Validate sort orders
            validateStringInList(criteria.getSortOrder(), "sortOrder", VALID_SORT_ORDERS);

            criteria.setSortOrder(criteria.getSortOrder().toUpperCase());
        }

        // Set default sort column
        if (StringUtils.isEmpty(criteria.getSortBy())) {
            criteria.setSortBy(this.defaultSortColumn);
        } else {
            // Validate sort column
            validateStringInList(criteria.getSortBy(), "sortBy", this.validSortColumns);
        }

        // Create the paging request
        Sort sort = Sort.by("ASC".equals(criteria.getSortOrder()) ? Sort.Direction.ASC : Sort.Direction.DESC, criteria.getSortBy());
        Pageable pageable = new OffsetLimitRequest(criteria.getOffset(), criteria.getLimit(), sort);

        return pageable;
    }



    /**
     * Get All List
     * @return list
     */
    public List<T> getAll() {
        return repository.findAll();
    }

    /**
     * Insert Entity
     * @param t      the entity
     * @return
     */
    public T insert(T t) {
        t.setId(null);
        validateEntity(t);
        return repository.saveAndFlush(t);
    }

    /**
     * Update an existing entity.
     *
     * @param id     the entity id
     * @param entity the entity to update
     * @return the updated entity
     * @throws EntityNotFoundException  if the entity does not exist
     * @throws IllegalArgumentException if any validation error occurs
     */
    public T update(long id, T entity) {
        ensureExist(repository, id, "Entity");
        entity.setId(id);
        validateEntity(entity);
        return repository.saveAndFlush(entity);
    }

    /**
     * Get a entity by id.
     *
     * @param id the entity id
     * @return the entity
     * @throws EntityNotFoundException  if the entity does not exist
     * @throws IllegalArgumentException if the id is not positive
     */
    @Transactional(readOnly = true)

    public T getById(Long id) {
        return ensureExist(repository, id, "Entity");
    }

    public void deleteT(Long id) {
        ensureExist(repository, id, "Entity");
        repository.deleteById(id);
    }

    /**
     * Validate the entity. The derived classes should override this method to add
     * additional validations.
     *
     * @param entity the entity
     * @throws IllegalArgumentException if any validation error occurs
     */
    protected void validateEntity(T entity) {
    }

    /**
     * Ensure that the id exists in DB.
     *
     * @param id         the id to check
     * @param entityName the entity name
     * @return the entity
     * @throws IllegalArgumentException if the id is not positive
     * @throws EntityNotFoundException  if the id does not exist
     */
    public static <E extends IdentifiableEntity> E ensureExist(BaseRepository<E> repository, Long id, String entityName) {
        if (id == null) {
            throw new IllegalArgumentException(entityName + " id must not be null");
        }
        if (id <= 0) {
            throw new IllegalArgumentException(entityName + " id must be positive");
        }

        Optional<E> optionalEntity = repository.findById(id);

        if (!optionalEntity.isPresent()) {
            throw new EntityNotFoundException(String.format("%s does not exist with id %s", entityName, id));
        }


        return repository.getById(id);
    }


    /**
     * Validate that a property value is in the specified list of strings.
     *
     * @param propertyValue the property value
     * @param propertyName  the property name
     * @param list          the list
     * @throws IllegalArgumentException if the value is not in the specified list
     */
    protected void validateStringInList(Object propertyValue, String propertyName, List<String> list) {
        if (!list.contains(propertyValue)) {
            throw new IllegalArgumentException(String.format("%s must be in %s", propertyName, list));
        }
    }
}
