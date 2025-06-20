package com.vishnu.salarycalculator.domain.india;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class IndiaSalaryStructureFactory {

    private static final BigDecimal EMPLOYER_PF_PERCENTAGE = new BigDecimal("0.12");
    private static final BigDecimal GRATUITY_PERCENTAGE = new BigDecimal("0.0481");
    private static final BigDecimal MULTIPLIER = new BigDecimal("2.1681");

    private IndiaSalaryStructureFactory() {
    }

    public static IndiaSalaryStructure createFrom(IndiaSalaryInput input) {
        BigDecimal basicPay;
        BigDecimal cashAllowance;
        if (input.fixedPay() != null && (input.basicPay() == null || input.cashAllowance() == null)) {
            basicPay = input.fixedPay().divide(MULTIPLIER, 2, RoundingMode.HALF_UP);
            cashAllowance = basicPay;
        } else if (input.basicPay() != null && input.cashAllowance() != null) {
            basicPay = input.basicPay();
            cashAllowance = input.cashAllowance();
        } else {
            throw new IllegalArgumentException("Invalid combination of inputs: must be either fixedPay or (basicPay + cashAllowance)");
        }
        BigDecimal employerPf = basicPay.multiply(EMPLOYER_PF_PERCENTAGE).setScale(2, RoundingMode.HALF_UP);
        BigDecimal gratuity = basicPay.multiply(GRATUITY_PERCENTAGE).setScale(2, RoundingMode.HALF_UP);
        BigDecimal fixedPay = basicPay.add(cashAllowance).add(employerPf).add(gratuity);
        return new IndiaSalaryStructure(basicPay, cashAllowance, employerPf, gratuity, fixedPay);
    }
}
