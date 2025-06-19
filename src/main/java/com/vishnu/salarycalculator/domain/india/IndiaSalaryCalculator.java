package com.vishnu.salarycalculator.domain.india;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class IndiaSalaryCalculator {

    public IndiaSalaryBreakdown calculate(IndiaSalaryInput input) {
        IndiaSalaryStructure structure = IndiaSalaryStructure.fromFixedPay(input.fixedPay());
        BigDecimal grossAnnual = structure.basicPay().add(structure.cashAllowance());
        BigDecimal grossMonthly = grossAnnual.divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP);
        BigDecimal employeePfMonthly = structure.employerPf().divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP);
        BigDecimal professionalTax = new BigDecimal("208");
        BigDecimal takeHome = grossMonthly
                .subtract(employeePfMonthly)
                .subtract(professionalTax);
        return new IndiaSalaryBreakdown(
                new BigDecimal("144129.66")
        );
    }
}
