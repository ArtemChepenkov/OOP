package ru.nsu.chepenkov;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;
import java.io.InputStream;

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
        assertTrue(true);
        System.setIn(stdin);
    }
}
