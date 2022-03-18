package com.interview.project.onlineticketsystem_backend.controller;

import com.interview.project.onlineticketsystem_backend.entities.BusSchedule;
import com.interview.project.onlineticketsystem_backend.entities.requests.BusScheduleSearchRequest;
import com.interview.project.onlineticketsystem_backend.services.BusScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The controller defines /busSchedule endpoints.
 */
@RestController
@RequestMapping("/schedules")
public class BusScheduleController extends BaseController<BusSchedule, BusScheduleSearchRequest> {

    private final BusScheduleService service;

    @Autowired
    public BusScheduleController(BusScheduleService service) {
        this.service = service;
    }


}

