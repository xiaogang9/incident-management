package com.xiaogang.IncidentManagement.Service;

import com.xiaogang.IncidentManagement.Entity.Incident;
import com.xiaogang.IncidentManagement.Exception.ResourceNotFoundException;
import com.xiaogang.IncidentManagement.Repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class IncidentService {
    @Autowired
    private IncidentRepository repository;

    /**
     * Create incident in the H2 DB
     * @param incident passed from controller layer
     * @return incident entity
     */
    public Incident createIncident(Incident incident) {
        if (repository.existsByTitle(incident.getTitle())) {
            throw new IllegalArgumentException("Incident with this title already exist.");
        }
        incident.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        incident.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return repository.save(incident);
    }

    /**
     * Return a list of incidents
     * @param pageable pagination controlled by each page displays 20 incidents
     * @return pageable incidents
     */
    @Cacheable("incidents")
    public Page<Incident> getAllIncidents(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * update incident in the service layer
     * @param id incident id
     * @param incidentDetail incident entity
     * @return incident entity
     */
    public Incident updateIncident(Long id, Incident incidentDetail) {
        Incident incident = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found for this id :: " + id));
        incident.setTitle(incidentDetail.getTitle());
        incident.setDescription(incidentDetail.getDescription());
        incident.setLevel(incidentDetail.getLevel());
        incident.setStatus(incidentDetail.getStatus());
        incident.setAssignedTo(incidentDetail.getAssignedTo());
        incident.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return repository.save(incident);
    }

    /**
     * Delete incident in the service layer
     * @param id incident id
     */
    public void deleteIncident(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Incident not found for this id :: " + id);
        }
        repository.deleteById(id);
    }
}
