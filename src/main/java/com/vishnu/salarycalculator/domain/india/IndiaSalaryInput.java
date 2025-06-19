package com.vishnu.salarycalculator.domain.india;

import com.vishnu.salarycalculator.domain.india.tax.TaxRegime;

import java.math.BigDecimal;

public record IndiaSalaryInput(
        BigDecimal fixedPay,
        TaxRegime taxRegime
) {
}
