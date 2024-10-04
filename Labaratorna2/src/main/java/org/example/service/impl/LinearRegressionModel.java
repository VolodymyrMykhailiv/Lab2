package org.example.service.impl;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.example.model.CurrencyRate;
import org.example.service.PredictModel;

import java.time.LocalDate;
import java.util.List;

public class LinearRegressionModel implements PredictModel {
    private SimpleRegression regression;

    public LinearRegressionModel() {
        regression = new SimpleRegression();
    }

    @Override
    public void train(List<CurrencyRate> rates) {
        for (CurrencyRate rate : rates) {
            // Використовуємо індекс (число днів) як незалежну змінну
            double x = rate.getDate().toEpochDay();
            double y = rate.getPrice();
            regression.addData(x, y);
        }
    }

    @Override
    public double predict(LocalDate futureDate) {
        double x = futureDate.toEpochDay();
        return regression.predict(x);
    }
}

