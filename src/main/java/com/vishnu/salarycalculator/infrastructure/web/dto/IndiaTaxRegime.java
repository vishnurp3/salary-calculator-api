package com.vishnu.salarycalculator.infrastructure.web.dto;

import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "IndiaTaxRegime", description = "Tax regime details including year and type")
public class IndiaTaxRegime {

    @NotNull(message = "Tax regime year must be provided")
    @Schema(type = "integer", format = "int32", description = "Tax regime year", example = "2025")
    private Integer year;

    @NotNull(message = "Tax regime type must be provided")
    @Pattern(regexp = "NEW|OLD", message = "Tax regime type must be 'NEW' or 'OLD'")
    @Schema(description = "Tax regime type", example = "NEW")
    private String type;
}
