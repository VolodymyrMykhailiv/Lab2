package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberFinder {
    public List<String> findNumbers(String filePath) {
        List<String> numbers = new ArrayList<>();

        String regex = "\\b[+-]?\\d+\\.\\d+([eE][+-]?\\d+)?\\b|\\b[+-]?\\d+([eE][+-]?\\d+)?\\b";

        Pattern pattern = Pattern.compile(regex);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    numbers.add(matcher.group());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }
}
