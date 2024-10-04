package org.example.service;

import org.example.model.CurrencyRate;

import java.time.LocalDate;
import java.util.List;

public interface PredictModel {
    void train(List<CurrencyRate> rates);

    double predict(LocalDate futureDate);
}
