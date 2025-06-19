package com.vishnu.salarycalculator.domain.india;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record IndiaSalaryStructure(
        BigDecimal basicPay,
        BigDecimal cashAllowance,
        BigDecimal employerPf,
        BigDecimal gratuity
) {
    public static IndiaSalaryStructure fromFixedPay(BigDecimal fixedPay) {
        BigDecimal multiplier = new BigDecimal("2.1681");
        BigDecimal basic = fixedPay.divide(multiplier, 2, RoundingMode.HALF_UP);
        BigDecimal cashAllowance = basic;
        BigDecimal employerPf = basic.multiply(new BigDecimal("0.12")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal gratuity = basic.multiply(new BigDecimal("0.0481")).setScale(2, RoundingMode.HALF_UP);
        return new IndiaSalaryStructure(
                basic,
                cashAllowance,
                employerPf,
                gratuity
        );
    }
}
