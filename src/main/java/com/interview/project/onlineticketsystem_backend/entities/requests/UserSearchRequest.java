package com.interview.project.onlineticketsystem_backend.entities.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The User Search request.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UserSearchRequest extends BaseSearchRequest{

    /**
     * First Name
     */
    private String firstName;

    /**
     * Last Name
     */
    private String lastName;

    /**
     * Email
     */
    private String email;

    /**
     * Mobile
     */
    private String mobileNo;
}
