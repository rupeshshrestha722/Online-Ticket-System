package com.interview.project.onlineticketsystem_backend.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * The Booking entity
 */
@Entity
@Table(name = "bookings")
@Getter
@Setter
public class Booking extends IdentifiableEntity {

    /**
     * Passenger Name
     */
    @Size(min = 1, max = 255)
    @Column(name = "passenger_name")
    private String passengerName;

    /**
     * Passenger Email
     */
    @Email
    @Size(min = 1, max = 255)
    @Column(name = "passenger_email")
    private String email;

    /**
     * Passenger Mobile
     */
    @Size(min = 1, max = 255)
    @Column(name = "mobile_no")
    private String mobileNo;

    /**
     * Remarks
     */
    @Column(name = "remarks")
    private String remarks;

    /**
     * Fare
     */
    @Column(name = "fare")
    private Long fare;

    /**
     * Total Fare
     */
    @Column(name="total_fare")
    private Long totalFare;

    /**
     * No of Seats
     */
    @Column(name = "no_of_seats")
    private Long noOfSeats;

    /**
     * The Bus
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bus_id", nullable = false)
    @JsonBackReference("bus")
    private BusSchedule bus;

    /**
     * The user
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user")
    private User user;

    /**
     * Payment Method
     */
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;


}
