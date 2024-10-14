package com.interview.luxmed.company.controller;

import com.interview.luxmed.company.dto.CompanyDto;
import com.interview.luxmed.company.model.Company;
import com.interview.luxmed.company.service.CompanyService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    private final CompanyService companyService;
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<CompanyDto> getByName(@PathVariable String name, WebRequest request) {
        logger.info("Received GET request, {}", request.getDescription(false));
        return companyService.getCompanyByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getMatchingByName(@RequestParam String nameIncludes, WebRequest request) {
        logger.info("Received GET request, {}", request.getDescription(false));
        return ResponseEntity.ok(companyService.getMatchingByName(nameIncludes));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @NotNull Company company, WebRequest request) throws Exception {
        logger.info("Received POST request, {}, body={}", request.getDescription(false), company);
        companyService.saveCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{name}")
    public ResponseEntity<CompanyDto> replace(@PathVariable String name, @RequestBody Company company, WebRequest request) {
        logger.info("Received PUT request, {}, body={}", request.getDescription(false), company);
        return companyService.replaceCompany(name, company).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // implement partialUpdate for PATCH request if Company had more than just name field

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name, WebRequest request) {
        logger.info("Received DELETE request, {}", request.getDescription(false));
        return companyService.deleteCompanyByName(name)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}