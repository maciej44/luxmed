package com.interview.luxmed.manager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("manager")
public class Manager {

    @Id
    private Long id;

    @Column("contact_information")
    private String contactInformation;

    @Column("project_id")
    private Long projectId;

    public Manager() {}

    public Manager(String contactInformation, Long projectId) {
        if (contactInformation == null || contactInformation.isBlank()) {
            throw new IllegalArgumentException("contactInformation parameter cannot be null or empty.");
        }
        this.contactInformation = contactInformation;
        this.projectId = Objects.requireNonNull(projectId, "projectId parameter cannot be null");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Manager manager)) {
            return false;
        }
        return contactInformation.equals(manager.getContactInformation());
    }

    @Override
    public int hashCode() {
        return contactInformation.hashCode();
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", contactInformation='" + contactInformation + "'" +
                ", projectId='" + projectId + "'" +
                "}";
    }
}
