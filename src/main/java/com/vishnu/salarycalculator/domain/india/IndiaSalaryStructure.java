package com.vishnu.salarycalculator.domain.india;

import java.math.BigDecimal;

public record IndiaSalaryStructure(
        BigDecimal basicPay,
        BigDecimal cashAllowance,
        BigDecimal employerPf,
        BigDecimal gratuity,
        BigDecimal fixedPay
) {
}
