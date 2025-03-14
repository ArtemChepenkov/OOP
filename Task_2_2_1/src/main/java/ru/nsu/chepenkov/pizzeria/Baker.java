package ru.nsu.chepenkov.pizzeria;

/**
 * Пекарь.
 *
 * @author ArtemChepenkov
 */

public class Baker implements Runnable {
    private final int bakeTime;
    private final Storage storage;
    private final OrderQueue orderQueue;
    private static int bakerCounter;
    private final int bakerId;

    public Baker(int bakeTime, Storage storage, OrderQueue orderQueue) {
        this.bakeTime = bakeTime;
        this.storage = storage;
        this.orderQueue = orderQueue;
        bakerCounter++;
        this.bakerId = bakerCounter;
    }

    @Override
    public void run() {
        try {
            while(true) {
                if (storage.needBakerFinish()) {
                    storage.bakerGoHome();
                    storage.tryClosePizzeria();
                    break;
                }
                Order curOrder = orderQueue.takeOrder();
                for (int i = 0; i < curOrder.getPizzaNumber(); i++) {
                    Thread.sleep(bakeTime);
                }
                System.out.println("Baked one pizza for " + curOrder.getParentOrderId());
                storage.putOrder(curOrder);
            }
            System.out.println("Baker " + bakerId + " stopped");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
