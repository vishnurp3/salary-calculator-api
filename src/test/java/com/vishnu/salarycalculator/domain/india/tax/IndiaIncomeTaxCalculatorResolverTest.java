package com.vishnu.salarycalculator.domain.india.tax;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IndiaIncomeTaxCalculatorResolverTest {

    @Test
    void shouldReturnNewTaxRegime2025Calculator() {
        TaxRegime regime = new TaxRegime(2025, TaxRegime.Type.NEW);
        IndiaIncomeTaxCalculatorResolver resolver = new IndiaIncomeTaxCalculatorResolver();
        IncomeTaxCalculator calculator = resolver.resolve(regime);
        assertThat(calculator).isInstanceOf(NewTaxRegime2025Calculator.class);
    }

    @Test
    void shouldThrowExceptionForUnsupportedRegime() {
        TaxRegime regime = new TaxRegime(2024, TaxRegime.Type.OLD);
        IndiaIncomeTaxCalculatorResolver resolver = new IndiaIncomeTaxCalculatorResolver();
        assertThatThrownBy(() -> resolver.resolve(regime))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("Tax calculation not supported for year: 2024, type: OLD");
    }
}
