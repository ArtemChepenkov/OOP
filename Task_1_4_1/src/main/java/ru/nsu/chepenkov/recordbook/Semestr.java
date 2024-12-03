package ru.nsu.chepenkov.recordbook;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Класс семестра, каждый тип аттестации содержит в себе оценки.
 */
public class Semestr {
    private final Map<ControlType, Grades> semestrRecord;

    Semestr(int taskGrades, int testGrades, int colloquimGrades,
                    int examGrades, int difCreditGrades,
                    int creditGrades, int practiceDefenseGrades,
                    int vkrDefenseGrades) {
        semestrRecord = new HashMap<>();
        semestrRecord.put(ControlType.TASK, new Grades(taskGrades));
        semestrRecord.put(ControlType.TEST, new Grades(testGrades));
        semestrRecord.put(ControlType.COLLOQUIM, new Grades(colloquimGrades));
        semestrRecord.put(ControlType.EXAM, new Grades(examGrades));
        semestrRecord.put(ControlType.DIF_CREDIT, new Grades(difCreditGrades));
        semestrRecord.put(ControlType.CREDIT, new Grades(creditGrades));
        semestrRecord.put(ControlType.PRACTICE_DEFENSE, new Grades(practiceDefenseGrades));
        semestrRecord.put(ControlType.VKR_DEFENSE, new Grades(vkrDefenseGrades));
    }

    /**Получение среднего балла за семестр.*/
    public double getSemestrAverageScore() {

        return semestrRecord.values()
                .stream()
                .mapToDouble(Grades::getAverage)
                .filter(x -> x > 0)
                .average()
                .orElse(0);
    }

    /**Проверка на наличие оценок диф зачёта и экзамена.*/
    public boolean hasMarks(int examMark, int difCreditMark) {
        Optional<Integer> res = semestrRecord.values()
                .stream()
                .flatMap(Grades::getGrades)
                .filter(x -> x <= examMark)
                .findAny();

        Optional<Integer> res1 = semestrRecord.get(ControlType.EXAM)
                .getGrades()
                .filter(x -> x <= difCreditMark)
                .findAny();
        return !res.isEmpty() || !res1.isEmpty();
    }

    /**Просто получение записи для работы со стримами.*/
    public Map<ControlType, Grades> getSemestrRecord() {
        return semestrRecord;
    }

    /**Просто для одного случая получение оценки ВКР.*/
    public int getVkrMark() {
        return semestrRecord.get(ControlType.VKR_DEFENSE)
                .getGrades()
                .findFirst()
                .orElse(0);
    }

    /**Добавление оценки.*/
    public void addGradeSemestr(String subject, int grade, ControlType controlType) {
        semestrRecord.get(controlType).addGrade(grade, subject);
    }
}
