package com.vishnu.salarycalculator.infrastructure.web.api;

import com.vishnu.salarycalculator.domain.india.IndiaSalaryBreakdown;
import com.vishnu.salarycalculator.domain.india.IndiaSalaryCalculator;
import com.vishnu.salarycalculator.domain.india.IndiaSalaryInput;
import com.vishnu.salarycalculator.infrastructure.web.dto.IndiaSalaryRequest;
import com.vishnu.salarycalculator.infrastructure.web.dto.IndiaSalaryResponse;
import com.vishnu.salarycalculator.infrastructure.web.mapper.IndiaSalaryRequestMapper;
import com.vishnu.salarycalculator.infrastructure.web.mapper.IndiaSalaryResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/india/salary")
@Tag(name = "India Salary API", description = "Calculate take-home salary under India tax regime")
public class IndiaSalaryApi {

    private final IndiaSalaryCalculator calculator;

    public IndiaSalaryApi(IndiaSalaryCalculator calculator) {
        this.calculator = calculator;
    }

    @PostMapping("/calculate")
    @Operation(summary = "Calculate take-home salary", description = "Calculates monthly take-home salary using fixed pay or basic/cash components")
    public ResponseEntity<IndiaSalaryResponse> calculate(@Valid @RequestBody IndiaSalaryRequest indiaSalaryRequest) {
        IndiaSalaryInput indiaSalaryInput = IndiaSalaryRequestMapper.to(indiaSalaryRequest);
        IndiaSalaryBreakdown indiaSalaryBreakdown = calculator.calculate(indiaSalaryInput);
        IndiaSalaryResponse indiaSalaryResponse = IndiaSalaryResponseMapper.from(indiaSalaryBreakdown);
        return ResponseEntity.ok(indiaSalaryResponse);
    }
}