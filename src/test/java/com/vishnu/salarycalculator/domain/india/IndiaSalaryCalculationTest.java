package com.vishnu.salarycalculator.domain.india;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class IndiaSalaryCalculationTest {

    @Test
    void shouldCalculateMonthlyTakeHome_givenFixedPayUnderNewTaxRegime2025() {
        var input = new IndiaSalaryInput(
                new BigDecimal("2236395.00"),
                2025,
                "NEW"
        );
        var calculator = new IndiaSalaryCalculator();
        var result = calculator.calculate(input);
        assertThat(result.monthlyTakeHome())
                .isEqualTo(new BigDecimal("144129.66"));
    }
}
