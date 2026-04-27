package edu.famu.cop3060.yard.dto;

import java.util.List;

public class OpportunityDTO {

    private String id;
    private String title;
    private String type;
    private String sponsor;
    private String deadline;
    private String description;
    private List<String> tags;
    private String url;

    public OpportunityDTO(String id, String title, String type, String sponsor,
                          String deadline, String description, List<String> tags, String url) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.sponsor = sponsor;
        this.deadline = deadline;
        this.description = description;
        this.tags = tags;
        this.url = url;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getType() { return type; }
    public String getSponsor() { return sponsor; }
    public String getDeadline() { return deadline; }
    public String getDescription() { return description; }
    public List<String> getTags() { return tags; }
    public String getUrl() { return url; }
}