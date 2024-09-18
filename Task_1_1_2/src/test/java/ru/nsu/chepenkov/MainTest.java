package ru.nsu.chepenkov;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.DisplayName;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

/**
 * Класс MainTest предназначен для тестирования класса Main.
 *
 * @author Artem Chepenkov
 */
public class MainTest {
    @Test
    @DisplayName("mainTest")
    void mainTest() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("0\n1\n0\n0\n0\n".getBytes()));
        Main.main(new String[0]);
        System.setIn(stdin);
        assertTrue(true);
    }
}
