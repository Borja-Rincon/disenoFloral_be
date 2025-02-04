package com.disenofloral.backend.repository;

import com.disenofloral.backend.model.EventLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventLocationRepository extends JpaRepository<EventLocation, Long> {
}
