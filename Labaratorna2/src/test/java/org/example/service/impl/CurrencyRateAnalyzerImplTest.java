package org.example.service.impl;

import org.example.model.AnalysisResult;
import org.example.model.CurrencyRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRateAnalyzerImplTest {
    private List<CurrencyRate> rates;
    private CurrencyRateAnalyzerImpl analyzer;

    @BeforeEach
    void setUp() {
        rates = Arrays.asList(
                new CurrencyRate(LocalDate.of(2024, 1, 1), 100.0, 0.8),
                new CurrencyRate(LocalDate.of(2024, 1, 2), 105.0, 0.8),
                new CurrencyRate(LocalDate.of(2024, 1, 3), 102.0, 0.8),
                new CurrencyRate(LocalDate.of(2024, 1, 4), 108.0, 0.8)
        );
        analyzer = new CurrencyRateAnalyzerImpl(rates);
    }

    @Test
    void analyze_withValidDateRange_returnsCorrectAnalysisResult() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 4);

        AnalysisResult result = analyzer.analyze(startDate, endDate);

        assertEquals(103.75, result.getAverage(), 0.01); // Перевірка середнього значення
        assertEquals(100.0, result.getMin(), 0.01); // Перевірка мінімального значення
        assertEquals(108.0, result.getMax(), 0.01); // Перевірка максимального значення
        assertEquals(2, result.getIncreasingTrends()); // Перевірка кількості підвищень
        assertEquals(1, result.getDecreasingTrends()); // Перевірка кількості знижень
        assertEquals("Підвищення курсу", result.getOverallTrend()); // Перевірка загальної тенденції
    }

    @Test
    void analyze_withEmptyRates_returnsNull() {
        analyzer = new CurrencyRateAnalyzerImpl(Arrays.asList());

        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 4);

        AnalysisResult result = analyzer.analyze(startDate, endDate);

        assertNull(result); // Перевірка, що результат є null для порожнього списку
    }

    @Test
    void analyze_withNoMatchingDates_returnsNull() {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        AnalysisResult result = analyzer.analyze(startDate, endDate);

        assertNull(result); // Перевірка, що результат є null, якщо немає відповідних дат
    }
}