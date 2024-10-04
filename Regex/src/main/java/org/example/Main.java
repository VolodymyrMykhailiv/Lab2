package org.example;

import java.util.List;
public class Main {
    public static void main(String[] args) {
        // Шлях до вашого текстового файлу
        String filePath = "src/main/java/org/example/numbers_example.txt"; // Змініть на правильний шлях до файлу

        // Виклик методу для знаходження чисел та отримання списку
        NumberFinder finder = new NumberFinder();
        List<String> numbers = finder.findNumbers(filePath);

        // Виведення знайдених чисел
        System.out.println("Знайдені числа:");
        for (String number : numbers) {
            System.out.println(number);
        }
    }
}
