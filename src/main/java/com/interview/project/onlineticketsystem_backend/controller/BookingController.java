package com.interview.project.onlineticketsystem_backend.controller;

import com.interview.project.onlineticketsystem_backend.entities.Booking;
import com.interview.project.onlineticketsystem_backend.entities.requests.BookingSearchRequest;
import com.interview.project.onlineticketsystem_backend.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The controller defines /booking endpoints.
 */
@RestController
@RequestMapping("/booking")
public class BookingController extends BaseController<Booking, BookingSearchRequest> {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Make Payment
     *
     * @param id
     *          the entity id to update
     * @param entity
     *          the entity to update
     */
    @PutMapping("/payment/{id}")
    public void makePayment(@PathVariable("id") long id, @Valid @RequestBody Booking entity) {
        bookingService.makePayment(entity, id);
    }

    /**
     * All Booking List By User
     *
     * @return the booking list
     */
    @GetMapping("/self")
    public List<Booking> getAllBooking() {
        return bookingService.bookingListByUser();
    }


}

