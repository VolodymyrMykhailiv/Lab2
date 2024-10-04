package org.example;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class NumberFinderTest {

    @Test
    public void testFindIntegers() throws IOException {
        // Підготовка тестового файлу
        String testFilePath = "testIntegers.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilePath))) {
            writer.write("Цілі числа: 42, 17, 0, 123456");
        }

        NumberFinder numberFinder = new NumberFinder();
        List<String> numbers = numberFinder.findNumbers(testFilePath);

        // Очікувані результати
        assertEquals(4, numbers.size());
        assertEquals("42", numbers.get(0));
        assertEquals("17", numbers.get(1));
        assertEquals("0", numbers.get(2));
        assertEquals("123456", numbers.get(3));

        // Видалення тестового файлу
        new File(testFilePath).delete();
    }

    @Test
    public void testFindFloatingPointNumbers() throws IOException {

        String testFilePath = "testFloatingPoint.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilePath))) {
            writer.write("Дійсні числа: 3.14, 0.001, 2.71828, 1.5e-3, 6.022e23");
        }

        NumberFinder numberFinder = new NumberFinder();
        List<String> numbers = numberFinder.findNumbers(testFilePath);

        // Очікувані результати
        assertEquals(5, numbers.size());
        assertEquals("3.14", numbers.get(0));
        assertEquals("0.001", numbers.get(1));
        assertEquals("2.71828", numbers.get(2));
        assertEquals("1.5e-3", numbers.get(3));
        assertEquals("6.022e23", numbers.get(4));

        // Видалення тестового файлу
        new File(testFilePath).delete();
    }

    @Test
    public void testFindExponentialNumbers() throws IOException {
        // Підготовка тестового файлу
        String testFilePath = "testExponential.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilePath))) {
            writer.write("Експоненціальна форма: 5e6, 4.2E+2, 1.0e-10");
        }

        NumberFinder numberFinder = new NumberFinder();
        List<String> numbers = numberFinder.findNumbers(testFilePath);

        // Очікувані результати
        assertEquals(3, numbers.size());
        assertEquals("5e6", numbers.get(0));
        assertEquals("4.2E+2", numbers.get(1));
        assertEquals("1.0e-10", numbers.get(2));

        // Видалення тестового файлу
        new File(testFilePath).delete();
    }
}
