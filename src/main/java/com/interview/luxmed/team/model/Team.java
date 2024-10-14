package com.interview.luxmed.team.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("team")
public class Team {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("department_id")
    private Integer departmentId;

    public Team() {}

    public Team(String name, Integer departmentId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name parameter cannot be null or empty.");
        }
        if (departmentId == null) {
            throw new IllegalArgumentException("departmentId parameter cannot be null or empty.");
        }
        this.name = name;
        this.departmentId = departmentId;
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Team team)) {
            return false;
        }
        return this.name.equals(team.getName())
                && this.departmentId.equals(team.getDepartmentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, departmentId);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", departmentId=" + departmentId +
                "'";
    }
}