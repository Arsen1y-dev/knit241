package org.knit.solutions.task9;

import java.util.concurrent.BlockingQueue;

public class Stamper implements Runnable {
    private final BlockingQueue<Detail> stampingQueue;
    private int lastDetailId = 0;
    private final int DETAILS_TO_PRODUCE = 10;

    public Stamper(BlockingQueue<Detail> stampingQueue) {
        this.stampingQueue = stampingQueue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < DETAILS_TO_PRODUCE; i++) {
                Detail detail = new Detail(++lastDetailId);
                stampingQueue.put(detail);
                System.out.println("Штамповщик: Заготовка " + detail.getId() + " создана");
                Thread.sleep(1000);
            }
            stampingQueue.put(null);
            System.out.println("Штамповщик: Производство заготовок завершено");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("Штамповщик: Закончил работу.");
        }
    }
}