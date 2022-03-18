package com.interview.project.onlineticketsystem_backend.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


/**
 * The Test Entity
 */
@Entity(name = "bus_schedules")
@Getter
@Setter
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class BusSchedule extends IdentifiableEntity {

    /**
     * The Bus No
     */
    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "bus_no")
    private String busNo;

    /**
     * The  Bus Model
     */
    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "bus_model")
    private String busModel;

    /**
     * The No of Seats
     */
    @Column(name = "no_of_seats")
    private Long noOfSeats;

    /**
     * The Fare
     */
    @Column(name = "fare")
    private Long fare;

    /**
     * The Source
     */
    @NotBlank
    @Column(name = "source")
    private String source;

    /**
     * The Destination
     */
    @NotBlank
    @Column(name = "destination")
    private String destination;

    /**
     * The Departure Date
     */
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    /**
     * The Arrival Date
     */

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDate;

    /** Booking List
     */
    @OneToMany(mappedBy = "bus", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Booking> bookingList;


}
