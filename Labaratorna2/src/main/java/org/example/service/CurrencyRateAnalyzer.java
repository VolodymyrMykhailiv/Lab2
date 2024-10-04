package org.example.service;

import org.example.model.AnalysisResult;

import java.time.LocalDate;

public interface CurrencyRateAnalyzer {
    AnalysisResult analyze(LocalDate startDate, LocalDate endDate);
}
