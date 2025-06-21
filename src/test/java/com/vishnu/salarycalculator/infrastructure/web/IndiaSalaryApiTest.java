package com.vishnu.salarycalculator.infrastructure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishnu.salarycalculator.configuration.IndiaSalaryConfiguration;
import com.vishnu.salarycalculator.infrastructure.web.api.IndiaSalaryApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IndiaSalaryApi.class)
@Import(IndiaSalaryConfiguration.class)
class IndiaSalaryApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String HEADER_KEY = "X-API-KEY";
    private static final String API_KEY = "test-secret";

    @Test
    void shouldReturnTakeHomeForValidFixedPayRequest() throws Exception {
        Map<String, Object> request = Map.of(
                "fixedPay", "2236395",
                "taxRegime", Map.of("year", 2025, "type", "NEW")
        );
        mockMvc.perform(post("/api/v1/india/salary/calculate")
                        .header(HEADER_KEY, API_KEY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.monthlyTakeHome").value("144268"));
    }

    @Test
    void shouldReturnTakeHomeForBasicAndCashAllowance() throws Exception {
        Map<String, Object> request = Map.of(
                "basicPay", "700000",
                "cashAllowance", "900000",
                "taxRegime", Map.of("year", 2025, "type", "NEW")
        );
        mockMvc.perform(post("/api/v1/india/salary/calculate")
                        .header(HEADER_KEY, API_KEY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.monthlyTakeHome").value("116700"));
    }

    @Test
    void shouldReturnUnauthorizedForInvalidApiKey() throws Exception {
        Map<String, Object> request = Map.of(
                "fixedPay", "2236395",
                "taxRegime", Map.of("year", 2025, "type", "NEW")
        );
        mockMvc.perform(post("/api/v1/india/salary/calculate")
                        .header(HEADER_KEY, "invalid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Invalid or missing API key"));
    }

    @Test
    void shouldReturnBadRequestForOnlyBasicPayProvided() throws Exception {
        Map<String, Object> request = Map.of(
                "basicPay", "700000",
                "taxRegime", Map.of("year", 2025, "type", "NEW")
        );
        mockMvc.perform(post("/api/v1/india/salary/calculate")
                        .header(HEADER_KEY, API_KEY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid input combination. Provide either fixedPay or (basicPay + cashAllowance)"));
    }

    @Test
    void shouldReturnBadRequestForFixedPayOutOfRange() throws Exception {
        Map<String, Object> request = Map.of(
                "fixedPay", "999",
                "taxRegime", Map.of("year", 2025, "type", "NEW")
        );
        mockMvc.perform(post("/api/v1/india/salary/calculate")
                        .header(HEADER_KEY, API_KEY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Fixed pay must be at least ₹10,000"));
    }

    @Test
    void shouldReturnBadRequestForUnsupportedTaxRegime() throws Exception {
        Map<String, Object> request = Map.of(
                "fixedPay", "2236395",
                "taxRegime", Map.of("year", 2025, "type", "OLD")
        );
        mockMvc.perform(post("/api/v1/india/salary/calculate")
                        .header(HEADER_KEY, API_KEY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Tax calculation not supported for year: 2025, type: OLD"));
    }
}
