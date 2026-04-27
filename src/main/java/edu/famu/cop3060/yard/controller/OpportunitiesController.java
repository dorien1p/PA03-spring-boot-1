package edu.famu.cop3060.yard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.famu.cop3060.yard.dto.OpportunityDTO;
import edu.famu.cop3060.yard.service.OpportunitiesService;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunitiesController {

    private static final Logger logger = LoggerFactory.getLogger(OpportunitiesController.class);

    private final OpportunitiesService service;

    public OpportunitiesController(OpportunitiesService service) {
        this.service = service;
    }

    @GetMapping
    public List<OpportunityDTO> getAll(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String q) {

        logger.info("GET /api/opportunities — type={}, q={}",
                type == null ? "<empty>" : type,
                q == null ? "<empty>" : q);

        return service.getOpportunities(type, q);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpportunityDTO> getById(@PathVariable String id) {

        logger.info("GET /api/opportunities/{}", id);

        return service.getOpportunityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}