package edu.famu.cop3060.yard.controller;

import edu.famu.cop3060.yard.dto.OpportunityDTO;
import edu.famu.cop3060.yard.service.OpportunitiesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OpportunitiesController.class)
class OpportunitiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OpportunitiesService service;

    @Test
    void listEndpointReturns200AndJsonArray() throws Exception {
        List<OpportunityDTO> opportunities = List.of(
                new OpportunityDTO("opp-001", "UNCF STEM Scholarship", "Scholarship",
                        "UNCF", "2025-04-15", "Scholarship for STEM students",
                        List.of("STEM", "paid"), "https://uncf.org"),
                new OpportunityDTO("opp-002", "Google HBCU Residency", "Fellowship",
                        "Google", "2025-05-01", "Tech fellowship program",
                        List.of("tech", "paid"), "https://google.com")
        );

        when(service.getOpportunities(null, null)).thenReturn(opportunities);

        mockMvc.perform(get("/api/opportunities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("UNCF STEM Scholarship"));
    }

    @Test
    void detailEndpointReturns200AndCorrectTitle() throws Exception {
        OpportunityDTO opportunity = new OpportunityDTO(
                "opp-001", "UNCF STEM Scholarship", "Scholarship",
                "UNCF", "2025-04-15", "Scholarship for STEM students",
                List.of("STEM", "paid"), "https://uncf.org"
        );

        when(service.getOpportunityById("opp-001")).thenReturn(Optional.of(opportunity));

        mockMvc.perform(get("/api/opportunities/opp-001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("UNCF STEM Scholarship"));
    }

    @Test
    void detailEndpointReturns404ForUnknownId() throws Exception {
        when(service.getOpportunityById("opp-999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/opportunities/opp-999"))
                .andExpect(status().isNotFound());
    }
}