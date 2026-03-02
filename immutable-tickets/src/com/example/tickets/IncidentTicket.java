package com.example.tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * INTENTION: A ticket should be an immutable record-like object.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - mutable fields
 * - multiple constructors
 * - public setters
 * - tags list can be modified from outside
 * - validation is scattered elsewhere
 *
 * TODO (student): refactor to immutable + Builder.
 */
public final class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;
    private final String description;
    private final String priority;
    private final List<String> tags; 
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;
    private final String source;     

    private IncidentTicket(Builder builder){
        this.id = builder.id;
        this.reporterEmail = builder.reporterEmail;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        this.tags = Collections.unmodifiableList(new ArrayList<>(builder.tags));
        this.assigneeEmail = builder.assigneeEmail;
        this.customerVisible = builder.customerVisible;
        this.slaMinutes = builder.slaMinutes;
        this.source = builder.source;
    }

    public static Builder builder() {
        return new Builder();
    }



    public Builder toBuilder() {
        return new Builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .description(description)
                .priority(priority)
                .tags(new ArrayList<>(tags))
                .assigneeEmail(assigneeEmail)
                .customerVisible(customerVisible)
                .slaMinutes(slaMinutes)
                .source(source);
    }

    // Getters
    public String getId() { return id; }
    public String getReporterEmail() { return reporterEmail; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public List<String> getTags() { return tags; } // BROKEN: leaks internal list
    public String getAssigneeEmail() { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes() { return slaMinutes; }
    public String getSource() { return source; }

    // Setters (BROKEN: should not exist after refactor)
    // public void setId(String id) { this.id = id; }
    // public void setReporterEmail(String reporterEmail) { this.reporterEmail = reporterEmail; }
    // public void setTitle(String title) { this.title = title; }
    // public void setDescription(String description) { this.description = description; }
    // public void setPriority(String priority) { this.priority = priority; }
    // public void setTags(List<String> tags) { this.tags = tags; } // BROKEN: retains external reference
    // public void setAssigneeEmail(String assigneeEmail) { this.assigneeEmail = assigneeEmail; }
    // public void setCustomerVisible(boolean customerVisible) { this.customerVisible = customerVisible; }
    // public void setSlaMinutes(Integer slaMinutes) { this.slaMinutes = slaMinutes; }
    // public void setSource(String source) { this.source = source; }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }

    public static class Builder {
        private String id;
        private String reporterEmail;
        private String title;
        private String description;
        private String priority = "MEDIUM";
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail;
        private boolean customerVisible = false;
        private Integer slaMinutes;
        private String source = "CLI"; 

        public Builder id(String id) { this.id = id; return this; }
        public Builder reporterEmail(String email) { this.reporterEmail = email; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder description(String desc) { this.description = desc; return this; }
        public Builder priority(String p) { this.priority = p; return this; }
        public Builder tags(List<String> tags) { this.tags = tags; return this; }
        public Builder assigneeEmail(String email) { this.assigneeEmail = email; return this; }
        public Builder customerVisible(boolean visible) { this.customerVisible = visible; return this; }
        public Builder slaMinutes(Integer sla) { this.slaMinutes = sla; return this; }
        public Builder source(String source) { this.source = source; return this; }

        public IncidentTicket build() {
            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireNonBlank(title, "title");
            Validation.requireMaxLen(title, 80, "title");
            Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes");
            if (assigneeEmail != null) Validation.requireEmail(assigneeEmail, "assigneeEmail");

            return new IncidentTicket(this);
        }
    }
}
