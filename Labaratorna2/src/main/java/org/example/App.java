package org.example;

import org.example.model.AnalysisResult;
import org.example.model.CurrencyRate;
import org.example.service.*;
import org.example.service.impl.CurrencyRateAnalyzerImpl;
import org.example.service.impl.CurrencyRatesCsvFileReader;
import org.example.service.impl.LinearRegressionModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);

        // Запитуємо користувача ввести шлях до файлу
        System.out.print("Введіть валютну пару(EUR_USD, GBP_USD, USD_UAH)\n");
        String rate = scanner.nextLine();

        String pathToFile = "D:\\Універ 5 семестр\\Кросплатформне програмування\\Лабораторна 2\\" + rate + " Historical Data.csv";

        CurrencyRatesReader reader = new CurrencyRatesCsvFileReader();
        ArrayList<CurrencyRate> rates = (ArrayList<CurrencyRate>) reader.read(pathToFile);
        for(CurrencyRate it: rates) {
            System.out.println(it);
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("Введіть початкову дату для аналізу (формат: yyyy-MM-dd):");
        String startDateInput = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateInput, dateFormatter);

        // Запитуємо у користувача кінцеву дату
        System.out.println("Введіть кінцеву дату для аналізу (формат: yyyy-MM-dd):");
        String endDateInput = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(endDateInput, dateFormatter);

        CurrencyRateAnalyzer analyzer = new CurrencyRateAnalyzerImpl(rates);
        AnalysisResult result = analyzer.analyze(startDate, endDate);

        if(result != null) {
            System.out.println(result);
        }

        // Запитуємо у користувача дату в майбутньому для прогнозування
        System.out.println("Введіть дату в майбутньому для прогнозування курсу (формат: yyyy-MM-dd):");
        String futureDateInput = scanner.nextLine();
        LocalDate futureDate = LocalDate.parse(futureDateInput, dateFormatter);

        // Прогнозування
        PredictModel regressionModel = new LinearRegressionModel();
        regressionModel.train(rates);
        double predictedPrice = regressionModel.predict(futureDate);

        // Виводимо прогнозовану ціну
        System.out.printf("Прогнозована ціна для %s: %.2f\n", futureDate, predictedPrice);

        scanner.close();
    }
}
