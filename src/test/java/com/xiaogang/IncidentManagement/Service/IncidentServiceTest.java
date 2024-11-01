package com.xiaogang.IncidentManagement.Service;

import com.xiaogang.IncidentManagement.Entity.Incident;
import com.xiaogang.IncidentManagement.Exception.ResourceNotFoundException;
import com.xiaogang.IncidentManagement.Repository.IncidentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IncidentServiceTest {
    @InjectMocks
    private IncidentService incidentService;
    
    @Mock
    private IncidentRepository incidentRepository;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateIncident_Success() {
        Incident incident = new Incident();
        incident.setTitle("Test Incident");
        incident.setDescription("Test Description");

        when(incidentRepository.existsByTitle("Test Incident")).thenReturn(false);
        when(incidentRepository.save(incident)).thenReturn(incident);

        Incident createIncident = incidentService.createIncident(incident);

        assertNotNull(createIncident);
        assertEquals("Test Incident", createIncident.getTitle());
    }

    @Test
    void testCreateIncident_DuplicateTitle() {
        Incident incident = new Incident();
        incident.setTitle("Existing Incident");

        when(incidentRepository.existsByTitle("Existing Incident")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, ()-> {
            incidentService.createIncident(incident);
        });
    }

    @Test
    void testGetAllIncidents() {
        Incident incident = new Incident();
        incident.setId(1L);
        incident.setTitle("Test Incident 1");

        List<Incident> incidents = Arrays.asList(incident);
        Page<Incident> page = new PageImpl<>(incidents);

        when(incidentRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Incident> result = incidentService.getAllIncidents(Pageable.unpaged());

        assertEquals(1, result.getTotalElements());
        verify(incidentRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testUpdateIncident_Success() {
        Incident exisitingIncident = new Incident();
        exisitingIncident.setId(1L);
        exisitingIncident.setTitle("Exisiting Title");

        Incident updateDetail = new Incident();
        updateDetail.setTitle("New Title");
        updateDetail.setDescription("Updated Description");

        when(incidentRepository.findById(1L)).thenReturn(Optional.of(exisitingIncident));
        when(incidentRepository.save(exisitingIncident)).thenReturn(exisitingIncident);

        Incident updatedIncident = incidentService.updateIncident(1L, updateDetail);
        assertEquals("New Title", updatedIncident.getTitle());
        assertEquals("Updated Description", updatedIncident.getDescription());
    }

    @Test
    void testUpdateIncident_NotFound() {
        Incident updatedDetail = new Incident();
        updatedDetail.setTitle("Exisiting Title");

        when(incidentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> {
            incidentService.updateIncident(1L, updatedDetail);
        });
    }

    @Test
    void testDeleteIncident_Success() {
        when(incidentRepository.existsById(1L)).thenReturn(true);

        incidentService.deleteIncident(1L);

        verify(incidentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteIncident_NotFound() {
        when(incidentRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, ()-> {
            incidentService.deleteIncident(1L);
        });
    }
}
