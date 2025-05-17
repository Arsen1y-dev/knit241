package org.knit.solutions.task9;


import java.util.concurrent.BlockingQueue;

public class Operator implements Runnable {
    private final double PR = 0.3;
    private final BlockingQueue<Detail> stampingQueue;
    private final BlockingQueue<Detail> reassemblingQueue;
    private final BlockingQueue<Detail> assemblingQueue;
    private final BlockingQueue<Detail> warehouseQueue;
    private final Factory factory;

    public Operator(
            BlockingQueue<Detail> stampingQueue,
            BlockingQueue<Detail> reassemblingQueue,
            BlockingQueue<Detail> assemblingQueue,
            BlockingQueue<Detail> warehouseQueue,
            Factory factory
    ) {
        this.stampingQueue = stampingQueue;
        this.reassemblingQueue = reassemblingQueue;
        this.assemblingQueue = assemblingQueue;
        this.warehouseQueue = warehouseQueue;
        this.factory = factory;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Detail detail = assemblingQueue.take();
                Thread.sleep(2000);
                if (Math.random() > PR) {
                    warehouseQueue.put(detail);
                    System.out.println("Оператор проверил деталь " + detail.getId() + " и отправил ее на склад");
                } else {
                    reassemblingQueue.put(detail);
                    System.out.println("Оператор проверил деталь " + detail.getId() + " и отправил ее на доработку");
                }

                if (factory.getWorkFinished() && stampingQueue.isEmpty() && assemblingQueue.isEmpty() && reassemblingQueue.isEmpty()) {
                    System.out.println("Оператор закончил работу и ушел домой.");
                    break;
                }


            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}