package org.knit.solutions.task7;

import java.util.LinkedList;
import java.util.Queue;

public class Warehouse {
    private final int CAPACITY = 5;
    private final Queue<String> productsQueue = new LinkedList<>();

    public synchronized void addProduct(String productName) {
        while (productsQueue.size() == CAPACITY) {
            try {
                System.out.println("Производитель ждет. Склад заполнен.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        productsQueue.add(productName);
        System.out.println("Товар " + productName + " был добавлен на склад. Товаров на складе: " + productsQueue.size());
        notify();
    }

    public synchronized void buyProduct() {
        while (productsQueue.isEmpty()) {
            try {
                System.out.println("Потребитель ждет. Склад пуст.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        String product = productsQueue.poll();
        System.out.println("Товар " + product + " был взят со склада. Товаров на складе: " + productsQueue.size());
        notify();
    }
}