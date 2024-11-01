package com.xiaogang.IncidentManagement.Service;

import com.xiaogang.IncidentManagement.Entity.Incident;
import com.xiaogang.IncidentManagement.Exception.ResourceNotFoundException;
import com.xiaogang.IncidentManagement.Repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IncidentService {
    @Autowired
    private IncidentRepository repository;

    public Incident createIncident(Incident incident) {
        if (repository.existsByTitle(incident.getTitle())) {
            throw new IllegalArgumentException("Incident with this title already exist.");
        }
        return repository.save(incident);
    }

    @Cacheable("incidents")
    public Page<Incident> getAllIncidents(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Incident updateIncident(Long id, Incident incidentDetails) {
        Incident incident = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found for this id :: " + id));
        incident.setTitle(incidentDetails.getTitle());
        incident.setDescription(incidentDetails.getDescription());
        return repository.save(incident);
    }

    public void deleteIncident(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Incident not found for this id :: " + id);
        }
        repository.deleteById(id);
    }
}
