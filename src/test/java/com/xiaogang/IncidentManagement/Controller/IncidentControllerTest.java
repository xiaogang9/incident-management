package com.xiaogang.IncidentManagement.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaogang.IncidentManagement.Entity.Incident;
import com.xiaogang.IncidentManagement.Service.IncidentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = IncidentController.class)
@ComponentScan(basePackages = {"com.xiaogang.IncidentManagement.Controller"})
public class IncidentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncidentService incidentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateIncident() throws Exception {
        Incident incident = new Incident();
        incident.setTitle("New Incident");
        incident.setDescription("Description");

        when(incidentService.createIncident(any(Incident.class))).thenReturn(incident);

        mockMvc.perform(post("/api/incidents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incident)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Incident"));

    }

    @Test
    void testGetAllIncidents() throws Exception {
        Incident incident = new Incident();
        incident.setId(1L);
        incident.setTitle("Incident 1");

        Page<Incident> page = new PageImpl<>(Arrays.asList(incident));

        when(incidentService.getAllIncidents(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/incidents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("Incident 1"));
    }

    @Test
    void testUpdateIncident() throws Exception {
        Incident incident = new Incident();
        incident.setId(1L);
        incident.setTitle("Updated Title");

        when(incidentService.updateIncident(any(Long.class), any(Incident.class))).thenReturn(incident);

        mockMvc.perform(put("/api/incidents/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incident)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    void testDeleteIncident() throws Exception {
        mockMvc.perform(delete("/api/incidents/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Incident deleted successfully."));
    }
}
