package ru.nsu.chepenkov.recordbook;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Класс, описывающий оценки,.
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

    /**Геттер для значений(сделан, чтобы избавиться от метода, который возвращал стрим).*/
    public Collection<Integer> getValues() {
        return grades.values();
    }

    /**Один из этапов добавления оценки.*/
    public void addGrade(int grade, String subject) {
        if (!grades.containsKey(subject) && grades.size() < maxGrades) {
            grades.put(subject, grade);
        }

    }

    /** Преобразование объекта в строку. */
    public String toText() {
        StringBuilder sb = new StringBuilder();
        sb.append(maxGrades).append("|");
        int count = 0;
        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            sb.append(entry.getKey()).append(",").append(entry.getValue());
            if (++count < grades.size()) {
                sb.append("\t");
            }
        }
        return sb.toString();
    }


    /** Восстановление объекта из строки. */
    public static Grades fromText(String text) {
        String[] parts = text.split(",");
        int grade = Integer.parseInt(parts[1]);
        Grades grades = new Grades(grade);
        return grades;
    }

}
