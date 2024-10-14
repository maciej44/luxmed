package com.interview.luxmed.department.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("department")
public class Department {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("company_id")
    private Long companyId;

    public Department() {}

    public Department(String name, Long companyId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name parameter cannot be null or empty.");
        }
        this.name = name;
        this.companyId = Objects.requireNonNull(companyId, "companyId parameter cannot be null.");
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Department department)) {
            return false;
        }
        return name.equals(department.getName())
                && companyId.equals(department.getCompanyId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, companyId);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", companyId=" + companyId +
                "}";
    }
}