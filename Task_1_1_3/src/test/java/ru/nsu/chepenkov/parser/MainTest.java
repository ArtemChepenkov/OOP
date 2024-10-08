package ru.nsu.chepenkov.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MainTest {

    @Test
    @DisplayName("MainTestSimpleTest")
    void MainTestSimpleTest() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String testData = "(x-y)\nx=10;y=3\nx";


        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(testData.getBytes()));

        Main.main(new String[0]);

        System.setIn(stdin);

        assert(outContent.toString().trim().contains("7.0"));
        assert(outContent.toString().trim().contains("1.0"));
    }
}
