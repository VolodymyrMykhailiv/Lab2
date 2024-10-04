package org.example.model;

public class AnalysisResult {
    private final double average;
    private final double min;
    private final double max;
    private final int increasingTrends;
    private final int decreasingTrends;
    private final String overallTrend;
   // private final List<String> percentChanges;


    public AnalysisResult(double average, double min, double max, int increasingTrends, int decreasingTrends, String overallTrend) {
        this.average = average;
        this.min = min;
        this.max = max;
        this.increasingTrends = increasingTrends;
        this.decreasingTrends = decreasingTrends;
        this.overallTrend = overallTrend;
       // this.percentChanges = percentChanges;
    }


    public double getAverage() {
        return average;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public int getIncreasingTrends() {
        return increasingTrends;
    }

    public int getDecreasingTrends() {
        return decreasingTrends;
    }

    public String getOverallTrend() {
        return overallTrend;
    }

//    public List<String> getPercentChanges() {
//        return percentChanges; // Геттер для відсоткових змін
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Аналіз курсів валют:\n");
        sb.append(String.format("Середній курс: %.2f\n", average));
        sb.append(String.format("Мінімальний курс: %.2f\n", min));
        sb.append(String.format("Максимальний курс: %.2f\n", max));
        sb.append(String.format("Кількість підвищень: %d\n", increasingTrends));
        sb.append(String.format("Кількість зниження: %d\n", decreasingTrends));
        sb.append("Загальна тенденція: ").append(overallTrend).append("\n");
//        sb.append("Відсоткові зміни курсу:\n");
//        for (String change : percentChanges) {
//            sb.append(change).append("\n");
//        }
        return sb.toString();
    }
}
