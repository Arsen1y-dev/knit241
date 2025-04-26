package org.knit.solutions.task8;

public class TrafficLight {
    private boolean isRed = true; // Изначально красный свет

    public synchronized boolean isRed() {
        return isRed;
    }

    public synchronized void waitForGreen() {
        while (isRed) {
            try {
                System.out.println("Машина ждет зеленый свет.");
                wait(); // Поток машины ждет, пока светофор красный
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public synchronized void changeColor() {
        System.out.println("Смена цвета. Текущий isRed: " + isRed); // Отладочный вывод
        isRed = !isRed; // Переключаем цвет (красный <-> зеленый)
        System.out.println((isRed ? "Красный" : "Зеленый") + " свет включен.");
        notifyAll(); // Оповещаем все ждущие потоки машин о смене цвета
    }
}