package ru.nsu.chepenkov.pizzeria;

/**
 * Курьер.
 *
 * @author ArtemChepenkov
 */

public class Courier implements Runnable {
    private final int deliveryTime;
    private final int trunkCapacity;
    private final Storage storage;
    private static int courierCounter;
    private final int courierId;

    /**Конструктор.*/
    public Courier(int deliveryTime, Storage storage, int trunkCapacity) {
        this.deliveryTime = deliveryTime;
        this.storage = storage;
        this.trunkCapacity = trunkCapacity;
        courierCounter++;
        this.courierId = courierCounter;
    }

    /**Собственно, наш поток.*/
    @Override
    public void run() {
        try {
            while (true) {
                //курьеры уходят, когда пекари заканчивают работу и закрывают склад
                //ещё передавать функ интерфейс, чтобы проверять закрытость скалда
                int curTrunkCapacity = trunkCapacity;
                int flag = 0;
                while (curTrunkCapacity > 0) {
                    Order order = storage.takeOrder();
                    if (order == null) {
                        flag = 1;
                        break;
                    }
                    curTrunkCapacity--;

                }
                Thread.sleep(deliveryTime);
                System.out.println("delivered " + (trunkCapacity - curTrunkCapacity) + " pizzas");
                if(flag == 1) {
                    break;
                }
            }
            System.out.println("Courier " + courierId + " stopped");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
