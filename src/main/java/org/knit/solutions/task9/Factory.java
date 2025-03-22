package org.knit.solutions.task9;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Factory {
    final private BlockingQueue<Detail> stampingQueue = new LinkedBlockingQueue<>();
    final private BlockingQueue<Detail> reassemblingQueue = new LinkedBlockingQueue<>();
    final private BlockingQueue<Detail> assemblingQueue = new LinkedBlockingQueue<>();
    final private BlockingQueue<Detail> warehouseQueue = new LinkedBlockingQueue<>();

    private Boolean isWorkFinished = false;

    public synchronized Boolean getWorkFinished() {
        return isWorkFinished;
    }

    public synchronized void setWorkFinished(Boolean workFinished) {
        isWorkFinished = workFinished;
    }

    public void initProduction() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(new Stamper(stampingQueue));
        executorService.submit(new Assembler(stampingQueue, reassemblingQueue, assemblingQueue, this));
        executorService.submit(new Operator(stampingQueue, reassemblingQueue, assemblingQueue, warehouseQueue, this));

        executorService.shutdown();
    }
}