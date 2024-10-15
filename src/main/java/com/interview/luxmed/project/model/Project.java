package com.interview.luxmed.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("project")
public class Project {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("team_id")
    private Integer teamId;

    public Project() {}

    public Project(String name, Integer teamId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name parameter cannot be null or empty.");
        }
        this.name = name;
        this.teamId = teamId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Project project)) {
            return false;
        }
        return teamId.equals(project.getTeamId());
    }

    @Override
    public int hashCode() {
        return teamId.hashCode();
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", teamId=" + teamId +
                "'";
    }
}