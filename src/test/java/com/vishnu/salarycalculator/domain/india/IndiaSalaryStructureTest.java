package com.vishnu.salarycalculator.domain.india;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class IndiaSalaryStructureTest {

    @Test
    void shouldDeriveSalaryComponents_FromFixedPay() {
        var fixedPay = new BigDecimal("2236395.00");
        var structure = IndiaSalaryStructure.fromFixedPay(fixedPay);
        assertThat(structure.basicPay())
                .isEqualByComparingTo(new BigDecimal("1031499.93"));
        assertThat(structure.cashAllowance())
                .isEqualByComparingTo(new BigDecimal("1031499.93"));
        assertThat(structure.employerPf())
                .isEqualByComparingTo(new BigDecimal("123779.99"));
        assertThat(structure.gratuity())
                .isEqualByComparingTo(new BigDecimal("49615.15"));
    }
}
