package ru.nsu.chepenkov.recordbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Зачётная книжка, она хранит список семестр.
 */

public class RecordBook {
    private final List<Semestr> semestrList;

    public RecordBook(List<Semestr> semestrList) {
        this.semestrList = semestrList;
    }

    /**Проверка на возможность перевода на бюджет.*/
    public boolean canBudgetTransfer(int currentSemestr) {
        if (currentSemestr < 2) {
            return false;
        }
        return !semestrList.get(currentSemestr - 2).hasMarks(3, 2)
                && !semestrList.get(currentSemestr - 1).hasMarks(3, 2);
    }

    /**Проверка на возможность получения красного диплома, также может предиктить.*/
    public boolean canGetRedDiploma(int currentSemestr) {
        long totalMarks = semestrList.stream()
                .flatMap(semestr -> semestr.getSemestrRecord()
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getKey() != ControlType.CREDIT)
                        .flatMap(entry -> entry.getValue().getValues().stream()))
                .count();

        long markFive = semestrList.stream()
                .flatMap(semestr -> semestr.getSemestrRecord()
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getKey() != ControlType.CREDIT)
                        .flatMap(entry -> entry.getValue().getValues().stream()))
                .filter(mark -> mark == 5)
                .count();
        if ((double) markFive / totalMarks < 0.75) {
            return false;
        }

        long markThree = semestrList.stream()
                .flatMap(semestr -> semestr.getSemestrRecord()
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getKey() != ControlType.CREDIT)
                        .flatMap(entry -> entry.getValue().getValues().stream()))
                .filter(mark -> mark == 3)
                .count();
        if (markThree > 0) {
            return false;
        }
        if (currentSemestr != 8) {
            return (double) markFive / totalMarks >= 0.75;
        }
        return currentSemestr == 8
                && semestrList.get(currentSemestr - 1).getVkrMark() == 5;
    }

    /**Добавление оценки до лимита.*/
    public void addGrade(int semestr, String subject,
                         int grade, ControlType controlType) {
        semestrList.get(semestr - 1).addGradeSemestr(subject, grade, controlType);
    }

    /**Подсчёт среднего балла.*/
    public double getAverageScore() {
        return semestrList.stream()
                .mapToDouble(Semestr::getSemestrAverageScore)
                .filter(x -> x > 0)
                .average()
                .orElse(0);
    }

    /**Проверка на увеличенную стипендию.*/
    public boolean canGetIncreasedScholarship(int currentSemestr) {
        return semestrList.get(currentSemestr - 1)
                .getSemestrRecord()
                .entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().getValues().stream())
                .count()
                ==
                semestrList.get(currentSemestr - 1)
                        .getSemestrRecord()
                        .entrySet()
                        .stream()
                        .flatMap(entry -> entry.getValue().getValues().stream())
                        .filter(x -> x == 5)
                        .count();
    }

    public void saveToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Semestr semestr : semestrList) {
                //System.out.println(semestr.toText());
                writer.write(semestr.toText());
                writer.newLine();
            }
        }
    }

    public static RecordBook loadFromFile(String filePath) throws IOException {
        List<Semestr> semestrList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                semestrList.add(Semestr.fromText(line));
            }
        }
        return new RecordBook(semestrList);
    }
}
