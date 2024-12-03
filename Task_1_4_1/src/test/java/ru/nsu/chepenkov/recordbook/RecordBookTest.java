package ru.nsu.chepenkov.recordbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Тесты для зачётной книжки, тут проверяются и успешный случай и неуспешный.
 */

public class RecordBookTest {

    RecordBook recordBook;

    @BeforeEach
    @DisplayName("SetUp")
    void setUp() {
        ArrayList<Semestr> semestrList = new ArrayList<>();
        semestrList.add(new Semestr(2, 3, 1, 3, 3, 3, 0, 0));
        semestrList.add(new Semestr(2, 3, 1, 3, 3, 2, 0, 0));
        semestrList.add(new Semestr(3, 2, 0, 2, 6, 0, 0, 0));
        semestrList.add(new Semestr(2, 1, 0, 5, 5, 0, 0, 0));
        semestrList.add(new Semestr(2, 2, 0, 3, 4, 0, 0, 0));
        semestrList.add(new Semestr(2, 2, 0, 2, 6, 0, 0, 0));
        semestrList.add(new Semestr(2, 0, 0, 1, 4, 1, 1, 0));
        semestrList.add(new Semestr(0, 0, 0, 0, 0, 0, 0, 1));

        recordBook = new RecordBook(semestrList);

        recordBook.addGrade(1, "History", 5, ControlType.EXAM);
        recordBook.addGrade(1, "OOP", 5, ControlType.EXAM);
        recordBook.addGrade(1, "OSI", 5, ControlType.EXAM);
        recordBook.addGrade(1, "History", 5, ControlType.DIF_CREDIT);
        recordBook.addGrade(1, "OOP", 5, ControlType.DIF_CREDIT);
        recordBook.addGrade(1, "OSI", 5, ControlType.DIF_CREDIT);


        recordBook.addGrade(2, "History", 5, ControlType.EXAM);
        recordBook.addGrade(2, "OOP", 5, ControlType.EXAM);
        recordBook.addGrade(2, "OSI", 5, ControlType.EXAM);
        recordBook.addGrade(2, "History", 5, ControlType.DIF_CREDIT);
        recordBook.addGrade(2, "OOP", 5, ControlType.DIF_CREDIT);

    }

    @Test
    @DisplayName("RecordBookCanBudgetTransferTest")
    void canBudgetTransferTest() {
        assertTrue(recordBook.canBudgetTransfer(2));
    }

    @Test
    @DisplayName("RecordBookCantBudgetTransferTest")
    void cantBudgetTransferTest() {
        recordBook.addGrade(2, "English", 3, ControlType.DIF_CREDIT);
        assertFalse(recordBook.canBudgetTransfer(2));
    }

    @Test
    @DisplayName("RecordBookCanGetRedDiplomaTest")
    void canGetRedDiplomaTest() {
        assertTrue(recordBook.canGetRedDiploma(2));
    }

    @Test
    @DisplayName("RecordBookCantGetRedDiplomaTest")
    void cantGetRedDiplomaTest() {
        recordBook.addGrade(3, "OSI", 3, ControlType.EXAM);
        assertFalse(recordBook.canGetRedDiploma(3));
    }

    @Test
    @DisplayName("RecordBookAverageScoreTest")
    void averageScoreTest() {
        recordBook.addGrade(2, "Models of computing", 3, ControlType.DIF_CREDIT);
        assertEquals((double) 58 / 12, recordBook.getAverageScore());
    }

    @Test
    @DisplayName("RecordBookIncreasedScholarshipTest")
    void increasedScholarshipTest() {
        assertTrue(recordBook.canGetIncreasedScholarship(2));
    }

    @Test
    @DisplayName("RecordBookCantGetIncreasedScholarshipTest")
    void cantGetincreasedScholarshipTest() {
        recordBook.addGrade(2, "Models of computing", 4, ControlType.DIF_CREDIT);
        assertFalse(recordBook.canGetIncreasedScholarship(2));
    }
}
