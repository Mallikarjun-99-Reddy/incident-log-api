package com.humanchain.incidents.model;

import jakarta.persistence.*;


import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Severity cannot be null")
    private String severity;

    @Column(name = "reported_at", updatable = false)
    private LocalDateTime reportedAt;


    // Constructor
    public Incident(String title, String description, String severity) {
        this.title = title;
        this.description = description;
        this.severity = severity;
    }

    public Incident() {

    }

    // Getter and setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
    }

    // Automatically set the reportedAt timestamp before saving the entity
    @PrePersist
    public void onPrePersist() {
        this.reportedAt = LocalDateTime.now();
    }
}
