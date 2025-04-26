package com.humanchain.incidents.controller;

import com.humanchain.incidents.model.Incident;
import com.humanchain.incidents.service.IncidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // ✅ added

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    // Create a logger instance using SLF4J
    private static final Logger logger = LoggerFactory.getLogger(IncidentController.class);

    // Get all incidents
    @GetMapping
    public List<Incident> getAllIncidents() {
        logger.info("Fetching all incidents");
        List<Incident> incidents = incidentService.getAllIncidents();
        if (incidents.isEmpty()) {
            logger.warn("No incidents found in the database.");
        } else {
            logger.info("{} incidents found.", incidents.size());
        }
        return incidents;
    }

    // Get a specific incident by ID
    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Long id) {
        logger.info("Fetching incident with ID {}", id);
        Optional<Incident> incident = incidentService.getIncidentById(id);
        if (incident.isPresent()) {
            logger.info("Incident with ID {} found", id);
            return ResponseEntity.ok(incident.get());
        } else {
            logger.error("Incident with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new incident
    @PostMapping
    public ResponseEntity<Incident> createIncident(@Valid @RequestBody Incident incident) {
        logger.info("Creating new incident with title: {}", incident.getTitle());
        Incident savedIncident = incidentService.saveIncident(incident);
        logger.info("Incident with ID {} created successfully", savedIncident.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIncident);
    }

    // Update an incident completely (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Long id, @Valid @RequestBody Incident updatedIncident) {
        logger.info("Updating incident with ID {}", id);
        Optional<Incident> existingIncidentOpt = incidentService.getIncidentById(id);
        if (existingIncidentOpt.isEmpty()) {
            logger.error("Incident with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
        Incident existingIncident = existingIncidentOpt.get();
        existingIncident.setTitle(updatedIncident.getTitle());
        existingIncident.setDescription(updatedIncident.getDescription());
        existingIncident.setSeverity(updatedIncident.getSeverity());
        existingIncident.setReportedAt(updatedIncident.getReportedAt());
        incidentService.saveIncident(existingIncident);
        logger.info("Incident with ID {} updated successfully", id);
        return ResponseEntity.ok(existingIncident);
    }

    // Partial update (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Incident> partialUpdateIncident(@PathVariable Long id, @RequestBody Incident updatedFields) {
        logger.info("Partially updating incident with ID {}", id);
        Optional<Incident> existingIncidentOpt = incidentService.getIncidentById(id);
        if (existingIncidentOpt.isEmpty()) {
            logger.error("Incident with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
        Incident existingIncident = existingIncidentOpt.get();
        if (updatedFields.getTitle() != null) existingIncident.setTitle(updatedFields.getTitle());
        if (updatedFields.getDescription() != null) existingIncident.setDescription(updatedFields.getDescription());
        if (updatedFields.getSeverity() != null) existingIncident.setSeverity(updatedFields.getSeverity());
        if (updatedFields.getReportedAt() != null) existingIncident.setReportedAt(updatedFields.getReportedAt());
        incidentService.saveIncident(existingIncident);
        logger.info("Incident with ID {} partially updated successfully", id);
        return ResponseEntity.ok(existingIncident);
    }

    // Delete an incident by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIncident(@PathVariable Long id) {
        logger.info("Deleting incident with ID {}", id);
        Optional<Incident> existingIncident = incidentService.getIncidentById(id);
        if (existingIncident.isEmpty()) {
            logger.error("Incident with ID {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incident not found");
        }
        incidentService.deleteIncidentById(id);
        logger.info("Incident with ID {} successfully deleted", id);
        return ResponseEntity.ok("Incident with ID " + id + " was successfully deleted");
    }
}
