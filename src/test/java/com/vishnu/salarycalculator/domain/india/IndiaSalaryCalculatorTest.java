package com.vishnu.salarycalculator.domain.india;

import com.vishnu.salarycalculator.domain.india.tax.IndiaIncomeTaxCalculatorResolver;
import com.vishnu.salarycalculator.domain.india.tax.TaxRegime;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IndiaSalaryCalculatorTest {

    private final IndiaSalaryCalculator calculator = new IndiaSalaryCalculator(
            new IndiaIncomeTaxCalculatorResolver()
    );

    @Test
    void shouldCalculateMonthlyTakeHome_givenFixedPayUnderNewTaxRegime2025() {
        var input = IndiaSalaryInput.withFixedPayOnly(
                new BigDecimal("2236395.00"),
                TaxRegime.newRegimeFor2025()
        );
        var breakdown = calculator.calculate(input);
        assertThat(breakdown.grossMonthly()).isEqualByComparingTo("171916.66");
        assertThat(breakdown.employeePfMonthly()).isEqualByComparingTo("10315.00");
        assertThat(breakdown.monthlyTax()).isEqualByComparingTo("17125.33");
        assertThat(breakdown.professionalTax()).isEqualByComparingTo("208.00");
        assertThat(breakdown.annualTax()).isEqualByComparingTo("205503.97");
        assertThat(breakdown.monthlyTakeHome()).isEqualByComparingTo("144268");
    }

    @Test
    void shouldCalculateCorrectTakeHomeFromBasicAndCashAllowance() {
        IndiaSalaryInput input = IndiaSalaryInput.withBasicAndAllowance(
                new BigDecimal("800000"),
                new BigDecimal("900000"),
                TaxRegime.newRegimeFor2025()
        );
        var breakdown = calculator.calculate(input);
        assertThat(breakdown.monthlyTakeHome()).isEqualByComparingTo("122625");
    }

    @Test
    void shouldCalculateZeroTaxWhenBelowTaxableLimit() {
        IndiaSalaryInput input = IndiaSalaryInput.withFixedPayOnly(
                new BigDecimal("500000"),
                TaxRegime.newRegimeFor2025()
        );
        var breakdown = calculator.calculate(input);
        assertThat(breakdown.monthlyTakeHome()).isEqualByComparingTo("35922");
    }

    @Test
    void shouldThrowWhenTaxRegimeIsUnsupported() {
        IndiaSalaryInput input = IndiaSalaryInput.withFixedPayOnly(
                new BigDecimal("1500000"),
                new TaxRegime(2025, TaxRegime.Type.OLD)
        );
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("Tax calculation not supported for");
    }
}
