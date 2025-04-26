package com.humanchain.incidents.repository;

import com.humanchain.incidents.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    // No need to write any methods now! JpaRepository gives everything!
}
