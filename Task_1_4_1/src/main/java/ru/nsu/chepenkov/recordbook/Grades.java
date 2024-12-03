package ru.nsu.chepenkov.recordbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grades {
    private final Map<String, Integer> grades;
    private final int maxGrades;

    public Grades(int maxGrades) {
        this.grades = new HashMap<>();
        this.maxGrades = maxGrades;
    }

    public double getAverage() {
        return grades.values()
                .stream()
                .mapToDouble(Integer :: doubleValue)
                .average()
                .orElse(0);
    }

    public Stream<Integer> getGrades() {
        return grades.values()
                .stream();
    }

    public void addGrade(int grade, String subject) {
        if (grades.size() > maxGrades) {
            return;
        }
        if (grades.containsKey(subject)) {
            return;
        }
        grades.put(subject, grade);
    }

}
