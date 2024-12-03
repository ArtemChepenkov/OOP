package ru.nsu.chepenkov.recordbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecordBook {
    private final List<Semestr> semestrList;

    public RecordBook(List<Semestr> semestrList) {
        this.semestrList = semestrList;
    }

        public boolean canBudgetTransfer(int currentSemestr) {
            if (currentSemestr < 2) {
                return false;
            }
            return !semestrList.get(currentSemestr - 2).hasMarks(3, 2)
                    && !semestrList.get(currentSemestr - 1).hasMarks(3, 2);
        }

        public boolean canGetRedDiploma(int currentSemestr) {
            long totalMarks = semestrList.stream()
                    .flatMap(semestr -> semestr.getSemestrRecord()
                            .entrySet()
                            .stream()
                            .filter(entry -> entry.getKey() != ControlType.CREDIT)
                            .flatMap(entry -> entry.getValue().getGrades()))
                    .count();
            // We don't have to look at credit's marks
            long markFive = semestrList.stream()
                    .flatMap(semestr -> semestr.getSemestrRecord()
                            .entrySet()
                            .stream()
                            .filter(entry -> entry.getKey() != ControlType.CREDIT)
                            .flatMap(entry -> entry.getValue().getGrades()))
                    .filter(mark -> mark == 5)
                    .count();
            if ((double)markFive / totalMarks < 0.75) {
                return false;
            }

            long markThree = semestrList.stream()
                    .flatMap(semestr -> semestr.getSemestrRecord()
                            .entrySet()
                            .stream()
                            .filter(entry -> entry.getKey() != ControlType.CREDIT)
                            .flatMap(entry -> entry.getValue().getGrades()))
                    .filter(mark -> mark == 3)
                    .count();
            if (markThree > 0) {
                return false;
            }
            if (currentSemestr != 8) {
                return (double)markFive / totalMarks >= 0.75;
            }
            return currentSemestr == 8
                    && semestrList.get(currentSemestr - 1).getVkrMark() == 5;
        }

        public void addGrade(int semestr, String subject,
                             int grade, ControlType controlType) {
            semestrList.get(semestr - 1).addGradeSemestr(subject, grade, controlType);
        }

        public double getAverageScore() {
            return semestrList.stream()
                    .mapToDouble(Semestr :: getSemestrAverageScore)
                    .filter(x -> x > 0)
                    .average()
                    .orElse(0);
        }

        public boolean canGetIncreasedScholarship(int currentSemestr) {
            return semestrList.get(currentSemestr - 1)
                    .getSemestrRecord()
                    .entrySet()
                    .stream()
                    .flatMap(entry -> entry.getValue().getGrades())
                    .count()
                    ==
                    semestrList.get(currentSemestr - 1)
                            .getSemestrRecord()
                            .entrySet()
                            .stream()
                            .flatMap(entry -> entry.getValue().getGrades())
                            .filter(x -> x == 5)
                            .count();

        }
}