package com.interview.luxmed.integration.company;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;

import com.interview.luxmed.company.dao.CompanyRepository;
import com.interview.luxmed.company.model.Company;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.List;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

// Some examples of integration tests
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompanyTest {

    @LocalServerPort
    private Integer port;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    CompanyRepository customerRepository;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    void testGetRequest() {
        customerRepository.save(new Company("GetCompany"));

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/companies/GetCompany")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo("GetCompany"))
                .body("$", not(hasKey("id")))
                .extract().response();

        Company returnedCompany = response.as(Company.class);
        assertThat(returnedCompany.getName()).isEqualTo("GetCompany");
    }

    @Test
    void testDeleteRequest() {
        List<Company> customers = List.of(
                new Company("TestInc"),
                new Company("ABC")
        );
        customerRepository.saveAll(customers);

        given()
                .when()
                .get("/api/v1/companies/ABC")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo("ABC"));

        given()
                .when()
                .delete("/api/v1/companies/ABC")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        given()
                .when()
                .get("/api/v1/companies/ABC")
                .then()
                .statusCode(404);

        given()
                .when()
                .delete("/api/v1/companies/ABC")
                .then()
                .statusCode(404);

        given()
                .when()
                .get("/api/v1/companies/TestInc")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo("TestInc"));
    }
}
