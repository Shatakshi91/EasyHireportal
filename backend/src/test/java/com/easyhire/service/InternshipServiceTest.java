package com.easyhire.service;

import com.easyhire.exception.BadRequestException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class InternshipServiceTest {

    private final InternshipService internshipService = new InternshipService(null, null, null);

    @Test
    void searchRejectsInvalidSortField() {
        assertThrows(BadRequestException.class, () ->
                internshipService.search(null, null, null, null, null, null,
                        0, 10, "unknownField", "desc"));
    }

    @Test
    void searchRejectsInvalidPageSize() {
        assertThrows(BadRequestException.class, () ->
                internshipService.search(null, null, null, null, null, null,
                        0, 101, "createdAt", "desc"));
    }

    @Test
    void searchRejectsInvalidStipendRange() {
        assertThrows(BadRequestException.class, () ->
                internshipService.search(null, null, null, null,
                        BigDecimal.valueOf(5000), BigDecimal.valueOf(1000),
                        0, 10, "createdAt", "desc"));
    }

    @Test
    void searchRejectsNegativeMinStipend() {
        assertThrows(BadRequestException.class, () ->
                internshipService.search(null, null, null, null,
                        BigDecimal.valueOf(-100), BigDecimal.valueOf(1000),
                        0, 10, "createdAt", "desc"));
    }

    @Test
    void searchRejectsNegativeMaxStipend() {
        assertThrows(BadRequestException.class, () ->
                internshipService.search(null, null, null, null,
                        BigDecimal.valueOf(100), BigDecimal.valueOf(-50),
                        0, 10, "createdAt", "desc"));
    }

    @Test
    void searchRejectsMissingDirection() {
        assertThrows(BadRequestException.class, () ->
                internshipService.search(null, null, null, null, null, null,
                        0, 10, "createdAt", null));
    }
}
