package ru.nsu.chepenkov.snake;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TestMain {

    @Test
    void testMain() {
        Thread mainThread = new Thread(() -> {
            try {
                String[] args = new String[]{};
                Main.main(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        mainThread.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 5000);
        try {
            mainThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
