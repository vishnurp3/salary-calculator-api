package com.vishnu.salarycalculator.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Schema(name = "IndiaSalaryRequest", description = "Salary calculation request for India")
public class IndiaSalaryRequest {

    @DecimalMin(value = "10000", message = "Fixed pay must be at least ₹10,000")
    @DecimalMax(value = "1000000000", message = "Fixed pay must be at most ₹10 Cr")
    @Schema(type = "number", format = "decimal", description = "Total fixed pay in INR", example = "2000000")
    private BigDecimal fixedPay;

    @DecimalMin(value = "10000", message = "Basic pay must be at least ₹10,000")
    @DecimalMax(value = "1000000000", message = "Basic pay must be at most ₹10 Cr")
    @Schema(type = "number", format = "decimal", description = "Basic pay component", example = "700000")
    private BigDecimal basicPay;

    @DecimalMin(value = "10000", message = "Cash allowance must be at least ₹10,000")
    @DecimalMax(value = "1000000000", message = "Cash allowance must be at most ₹10 Cr")
    @Schema(type = "number", format = "decimal", description = "Cash allowance component", example = "900000")
    private BigDecimal cashAllowance;

    @Valid
    @NotNull(message = "TaxRegime is required")
    @Schema(description = "Tax regime to apply (year and type)")
    private IndiaTaxRegime taxRegime;
}
