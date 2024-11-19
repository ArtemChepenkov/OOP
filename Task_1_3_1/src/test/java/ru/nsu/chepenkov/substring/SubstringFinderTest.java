package ru.nsu.chepenkov.substring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubstringFinderTest {

    private File testFile;

    @BeforeEach()
    @DisplayName("CreateFile")
    void createName() {
        testFile = new File("test.txt");
        try {
            if (!testFile.createNewFile()) {
                System.err.println("File is already created");
            }
        } catch (IOException e) {
            System.err.println("An error occurred");
            e.getStackTrace();
        }
    }

    @AfterEach
    @DisplayName("DeleteFile")
    void deleteFile() {

        if (!testFile.delete()) {
            System.err.println("Failed to delete file");
        }
    }

    @Test
    @DisplayName("SubstringFinderTestSimpleFile")
    void testSimpleFile() throws IOException {
        ArrayList<Long> expected = new ArrayList<>();
        List<Long> res = new ArrayList<>();
        expected.add(3L);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt",
                StandardCharsets.UTF_8))) {
            writer.write("HelloWorld");

            writer.close();
            BufferedReader reader = new BufferedReader(new FileReader("test.txt",
                    StandardCharsets.UTF_8));

            res = SubstringFinder.find("test.txt", "loW");
            reader.close();

        } catch (IOException e) {
            System.err.println("File write error");
            e.printStackTrace();
        }
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("SubstringFinderTestBigFile")
    void testBigFile() throws IOException {
        ArrayList<Long> expected = new ArrayList<>();
        List<Long> res = new ArrayList<>();
        for (long i = 0L; i < 1000L; i++) {
            expected.add(3 + 10L * i);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt",
                StandardCharsets.UTF_8))) {
            for (long i = 0L; i < 1000L; i++) {
                writer.write("HelloWorld");
            }


            writer.close();
            BufferedReader reader = new BufferedReader(new FileReader("test.txt",
                    StandardCharsets.UTF_8));

            res = SubstringFinder.find("test.txt", "loW");
            reader.close();

        } catch (IOException e) {
            System.err.println("File write error");
            e.printStackTrace();
        }
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("SubstringFinderTestBorderSubstring")
    void testBorderSubstring() throws IOException {
        ArrayList<Long> expected = new ArrayList<>();
        List<Long> res = new ArrayList<>();
        int bufferSize = new SubstringFinder().getBufferSize();
        expected.add(bufferSize-2L);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt",
                StandardCharsets.UTF_8))) {
            for (long i = 0L; i < bufferSize-2L; i++) {
                writer.write("a");
            }
            writer.write("Hello");


            writer.close();
            BufferedReader reader = new BufferedReader(new FileReader("test.txt",
                    StandardCharsets.UTF_8));

            res = SubstringFinder.find("test.txt", "Hello");
            reader.close();

        } catch (IOException e) {
            System.err.println("File write error");
            e.printStackTrace();
        }
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("SubstringFinderTestLargeFile")
    void testLargeFile() throws IOException {
        ArrayList<Long> expected = new ArrayList<>();
        List<Long> res = new ArrayList<>();
        int bufferSize = new SubstringFinder().getBufferSize();
        expected.add(16_000_000_000L);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt",
                StandardCharsets.UTF_8))) {
            for (long i = 0L; i < 1_000_000_000L; i++) {
                writer.write("aaaaaaaaaaaaaaaa");
            }
            writer.write("Hello");


            writer.close();
            BufferedReader reader = new BufferedReader(new FileReader("test.txt",
                    StandardCharsets.UTF_8));

            res = SubstringFinder.find("test.txt", "Hello");
            reader.close();

        } catch (IOException e) {
            System.err.println("File write error");
            e.printStackTrace();
        }
        assertEquals(expected, res);
    }

}