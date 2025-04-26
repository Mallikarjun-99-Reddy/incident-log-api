package com.humanchain.incidents.service;

import com.humanchain.incidents.model.Incident;
import com.humanchain.incidents.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    // Get all incidents
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    // Get an incident by ID
    public Optional<Incident> getIncidentById(Long id) {
        return incidentRepository.findById(id);
    }

    // Save an incident
    public Incident saveIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    // Check if an incident exists by ID
    public boolean existsById(Long id) {
        return incidentRepository.existsById(id);
    }

    // Delete an incident by ID
    public void deleteIncidentById(Long id) {
        incidentRepository.deleteById(id);
    }
}
