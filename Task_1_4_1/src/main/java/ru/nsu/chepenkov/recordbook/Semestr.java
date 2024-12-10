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

    /** Преобразование объекта в строку. */
    public String toText() {
        StringBuilder sb = new StringBuilder();
        for (ControlType controlType : ControlType.values()) {
            Grades grades = semestrRecord.get(controlType);
            sb.append(controlType.name()).append(":").append(grades.toText()).append(";");
        }
        return sb.toString();
    }

    /** Восстановление объекта из строки. */
    public static Semestr fromText(String text) {
        Map<ControlType, Grades> semestrRecord = new HashMap<>();
        String[] parts = text.split(";");
        for (String part : parts) {
            String[] mainParts = part.split("\\|");
            String[] controlParts = mainParts[0].split(":");
            int maxGrades = Integer.parseInt(controlParts[1]);
            ControlType controlType = ControlType.valueOf(controlParts[0]);
            //Grades grades = Grades.fromText(controlParts[1]);
            //System.out.println(grades);
            Grades grades = new Grades(maxGrades);
            if (mainParts.length > 1) {
                String[] marks = mainParts[1].split("\t");
                for (int i = 0; i < marks.length; i++) {
                    String[] markPair = marks[i].split(",");
                    grades.addGrade(Integer.parseInt(markPair[1]), markPair[0]);
                }
            }
            semestrRecord.put(controlType, grades);
        }
        Semestr semestr = new Semestr(0, 0, 0, 0, 0, 0, 0, 0); // Заглушка для конструктора.
        semestr.semestrRecord.clear();
        semestr.semestrRecord.putAll(semestrRecord);
        return semestr;
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
                .flatMap(x -> x.getValues().stream())
                .filter(x -> x <= examMark)
                .findAny();

        Optional<Integer> res1 = semestrRecord.get(ControlType.EXAM)
                .getValues()
                .stream()
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
                .getValues()
                .stream()
                .findFirst()
                .orElse(0);
    }

    /**Добавление оценки.*/
    public void addGradeSemestr(String subject, int grade, ControlType controlType) {
        semestrRecord.get(controlType).addGrade(grade, subject);
    }
}
