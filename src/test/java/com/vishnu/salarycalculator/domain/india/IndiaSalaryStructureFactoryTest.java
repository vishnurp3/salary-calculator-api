package com.vishnu.salarycalculator.domain.india;

import com.vishnu.salarycalculator.domain.india.tax.TaxRegime;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class IndiaSalaryStructureFactoryTest {

    @Test
    void shouldCreateFromFixedPayOnly() {
        IndiaSalaryInput input = IndiaSalaryInput.withFixedPayOnly(
                new BigDecimal("1000000"),
                TaxRegime.newRegimeFor2025()
        );
        IndiaSalaryStructure structure = IndiaSalaryStructureFactory.createFrom(input);
        assertThat(structure.basicPay()).isEqualByComparingTo("461233.34");
        assertThat(structure.cashAllowance()).isEqualByComparingTo("461233.34");
        assertThat(structure.fixedPay()).isEqualByComparingTo("1000000");
    }

    @Test
    void shouldCreateFromBasicAndAllowance() {
        IndiaSalaryInput input = IndiaSalaryInput.withBasicAndAllowance(
                new BigDecimal("400000"),
                new BigDecimal("600000"),
                TaxRegime.newRegimeFor2025()
        );
        IndiaSalaryStructure structure = IndiaSalaryStructureFactory.createFrom(input);
        assertThat(structure.basicPay()).isEqualByComparingTo("400000");
        assertThat(structure.cashAllowance()).isEqualByComparingTo("600000");
        assertThat(structure.fixedPay()).isEqualByComparingTo("1067240");
    }

    @Test
    void shouldThrowExceptionWhenOnlyBasicProvided() {
        IndiaSalaryInput input = new IndiaSalaryInput(
                null,
                new BigDecimal("500000"),
                null,
                TaxRegime.newRegimeFor2025()
        );
        assertThatThrownBy(() -> IndiaSalaryStructureFactory.createFrom(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid combination of inputs: must be either fixedPay or (basicPay + cashAllowance)");
    }

    @Test
    void shouldThrowExceptionWhenAllFieldsAreNull() {
        IndiaSalaryInput input = new IndiaSalaryInput(
                null, null, null, TaxRegime.newRegimeFor2025()
        );
        assertThatThrownBy(() -> IndiaSalaryStructureFactory.createFrom(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid combination of inputs: must be either fixedPay or (basicPay + cashAllowance)");
    }
}
