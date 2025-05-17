package org.knit.solutions.task9;

import java.util.concurrent.BlockingQueue;

public class Assembler implements Runnable {
    private final BlockingQueue<Detail> stampingQueue;
    private final BlockingQueue<Detail> reassemblingQueue;
    private final BlockingQueue<Detail> assemblingQueue;
    private final Factory factory;

    public Assembler(
            BlockingQueue<Detail> stampingQueue,
            BlockingQueue<Detail> reassemblingQueue,
            BlockingQueue<Detail> assemblingQueue,
            Factory factory
    ) {
        this.stampingQueue = stampingQueue;
        this.reassemblingQueue = reassemblingQueue;
        this.assemblingQueue = assemblingQueue;
        this.factory = factory;
    }

    @Override
    public void run() {
        while (!factory.getWorkFinished()) {
            try {
                Detail detail = null;
                if (!stampingQueue.isEmpty()) {
                    detail = stampingQueue.take();
                } else if (!reassemblingQueue.isEmpty()) {
                    detail = reassemblingQueue.take();
                }

                if (detail != null) {
                    Thread.sleep(1500);
                    assemblingQueue.put(detail);
                    System.out.println("Сборщик собрал деталь " + detail.getId());
                } else {
                    Thread.sleep(100);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("Сборщик закончил работу.");
    }
}