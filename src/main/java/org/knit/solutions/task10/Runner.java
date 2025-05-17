package org.knit.solutions.task10;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class Runner implements Runnable {
    private final int number;
    private final CyclicBarrier startLine;
    private final CountDownLatch finishLine;

    public Runner(int number, CyclicBarrier startLine, CountDownLatch finishLine) {
        this.number = number;
        this.startLine = startLine;
        this.finishLine = finishLine;
    }

    @Override
    public void run() {
        try {
            System.out.println("Бегун " + number + " готов к старту.");
            startLine.await();

            int runningTime = ThreadLocalRandom.current().nextInt(500, 3000);
            Thread.sleep(runningTime);

            System.out.println("Бегун " + number + " финишировал за " + runningTime + " мс.");
            finishLine.countDown();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}



