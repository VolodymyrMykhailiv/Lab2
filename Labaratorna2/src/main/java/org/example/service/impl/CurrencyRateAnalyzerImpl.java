package org.example.service.impl;

import org.example.model.AnalysisResult;
import org.example.model.CurrencyRate;
import org.example.service.CurrencyRateAnalyzer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyRateAnalyzerImpl implements CurrencyRateAnalyzer {
    private List<CurrencyRate> rates;

    public CurrencyRateAnalyzerImpl(List<CurrencyRate> rates) {
        this.rates = rates;
    }

    @Override
    public AnalysisResult analyze(LocalDate startDate, LocalDate endDate) {
        if (rates.isEmpty()) {
            return null;
        }

        // Фільтруємо ставки за датою
        List<CurrencyRate> filteredRates = rates.stream()
                .filter(rate -> rate.getDate().compareTo(startDate) >= 0 && rate.getDate().compareTo(endDate) <= 0)
                .collect(Collectors.toList());

        if (filteredRates.isEmpty()) {
            return null;
        }

        double total = filteredRates.stream()
                .mapToDouble(CurrencyRate::getPrice)
                .sum();

        double min = filteredRates.stream()
                .mapToDouble(CurrencyRate::getPrice)
                .min()
                .orElse(Double.MAX_VALUE);

        double max = filteredRates.stream()
                .mapToDouble(CurrencyRate::getPrice)
                .max()
                .orElse(Double.MIN_VALUE);

        // Визначаємо тренди
        List<Double> changes = new ArrayList<>();
        int increasingTrends = 0;
        int decreasingTrends = 0;

        // Змінна для попередньої ціни
        double previousPrice = -1;

        for (CurrencyRate rate : filteredRates) {
            double price = rate.getPrice();

            if (previousPrice >= 0) {
                double change = price - previousPrice;
                changes.add(change);
                if (change > 0) {
                    increasingTrends++;
                } else if (change < 0) {
                    decreasingTrends++;
                }
            }
            previousPrice = price; // Оновлюємо попередню ціну
        }

        double average = total / filteredRates.size();
        String overallTrend = analyzeOverallTrend(increasingTrends, decreasingTrends);
       // List<String> percentChanges = calculatePercentChanges(changes, filteredRates);

        // Повертаємо результат аналізу
        return new AnalysisResult(average, min, max, increasingTrends, decreasingTrends, overallTrend);
    }

    private String analyzeOverallTrend(int increasingTrends, int decreasingTrends) {
        if (increasingTrends > decreasingTrends) {
            return "Підвищення курсу";
        } else if (decreasingTrends > increasingTrends) {
            return "Падіння курсу";
        } else {
            return "Стабільність";
        }
    }

//    private List<String> calculatePercentChanges(List<Double> changes, List<CurrencyRate> filteredRates) {
//        List<String> percentChanges = new ArrayList<>();
//        for (int i = 0; i < changes.size(); i++) {
//            double previousPrice = filteredRates.get(i + 1).getPrice(); // Останній курс для обчислення відсоткової зміни
//            if (previousPrice != 0) { // Перевірка на нуль для уникнення ділення на нуль
//                double percentChange = (changes.get(i) / previousPrice) * 100; // Відсоткове змінення
//                percentChanges.add(String.format("Зміна між %s і %s: %.2f%%",
//                        filteredRates.get(i).getDate(), filteredRates.get(i + 1).getDate(), percentChange));
//            }
//        }
//        return percentChanges;
//    }
}
