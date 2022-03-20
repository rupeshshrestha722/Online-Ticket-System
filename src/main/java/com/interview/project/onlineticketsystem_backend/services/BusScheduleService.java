package com.interview.project.onlineticketsystem_backend.services;

import com.interview.project.onlineticketsystem_backend.entities.BusSchedule;
import com.interview.project.onlineticketsystem_backend.entities.requests.BusScheduleSearchRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * The service provides operations on Booking entity
 *
 */
@Service
public class BusScheduleService extends BaseService<BusSchedule, BusScheduleSearchRequest>{

    /**
     * Create a new instance.
     */
    public BusScheduleService() {
        super("departureDate", new String[] { "source", "destination","busNo","busModel","fare","noOfSeats","departureDate","arrivalDate"});
    }

    @Override
    protected Page<BusSchedule> searchWithPageable(BusScheduleSearchRequest criteria, Pageable pageable) {

        BusSchedule probe = new BusSchedule();
        BeanUtils.copyProperties(criteria, probe);

        Example<BusSchedule> example = Example.of(probe,
                ExampleMatcher.matching().withMatcher("source", ExampleMatcher.GenericPropertyMatchers.contains())
                        .withMatcher("destination",ExampleMatcher.GenericPropertyMatchers.contains())
                        .withMatcher("departureDate",ExampleMatcher.GenericPropertyMatchers.contains())
                        .withMatcher("busNo",ExampleMatcher.GenericPropertyMatchers.contains())
                        .withMatcher("busModel",ExampleMatcher.GenericPropertyMatchers.contains())
        );

        return repository.findAll(example,pageable);
    }

    @Override
    protected void validateEntity(BusSchedule t) {
        // New entity
        if (t.getId() == null) {
            t.setBusNo(t.getBusNo());
            t.setBusModel(t.getBusModel());
            t.setNoOfSeats(t.getNoOfSeats());
            t.setFare(t.getFare());
            t.setSource(t.getSource());
            t.setDestination(t.getDestination());
            t.setDepartureDate(t.getDepartureDate());
            t.setArrivalDate(t.getArrivalDate());
        }else {
            BusSchedule dbBus = ensureExist(repository, t.getId(), "BusSchedule");
            t.setBusNo(t.getBusNo());
            t.setBusModel(t.getBusModel());
            t.setNoOfSeats(t.getNoOfSeats());
            t.setFare(t.getFare());
            t.setSource(t.getSource());
            t.setDestination(t.getDestination());
            t.setDepartureDate(t.getDepartureDate());
            t.setArrivalDate(t.getArrivalDate());
        }



    }



}
