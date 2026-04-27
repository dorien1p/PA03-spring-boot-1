package edu.famu.cop3060.yard.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import edu.famu.cop3060.yard.dto.OpportunityDTO;

@Component
public class InMemoryOpportunityStore {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryOpportunityStore.class);

    private final Map<String, OpportunityDTO> opportunityMap = new HashMap<>();
    private final List<OpportunityDTO> opportunityList = new ArrayList<>();

    public InMemoryOpportunityStore() {

        addOpportunity(new OpportunityDTO("opp-001", "UNCF STEM Scholarship", "Scholarship",
                "UNCF", "2025-04-15", "Scholarship for STEM students",
                List.of("STEM", "paid", "undergrad"), "https://uncf.org"));

        addOpportunity(new OpportunityDTO("opp-002", "Google HBCU Residency", "Fellowship",
                "Google", "2025-05-01", "Tech fellowship program",
                List.of("tech", "paid", "summer"), "https://google.com"));

        addOpportunity(new OpportunityDTO("opp-003", "NSBE Chapter", "Organization",
                "NSBE", "N/A", "Engineering networking org",
                List.of("engineering", "networking"), "https://nsbe.org"));

        addOpportunity(new OpportunityDTO("opp-004", "Homecoming Step Show", "Event",
                "Student Gov", "2025-10-10", "Campus event",
                List.of("culture", "campus"), "https://campus.edu"));

        addOpportunity(new OpportunityDTO("opp-005", "Goldman Sachs Internship", "Internship",
                "Goldman Sachs", "2025-06-01", "Finance internship",
                List.of("finance", "paid"), "https://goldmansachs.com"));

        addOpportunity(new OpportunityDTO("opp-006", "Microsoft Explore", "Internship",
                "Microsoft", "2025-05-20", "Tech internship",
                List.of("tech", "paid"), "https://microsoft.com"));

        addOpportunity(new OpportunityDTO("opp-007", "Leadership Summit", "Event",
                "Campus Org", "2025-09-01", "Leadership training",
                List.of("leadership", "development"), "https://campus.edu"));

        addOpportunity(new OpportunityDTO("opp-008", "Black Student Union", "Organization",
                "BSU", "N/A", "Cultural organization",
                List.of("culture", "community"), "https://campus.edu"));

        logger.info("Seeded {} opportunities into the in-memory store.", opportunityList.size());
    }

    private void addOpportunity(OpportunityDTO opportunity) {
        opportunityMap.put(opportunity.getId(), opportunity);
        opportunityList.add(opportunity);
    }

    // Return all opportunities (unmodifiable)
    public List<OpportunityDTO> findAll() {
        return Collections.unmodifiableList(opportunityList);
    }

    // Find by ID
    public Optional<OpportunityDTO> findById(String id) {
        return Optional.ofNullable(opportunityMap.get(id));
    }

    // Filter method (IMPORTANT FOR ASSIGNMENT)
    public List<OpportunityDTO> findWithFilters(String type, String q) {
        return opportunityList.stream()
                .filter(o -> {
                    boolean matchesType = (type == null || type.isBlank()) ||
                            o.getType().equalsIgnoreCase(type);

                    boolean matchesQuery = (q == null || q.isBlank()) ||
                            o.getTitle().toLowerCase().contains(q.toLowerCase()) ||
                            o.getTags().stream().anyMatch(tag ->
                                    tag.toLowerCase().contains(q.toLowerCase()));

                    return matchesType && matchesQuery;
                })
                .collect(Collectors.toList());
    }
}