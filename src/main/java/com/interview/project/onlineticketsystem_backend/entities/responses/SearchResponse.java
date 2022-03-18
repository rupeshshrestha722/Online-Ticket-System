package com.interview.project.onlineticketsystem_backend.entities.responses;

import java.util.List;

import com.interview.project.onlineticketsystem_backend.entities.IdentifiableEntity;
import com.interview.project.onlineticketsystem_backend.entities.requests.BaseSearchRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The search response.
 *
 * @param <T>
 *          the entity type
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SearchResponse<T extends IdentifiableEntity> {

    /**
     * The total results found.
     */
    private long total;

    /**
     * The results.
     */
    private List<T> results;

    /**
     * The search request.
     */
    private BaseSearchRequest filter;
}
