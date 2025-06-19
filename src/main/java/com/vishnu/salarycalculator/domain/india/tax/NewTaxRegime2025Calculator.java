package com.vishnu.salarycalculator.domain.india.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NewTaxRegime2025Calculator implements IncomeTaxCalculator {

    @Override
    public BigDecimal calculateAnnualTax(BigDecimal taxableIncome) {
        BigDecimal deduction = new BigDecimal("75000");
        BigDecimal reducedIncome = taxableIncome.subtract(deduction);
        if (reducedIncome.compareTo(new BigDecimal("1200000")) <= 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal[][] slabs = {
                {new BigDecimal("400000"), new BigDecimal("0.00")},
                {new BigDecimal("800000"), new BigDecimal("0.05")},
                {new BigDecimal("1200000"), new BigDecimal("0.10")},
                {new BigDecimal("1600000"), new BigDecimal("0.15")},
                {new BigDecimal("2000000"), new BigDecimal("0.20")},
                {new BigDecimal("2400000"), new BigDecimal("0.25")},
                {new BigDecimal(String.valueOf(Integer.MAX_VALUE)), new BigDecimal("0.30")}
        };
        BigDecimal previous = BigDecimal.ZERO;
        BigDecimal tax = BigDecimal.ZERO;
        for (BigDecimal[] slab : slabs) {
            BigDecimal upper = slab[0];
            BigDecimal rate = slab[1];
            if (reducedIncome.compareTo(previous) <= 0) break;
            BigDecimal taxableAtThisSlab = reducedIncome.min(upper).subtract(previous);
            BigDecimal slabTax = taxableAtThisSlab.multiply(rate);
            tax = tax.add(slabTax);
            previous = upper;
        }
        BigDecimal taxWithCess = tax.multiply(new BigDecimal("1.04"));
        return taxWithCess.setScale(2, RoundingMode.HALF_UP);
    }

}
