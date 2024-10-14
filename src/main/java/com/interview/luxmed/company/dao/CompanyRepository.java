package com.interview.luxmed.company.dao;

import com.interview.luxmed.company.model.Company;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    Optional<Company> findByName(String name);

    @Query("SELECT * FROM company WHERE LOWER(name) LIKE LOWER('%' || :name || '%')")
    Stream<Company> streamByName(String name);
}
