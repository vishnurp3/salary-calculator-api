package com.vishnu.salarycalculator.domain.india.tax;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class NewTaxRegime2025CalculatorTest {

    @Test
    void shouldCalculateCorrectAnnualIncomeTax() {
        var calculator = new NewTaxRegime2025Calculator();
        BigDecimal taxableIncome = new BigDecimal("1800000");
        BigDecimal expectedTax = new BigDecimal("150800.00");
        BigDecimal tax = calculator.calculateAnnualTax(taxableIncome);
        assertThat(tax).isEqualTo(expectedTax);
    }
}