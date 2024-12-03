package ru.nsu.chepenkov.recordbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        recordBook.addGrade(1, "History", 5, ControlType.DIF_CREDIT);
        recordBook.addGrade(1, "OOP", 5, ControlType.DIF_CREDIT);
        recordBook.addGrade(1, "OSI", 5, ControlType.DIF_CREDIT);
    }

    @Test
    @DisplayName("RecordBookCanBudgetTransferTest")
    void canBudgetTransferTest() {
        assertTrue(recordBook.canBudgetTransfer(2));
        assertTrue(recordBook.canGetRedDiploma(2));
        assertEquals(5, recordBook.getAverageScore());
        assertTrue(recordBook.canGetIncreasedScholarship(2));
    }
}
