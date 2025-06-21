package com.vishnu.salarycalculator.infrastructure.web.mapper;

import com.vishnu.salarycalculator.domain.india.IndiaSalaryInput;
import com.vishnu.salarycalculator.domain.india.tax.TaxRegime;
import com.vishnu.salarycalculator.infrastructure.web.dto.IndiaSalaryRequest;

public class IndiaSalaryRequestMapper {

    private IndiaSalaryRequestMapper() {
    }

    public static IndiaSalaryInput to(IndiaSalaryRequest dto) {
        TaxRegime regime = new TaxRegime(dto.getTaxRegime().getYear(),
                TaxRegime.Type.valueOf(dto.getTaxRegime().getType()));
        if (dto.getFixedPay() != null && (dto.getBasicPay() == null || dto.getCashAllowance() == null)) {
            return IndiaSalaryInput.withFixedPayOnly(dto.getFixedPay(), regime);
        }
        if (dto.getBasicPay() != null && dto.getCashAllowance() != null) {
            return IndiaSalaryInput.withBasicAndAllowance(dto.getBasicPay(), dto.getCashAllowance(), regime);
        }
        throw new IllegalArgumentException("Invalid input combination. Provide either fixedPay or (basicPay + cashAllowance)");
    }
}
