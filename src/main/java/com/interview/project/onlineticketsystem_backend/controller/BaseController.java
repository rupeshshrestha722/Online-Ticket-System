package com.interview.project.onlineticketsystem_backend.controller;

import javax.validation.Valid;

import com.interview.project.onlineticketsystem_backend.entities.IdentifiableEntity;
import com.interview.project.onlineticketsystem_backend.entities.requests.BaseSearchRequest;
import com.interview.project.onlineticketsystem_backend.entities.responses.SearchResponse;
import com.interview.project.onlineticketsystem_backend.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

/**
 * The base controller provides CRUD endpoints.
 *
 * @param <T>
 *          the entity type
 */
public class BaseController<T extends IdentifiableEntity, S extends BaseSearchRequest> {

    /**
     * The service instance with CRUD operations.
     */
    @Autowired
    protected BaseService<T, S> service;

    /**
     * Create a new entity.
     *
     * @param entity
     *          the entity to create
     * @return the created entity
     */
    @PostMapping
    public T create(@Valid @RequestBody T entity) {
        return service.insert(entity);
    }


    /**
     * Search entities.
     *
     * @param criteria
     *          the search criteria
     * @return the search result
     */
    @GetMapping
    public SearchResponse<T> search(@Valid @ModelAttribute S criteria) {
        return service.search(criteria);
    }

    /**
     * Get an existing entity by id.
     *
     * @param id
     *          the entity id to get
     * @return the entity
     */
    @GetMapping("/{id}")
    public T get(@PathVariable("id") long id) {
        return service.getById(id);
    }

    /**
     * Update an existing entity.
     *
     * @param id
     *          the entity id to update
     * @param entity
     *          the entity to update
     * @return the updated entity
     */
    @PutMapping("/{id}")
    public T update(@PathVariable("id") long id, @Valid @RequestBody T entity) {
        return service.update(id, entity);
    }

    /**
     * Delete an existing entity.
     *
     * @param id
     *          the entity id to delete
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        service.deleteT(id);
    }
}
