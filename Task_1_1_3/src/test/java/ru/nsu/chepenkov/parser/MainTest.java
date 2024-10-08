package ru.nsu.chepenkov.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final InputStream stdin = System.in;

    @Test
    @DisplayName("MainTestEval")
    void MainTestEval() {
        PrintStream out = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String testData = "(x/y*x-x+y)\nx=3;y=6\ny";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(testData.getBytes()));

        Main.main(new String[0]);

        assert(outContent.toString().trim().contains("4.5"));
        assert(outContent.toString().trim().contains("-8.0"));
        System.setOut(out);
        System.setIn(stdin);
    }
}
