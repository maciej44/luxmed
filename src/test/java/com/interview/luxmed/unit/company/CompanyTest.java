package com.interview.luxmed.unit.company;

import com.interview.luxmed.company.model.Company;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    void testEqualsWithSameObject() {
        Company company = new Company("Test Company");
        assertTrue(company.equals(company));
    }

    @Test
    void testEqualsWithNull() {
        Company company = new Company("Test Company");
        assertFalse(company.equals(null));
    }

    @Test
    void testEqualsWithDifferentClass() {
        Company company = new Company("Test Company");
        assertFalse(company.equals("Not a Company object"));
    }

    @Test
    void testEqualsWithSameName() {
        Company company1 = new Company("Test Company");
        Company company2 = new Company("Test Company");
        assertTrue(company1.equals(company2));
    }

    @Test
    void testEqualsWithDifferentName() {
        Company company1 = new Company("Test Company 1");
        Company company2 = new Company("Test Company 2");
        assertFalse(company1.equals(company2));
    }

    @Test
    void testEqualsWithDifferentId() {
        Company company1 = new Company("Test Company");
        company1.setId(1L);
        Company company2 = new Company("Test Company");
        company2.setId(2L);
        assertTrue(company1.equals(company2));
    }

    @Test
    void testEqualsSymmetry() {
        Company company1 = new Company("Test Company");
        Company company2 = new Company("Test Company");
        assertTrue(company1.equals(company2) && company2.equals(company1));
    }

    @Test
    void testEqualsTransitivity() {
        Company company1 = new Company("Test Company");
        Company company2 = new Company("Test Company");
        Company company3 = new Company("Test Company");
        assertTrue(company1.equals(company2) && company2.equals(company3) && company1.equals(company3));
    }

    @Test
    void testHashcodeWithSameObject() {
        Company company1 = new Company("Test Company");
        Company company2 = new Company("Test Company");
        assertEquals(company1.hashCode(), company2.hashCode());
    }

    @Test
    void testHashcodeWithDifferentObject() {
        Company company1 = new Company("Test Company 1");
        Company company2 = new Company("Test Company 2");
        assertNotEquals(company1.hashCode(), company2.hashCode());
    }
}