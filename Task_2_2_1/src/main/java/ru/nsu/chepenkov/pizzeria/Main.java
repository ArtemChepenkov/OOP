package ru.nsu.chepenkov.pizzeria;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Thread.sleep;

/**
 * Главный класс для запуска.
 *
 * @author ArtemChepenkov
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        int bakerNumber = 0;
        int bakeTime = 0;
        int courierNumber = 0;
        int deliveryTime = 0;
        int trunkCapacity = 0;
        int storageCapacity = 0;
        try {
            JsonNode rootNode = objectMapper.readTree(new File("data.json"));

            JsonNode bakerNode = rootNode.get("baker");
            bakeTime = bakerNode.get("bakeTime").asInt();
            bakerNumber = bakerNode.get("bakerNumber").asInt();

            JsonNode courierNode = rootNode.get("courier");
            deliveryTime = courierNode.get("deliveryTime").asInt();
            courierNumber = courierNode.get("courierNumber").asInt();
            trunkCapacity = courierNode.get("trunkCapacity").asInt();

            JsonNode storageNode = rootNode.get("storage");
            storageCapacity = storageNode.get("storageCapacity").asInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Storage storage = new Storage(storageCapacity);
        OrderQueue orderQueue = new OrderQueue();
        List<Thread> bakers = new ArrayList<>();
        List<Thread> couriers = new ArrayList<>();
        storage.setBakerNumber(bakerNumber);

        for (int i = 0; i < bakerNumber; i++) {
            bakers.add(new Thread(new Baker(bakeTime, storage, orderQueue)));
        }

        for (int i = 0; i < courierNumber; i++) {
            couriers.add(new Thread(new Courier(deliveryTime, storage, trunkCapacity)));
        }

        for (int i = 0; i < 20; i++) {
            orderQueue.addOrder(new Order(2, i+1));
        }

        for (int i = 0; i < bakerNumber; i++) {
            bakers.get(i).start();
        }

        for (int i = 0; i < courierNumber; i++) {
            couriers.get(i).start();
        }
        sleep(10000);
        storage.closePizzeria();
    }
}


















