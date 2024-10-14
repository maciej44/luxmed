package com.interview.luxmed.company.service;

import com.interview.luxmed.company.dao.CompanyRepository;
import com.interview.luxmed.company.dto.CompanyDto;
import com.interview.luxmed.company.model.Company;
import com.interview.luxmed.util.exception.handler.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompanyService {
    private final Logger logger = LoggerFactory.getLogger(CompanyService.class);
    private final CompanyRepository companyRepository;
    CompanyService (CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Optional<CompanyDto> getCompanyByName(String name) {
        logger.info("Querying database for company with name: {}", name);
        return companyRepository.findByName(name).map(CompanyService::mapToDto);
    }

    public List<CompanyDto> getMatchingByName(String name) {
        logger.info("Querying database for company with name: {}", name);
        return companyRepository.streamByName(name).map(CompanyService::mapToDto).toList();
    }

    public Company saveCompany(Company company) throws Exception {
        try {
            Company newCompany = companyRepository.save(company);
            logger.info("Successfully saved company with name: {}", company);
            return newCompany;
        } catch (DbActionExecutionException e) {
            throw ExceptionUtil.handleDbActionExecutionException(e, company);
        }
    }

    public Optional<CompanyDto> replaceCompany(String name, Company company) {
        return companyRepository.findByName(name)
                .map(obj -> {
                    company.setId(obj.getId());
                    logger.info("Updating company: {} to {}", obj, company);
                    return mapToDto(companyRepository.save(company));
                });
    }

    @Transactional
    public boolean deleteCompanyByName(String name) {
        // foreign key constraint will be thrown when deleting company referenced by department
        // which should be handled. For purpose of this demonstration save new company before deleting it
        return companyRepository.findByName(name).map(obj -> {
            companyRepository.deleteById(obj.getId()); // Data JDBC can only delete by id, custom delete query should be implemented for deleting by name
            logger.info("Successfully deleted company with name: {}", name);
            return true;
        }).orElse(false);
    }

    private static CompanyDto mapToDto(Company company) {
        return new CompanyDto(
                Objects.requireNonNull(company, "Cannot map null to CompanyDto record").getName());
    }
}