package ru.nsu.chepenkov.recordbook;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Можно скзазать, что это конечный класс в иерархии зачётной книжки.
 */
public class Grades {
    private final Map<String, Integer> grades;
    private final int maxGrades;

    public Grades(int maxGrades) {
        this.grades = new HashMap<>();
        this.maxGrades = maxGrades;
    }

    /**Получение среднего.*/
    public double getAverage() {
        return grades.values()
                .stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0);
    }

    /**Вспомогательная функция для стримов.*/
    public Stream<Integer> getGrades() {
        return grades.values()
                .stream();
    }

    /**Один из этапов добавления оценки.*/
    public void addGrade(int grade, String subject) {
        if (!grades.containsKey(subject) && grades.size() < maxGrades) {
            grades.put(subject, grade);
        }

    }

}
