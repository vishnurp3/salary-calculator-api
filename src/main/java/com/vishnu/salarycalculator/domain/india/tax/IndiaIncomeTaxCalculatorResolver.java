package com.vishnu.salarycalculator.domain.india.tax;


public class IndiaIncomeTaxCalculatorResolver {

    public IncomeTaxCalculator resolve(TaxRegime regime) {
        if (regime.year() == 2025 && regime.type() == TaxRegime.Type.NEW) {
            return new NewTaxRegime2025Calculator();
        }
        throw new UnsupportedOperationException("Tax calculation not supported for year: " + regime.year() + ", type: " + regime.type());
    }
}
