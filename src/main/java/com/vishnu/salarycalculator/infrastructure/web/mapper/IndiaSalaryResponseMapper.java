package com.vishnu.salarycalculator.infrastructure.web.mapper;

import com.vishnu.salarycalculator.domain.india.IndiaSalaryBreakdown;
import com.vishnu.salarycalculator.infrastructure.web.dto.IndiaSalaryResponse;

public class IndiaSalaryResponseMapper {

    private IndiaSalaryResponseMapper() {
    }

    public static IndiaSalaryResponse from(IndiaSalaryBreakdown breakdown) {
        return new IndiaSalaryResponse(
                breakdown.monthlyTakeHome()
        );
    }
}
