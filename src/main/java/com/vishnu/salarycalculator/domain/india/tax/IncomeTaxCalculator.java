package com.vishnu.salarycalculator.domain.india.tax;

import java.math.BigDecimal;

public interface IncomeTaxCalculator {
    BigDecimal calculateAnnualTax(BigDecimal taxableIncome);
}
