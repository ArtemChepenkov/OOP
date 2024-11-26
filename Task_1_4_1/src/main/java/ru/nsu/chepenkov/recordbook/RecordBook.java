package ru.nsu.chepenkov.recordbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecordBook {
    private final List<Semestr> semestrList;

    public RecordBook() {
        this.semestrList = new ArrayList<>();
        semestrList.add(new Semestr(2, 3, 1, 3, 3, 3, 0, 0));
        semestrList.add(new Semestr(2, 3, 1, 3, 3, 2, 0, 0));
        semestrList.add(new Semestr(3, 2, 0, 2, 6, 0, 0, 0));
        semestrList.add(new Semestr(2, 1, 0, 5, 5, 0, 0, 0));
        semestrList.add(new Semestr(2, 2, 0, 3, 4, 0, 0, 0));
        semestrList.add(new Semestr(2, 2, 0, 2, 6, 0, 0, 0));
        semestrList.add(new Semestr(2, 0, 0, 1, 4, 1, 1, 0));
        semestrList.add(new Semestr(0, 0, 0, 0, 0, 0, 0, 1));
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
            if (currentSemestr == 8
                    && semestrList.get(currentSemestr - 1).getVkrMark() == 5) {
                return true;
            }
            return false;
        }
}
