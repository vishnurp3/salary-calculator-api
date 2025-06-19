package com.vishnu.salarycalculator.domain.india;

import java.math.BigDecimal;

public record IndiaSalaryInput(
        BigDecimal fixedPay,
        int year,
        String taxRegime
) {
}
