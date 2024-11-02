package com.xiaogang.IncidentManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import com.xiaogang.IncidentManagement.Service.IncidentService;
import com.xiaogang.IncidentManagement.Entity.Incident;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from localhost:3000
public class IncidentController {
    @Autowired
    private IncidentService service;

    /**
     * create incident in controller layer
     * @param incident incident entity
     * @return incident object
     */
    @PostMapping
    public Incident createIncident(@Valid @RequestBody Incident incident) {
        return service.createIncident(incident);
    }

    /**
     * return a list of incident with pagination
     * @param page page number
     * @param offLimit the number of incidents to be returned from domain layer
     * @return list of incidents
     */
    @GetMapping
    public Page<Incident> getAllIncidents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int offLimit) {
        return service.getAllIncidents(PageRequest.of(page, offLimit));
    }

    /**
     * update incident
     * @param id incidentId
     * @param incidentDetail incident entity retrieved from API
     * @return incident object
     */
    @PutMapping("/{id}")
    public Incident updateIncident(@PathVariable Long id, @Valid @RequestBody Incident incidentDetail) {
        return service.updateIncident(id, incidentDetail);
    }

    /**
     * Delete incident by Id
     * @param id incident id to be deleted
     * @return result msg / code
     */
    @DeleteMapping("/{id}")
    public String deleteIncident(@PathVariable Long id) {
        service.deleteIncident(id);
        // should be replaced with user msg code to adopt i18n
        return "Incident deleted successfully.";
    }
}
