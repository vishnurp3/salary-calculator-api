package com.vishnu.salarycalculator.domain.india;

import java.math.BigDecimal;

public record IndiaSalaryBreakdown(
        BigDecimal grossMonthly,
        BigDecimal employeePfMonthly,
        BigDecimal monthlyTax,
        BigDecimal professionalTax,
        BigDecimal annualTax,
        BigDecimal monthlyTakeHome
) {
}
