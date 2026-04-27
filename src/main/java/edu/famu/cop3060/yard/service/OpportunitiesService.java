package edu.famu.cop3060.yard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.famu.cop3060.yard.dto.OpportunityDTO;
import edu.famu.cop3060.yard.store.InMemoryOpportunityStore;

@Service
public class OpportunitiesService {

    private final InMemoryOpportunityStore store;

    public OpportunitiesService(InMemoryOpportunityStore store) {
        this.store = store;
    }

    public List<OpportunityDTO> getOpportunities(String type, String q) {
        return store.findWithFilters(type, q);
    }

    public Optional<OpportunityDTO> getOpportunityById(String id) {
        return store.findById(id);
    }
}