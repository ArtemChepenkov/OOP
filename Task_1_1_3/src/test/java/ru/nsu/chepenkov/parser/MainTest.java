package ru.nsu.chepenkov.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MainTest {
    @Test
    @DisplayName("MainTestInput")
    void MainTestInput() {
        InputStream stdin = System.in;

        System.setIn(new ByteArrayInputStream("(x-y)\nx=1;y=2\nx".getBytes()));
        Main.main(new String[0]);

        System.setIn(stdin);
        assert(true);

    }
}
