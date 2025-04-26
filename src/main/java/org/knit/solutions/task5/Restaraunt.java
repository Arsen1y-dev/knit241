package org.knit.solutions.task5;

import java.util.LinkedList;
import java.util.Queue;

public class Restaraunt {
    private final int CAPACITY = 3;
    private final Queue<String> mealsQueue = new LinkedList<>();

    public synchronized void cook(String name) {
        while (mealsQueue.size() >= CAPACITY) {
            try {
                System.out.println("Повар ждет, пока официант заберет блюдо. Поднос полон.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        mealsQueue.add(name);
        System.out.println("Повар приготовил блюдо " + name + ". На подносе: " + mealsQueue.size() + " блюд.");
        notify();
    }

    public synchronized void serve() {
        while (mealsQueue.isEmpty()) {
            try {
                System.out.println("Официант ждет блюдо. Поднос пуст.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        String meal = mealsQueue.poll();
        System.out.println("Официант подал блюдо " + meal + ". На подносе осталось: " + mealsQueue.size() + " блюд.");
        notify();
    }
}