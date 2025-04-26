package org.knit.solutions;

/*
2.8 Задача «Перекресток: светофор и машины» 🚦🚗

📌 Описание:
На перекрестке светофор управляет движением:

Красный свет – машины стоят (wait()).
Зеленый свет – машины едут (notifyAll()).
Светофор переключается каждые 5 секунд.
🔹 Что нужно реализовать?
✔ Поток "Светофор" изменяет цвет и отправляет notifyAll().
✔ Потоки "Машина" ждут wait(), если красный свет.
 */

import org.knit.TaskDescription;
import org.knit.solutions.task8.Car;
import org.knit.solutions.task8.TrafficLight;

@TaskDescription(taskNumber = 8, taskDescription = "Задача «Перекресток: светофор и машины»")

public class Task8 implements Solution {

    @Override
    public void execute() {
        TrafficLight trafficLight = new TrafficLight();

        Thread threadTrafficLight = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            while (true) {
                trafficLight.changeColor();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread threadCar = new Thread(() -> {
            for (int i = 1; i <= 12; i++) {
                Car car = new Car(String.valueOf(i));
                car.pass(trafficLight);
                try {
                    Thread.sleep(900);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        threadTrafficLight.start();
        threadCar.start();
    }
}
