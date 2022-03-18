package com.interview.project.onlineticketsystem_backend.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.interview.project.onlineticketsystem_backend.entities.requests.RoleDeserializer;

/**
 * The user roles
 */
@JsonDeserialize(using = RoleDeserializer.class)
public enum Role {

    /**
     * The admin
     */
    ADMIN,

    /**
     * The user or customer
     */
    USER
}
