package com.vishnu.salarycalculator.domain.india;

import com.vishnu.salarycalculator.domain.india.tax.IncomeTaxCalculator;
import com.vishnu.salarycalculator.domain.india.tax.IndiaIncomeTaxCalculatorResolver;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class IndiaSalaryCalculator {

    private final IndiaIncomeTaxCalculatorResolver taxResolver;

    public IndiaSalaryCalculator(IndiaIncomeTaxCalculatorResolver taxResolver) {
        this.taxResolver = taxResolver;
    }

    public IndiaSalaryBreakdown calculate(IndiaSalaryInput input) {
        IndiaSalaryStructure structure = IndiaSalaryStructureFactory.createFrom(input);
        BigDecimal grossAnnual = structure.basicPay().add(structure.cashAllowance());
        BigDecimal grossMonthly = grossAnnual.divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
        BigDecimal employeePfMonthly = structure.employerPf().divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
        BigDecimal professionalTax = new BigDecimal("208");
        IncomeTaxCalculator taxCalculator = taxResolver.resolve(input.taxRegime());
        BigDecimal annualTax = taxCalculator.calculateAnnualTax(grossAnnual);
        BigDecimal monthlyTax = annualTax.divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
        BigDecimal takeHome = grossMonthly
                .subtract(employeePfMonthly)
                .subtract(professionalTax)
                .subtract(monthlyTax)
                .setScale(0, RoundingMode.HALF_UP);
        return new IndiaSalaryBreakdown(
                grossMonthly,
                employeePfMonthly,
                monthlyTax,
                professionalTax,
                annualTax,
                takeHome
        );
    }
}
