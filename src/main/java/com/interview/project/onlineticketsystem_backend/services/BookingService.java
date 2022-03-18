package com.interview.project.onlineticketsystem_backend.services;

import com.interview.project.onlineticketsystem_backend.entities.Booking;
import com.interview.project.onlineticketsystem_backend.entities.BusSchedule;
import com.interview.project.onlineticketsystem_backend.entities.requests.BookingSearchRequest;
import com.interview.project.onlineticketsystem_backend.repositories.BookingRepository;
import com.interview.project.onlineticketsystem_backend.repositories.BusScheduleRepository;
import com.interview.project.onlineticketsystem_backend.securities.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The service provides operations on Booking entity
 *
 */
@Service
public class BookingService extends BaseService<Booking, BookingSearchRequest>{

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BusScheduleRepository busScheduleRepository;

    /**
     * Create a new instance.
     */
    public BookingService() {
        super("passengerName", new String[] { "id", "passengerName", "email", "mobileNo"});
    }

    @Override
    protected Page<Booking> searchWithPageable(BookingSearchRequest criteria, Pageable pageable) {
        Booking probe = new Booking();
        BeanUtils.copyProperties(criteria, probe);

        Example<Booking> example = Example.of(probe,
                ExampleMatcher.matching().withMatcher("passengerName", ExampleMatcher.GenericPropertyMatchers.contains())
        );

        return repository.findAll(example,pageable);
    }

    @Override
    protected void validateEntity(Booking booking) {
        // New entity
        if (booking.getId() == null) {
            booking.setPassengerName(booking.getPassengerName());
            booking.setEmail(booking.getEmail());
            booking.setMobileNo(booking.getMobileNo());
            booking.setRemarks(booking.getRemarks());
            booking.setBus(booking.getBus());
            booking.setUser(booking.getUser());
            booking.setFare(booking.getFare());
            booking.setTotalFare(booking.getTotalFare());
            booking.setNoOfSeats(booking.getNoOfSeats());

            BusSchedule bus = busScheduleRepository.getById(booking.getBus().getId());
            bus.setNoOfSeats(bus.getNoOfSeats() - booking.getNoOfSeats());
            busScheduleRepository.saveAndFlush(bus);
        }else {
            Booking dbBooking = ensureExist(repository, booking.getId(), "Booking");
            booking.setPassengerName(dbBooking.getPassengerName());
            booking.setEmail(dbBooking.getEmail());
            booking.setMobileNo(dbBooking.getMobileNo());
            booking.setRemarks(dbBooking.getRemarks());
            booking.setBus(dbBooking.getBus());
            booking.setUser(dbBooking.getUser());
            booking.setFare(dbBooking.getFare());
            booking.setTotalFare(dbBooking.getTotalFare());
            booking.setNoOfSeats(dbBooking.getNoOfSeats());
        }
    }

    /**
     * Make Payment.
     *
     * @param request the Booking Payment
     */
    public void makePayment(Booking request, Long id) {

        Booking dbBooking = ensureExist(repository, id, "Booking");
        dbBooking.setStatus(request.getStatus());
        repository.saveAndFlush(dbBooking);
    }

    /**
     * All Booking List By userId
     */
    public List<Booking> bookingListByUser() {
        return bookingRepository.findAllByUserId( SecurityUtil.getCurrentUser().getId());
    }


}
