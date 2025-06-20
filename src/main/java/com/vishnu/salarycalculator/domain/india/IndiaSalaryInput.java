package com.vishnu.salarycalculator.domain.india;

import com.vishnu.salarycalculator.domain.india.tax.TaxRegime;

import java.math.BigDecimal;

public record IndiaSalaryInput(
        BigDecimal fixedPay,
        BigDecimal basicPay,
        BigDecimal cashAllowance,
        TaxRegime taxRegime
) {
    public static IndiaSalaryInput withFixedPayOnly(BigDecimal fixedPay, TaxRegime regime) {
        return new IndiaSalaryInput(fixedPay, null, null, regime);
    }

    public static IndiaSalaryInput withBasicAndAllowance(BigDecimal basic, BigDecimal allowance, TaxRegime regime) {
        return new IndiaSalaryInput(null, basic, allowance, regime);
    }
}
