package org.example.service.impl;

import org.example.model.CurrencyRate;
import org.example.service.CurrencyRatesReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRatesCsvFileReader implements CurrencyRatesReader {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    public List<CurrencyRate> read(String path) {
        List<CurrencyRate> currencyRates = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine(); // Пропускаємо заголовок

            String line;
            while ((line = br.readLine()) != null) {
                CurrencyRate rate = parseLine(line);
                if (rate != null) {
                    currencyRates.add(rate);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currencyRates;
    }

    private CurrencyRate parseLine(String line) {
        String[] data = line.split(",");

        try {
            LocalDate date = LocalDate.parse(data[0].replace("\"", ""), formatter);
            double price = Double.parseDouble(data[3].replace("\"", ""));
            double changePercentage = Double.parseDouble(data[6].replace("\"", "").replace("%", ""));

            return new CurrencyRate(date, price, changePercentage);
        } catch (Exception e) {
            System.err.println("Помилка розбору рядка: " + line);
            return null;
        }
    }
}
