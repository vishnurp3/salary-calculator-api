package com.vishnu.salarycalculator.configuration;

import com.vishnu.salarycalculator.domain.india.IndiaSalaryCalculator;
import com.vishnu.salarycalculator.domain.india.tax.IndiaIncomeTaxCalculatorResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IndiaSalaryConfiguration {

    @Bean
    public IndiaSalaryCalculator indiaSalaryCalculator() {
        return new IndiaSalaryCalculator(indiaIncomeTaxCalculatorResolver());
    }

    @Bean
    public IndiaIncomeTaxCalculatorResolver indiaIncomeTaxCalculatorResolver() {
        return new IndiaIncomeTaxCalculatorResolver();
    }
}
