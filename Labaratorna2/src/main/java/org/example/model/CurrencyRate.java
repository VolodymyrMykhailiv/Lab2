package org.example.model;

import java.time.LocalDate;

public class CurrencyRate {
    private LocalDate date;
    private double price;
    private double changePercentage;

    // Конструктор
    public CurrencyRate(LocalDate date, double price, double changePercentage) {
        this.date = date;
        this.price = price;
        this.changePercentage = changePercentage;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Price: " + price + ", Change %: " + changePercentage + "%";
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getChangePercentage() {
        return changePercentage;
    }

}
