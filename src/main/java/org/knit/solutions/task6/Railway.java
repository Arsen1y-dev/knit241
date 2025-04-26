package org.knit.solutions.task6;

public class Railway {
    private volatile boolean isClosed = false;

    public synchronized void trainIsPassing() {
        System.out.println("Поезд приближается, шлагбаум закрывается.");
        isClosed = true;
    }

    public synchronized void trainPassed() {
        System.out.println("Поезд отдаляется, шлагбаум открывается.");
        isClosed = false;
        notifyAll();
    }

    public synchronized void passCar(String carName) {
        while (isClosed) {
            try {
                System.out.println(carName + " ждет перед переездом.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println(carName + " проехала переезд");
    }
}