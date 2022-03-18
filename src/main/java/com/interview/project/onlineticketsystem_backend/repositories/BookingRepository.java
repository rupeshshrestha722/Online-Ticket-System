package com.interview.project.onlineticketsystem_backend.repositories;

import com.interview.project.onlineticketsystem_backend.entities.Booking;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The repository defines operations on Booking entity.
 */
@Repository
public interface BookingRepository extends BaseRepository<Booking> {
   List<Booking> findAllByUserId(Long userId);
}
