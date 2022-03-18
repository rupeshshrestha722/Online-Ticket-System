package com.interview.project.onlineticketsystem_backend.repositories;

import com.interview.project.onlineticketsystem_backend.entities.IdentifiableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 * The base interface for all repositories.
 *
 * @param <T>
 *          the entity type
 */
@NoRepositoryBean
@Repository
public interface BaseRepository<T extends IdentifiableEntity> extends JpaRepository<T, Long> {
}
