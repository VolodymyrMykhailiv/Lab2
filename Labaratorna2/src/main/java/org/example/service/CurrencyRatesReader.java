package org.example.service;

import org.example.model.CurrencyRate;

import java.util.List;

public interface CurrencyRatesReader {
    List<CurrencyRate> read(String path);
}
