package com.interview.project.onlineticketsystem_backend.entities.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * The Bus Schedule Search request.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class BusScheduleSearchRequest extends BaseSearchRequest{

    /**
     * Source
     */
    private String source;

    /**
     * Destination
     */
    private String destination;

    /**
     * Bus No
     */
    private String busNo;

    /**
     * Bus Model
     */
    private String busModel;

    /**
     * Departure Date
     */
    private String departureDate;

    /**
     * Arrival Date
     */
    private String arrivalDate;





}
