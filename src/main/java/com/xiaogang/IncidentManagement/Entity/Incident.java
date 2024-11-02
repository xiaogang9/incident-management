package com.xiaogang.IncidentManagement.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    private String description;

    private IncidentLevel level;

    private IncidentStatus status;

    private Long submittedBy;

    private Long assignedTo;

    private Long projectId;

    private Long DomainId;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    enum IncidentLevel { // P0 is the highest priority of incident
        P0, P1, P2, P3, P4
    }

    enum IncidentStatus { // here just demo some basic status of the incident
        CREATED,
        ASSIGNED,
        RESOLVED,
        BACKLOGGED,
        OBSOLETED,
    }
}






