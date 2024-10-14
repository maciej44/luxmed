package com.interview.luxmed.company.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("company")
public class Company {

    @Id
    private Long id;

    @Column("name")
    private String name;

    public Company() {}
    public Company(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name parameter cannot be null or empty.");
        }
        this.name = name;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Company company)) {
            return false;
        }
        return name.equals(company.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + "'" +
                "}";
    }
}
