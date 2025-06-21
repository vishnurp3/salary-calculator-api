package com.vishnu.salarycalculator.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "IndiaSalaryResponse", description = "Monthly take-home salary breakdown for India")
public record IndiaSalaryResponse(
        @Schema(description = "Net monthly take-home salary in INR", example = "100000")
        BigDecimal monthlyTakeHome
) {
}
