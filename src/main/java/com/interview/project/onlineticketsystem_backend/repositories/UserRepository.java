package com.interview.project.onlineticketsystem_backend.repositories;

import com.interview.project.onlineticketsystem_backend.entities.User;
import org.springframework.stereotype.Repository;


/**
 * The repository defines operations on User entity.
 */
@Repository
public interface UserRepository extends BaseRepository<User> {

    /**
     * Find one by email.
     *
     * @param email the email
     * @return the user found
     */
    User findOneByEmail(String email);
}

