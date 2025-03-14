package ru.nsu.chepenkov.pizzeria;
import ru.nsu.chepenkov.pizzeria.Storage;

public class Courier implements Runnable {
    private final int deliveryTime;
    private final int trunkCapacity;
    private final Storage storage;
    private static int courierCounter;
    private final int courierId;

    public Courier(int deliveryTime, Storage storage, int trunkCapacity) {
        this.deliveryTime = deliveryTime;
        this.storage = storage;
        this.trunkCapacity = trunkCapacity;
        courierCounter++;
        this.courierId = courierCounter;
    }

    //TODO: сделать так, чтобы курьер ещё полагался на вместимость багажника
    @Override
    public void run() {
        try {
            while (storage.isOpened()) {
                int curTrunkCapacity = trunkCapacity;
                while (curTrunkCapacity > 0 && storage.isOpened()) {
                    Order order = storage.takeOrder();
                    curTrunkCapacity--;
                }
                Thread.sleep(deliveryTime);
                System.out.println("delivered " + (trunkCapacity - curTrunkCapacity) + " pizzas");
            }
            System.out.println("Courier " + courierId + " stopped");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
