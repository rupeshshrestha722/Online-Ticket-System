package com.interview.project.onlineticketsystem_backend.entities.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * The Booking Search request.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class BookingSearchRequest extends BaseSearchRequest{

    /**
     * Passenger Name
     */
    private String passengerName;

    /**
     * Email
     */
    private String email;

    /**
     * Mobile
     */
    private String mobileNo;
}
