package com.interview.project.onlineticketsystem_backend.entities.requests;

import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The base search request.
 */
@Getter
@Setter
@ToString
public class BaseSearchRequest {

    /**
     * The offset.
     */
    @Min(0)
    private Integer offset;

    /**
     * The limit.
     */
    @Min(1)
    private Integer limit;

    /**
     * The sort by.
     */
    private String sortBy;

    /**
     * The sort order.
     */
    private String sortOrder;
}
