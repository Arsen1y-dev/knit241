package org.knit.solutions;

/*
2.7 Задача «Производитель-Потребитель с ограничением» 🏭📦

📌 Описание:

Производитель создает товары (максимум 5).
Потребитель забирает товары.
Если товаров нет, потребитель ждет (wait()).
Если товаров максимум, производитель ждет (wait()).
🔹 Что нужно реализовать?
✔ wait() – если товаров нет или склад заполнен.
✔ notify() – пробуждение потока, когда изменяется состояние склада.
 */

import org.knit.solutions.task7.Warehouse;
import org.knit.TaskDescription;

@TaskDescription(taskNumber = 7, taskDescription = "Задача «Производитель-Потребитель с ограничением»")

public class Task7 implements Solution{

    @Override
    public void execute() {
        Warehouse warehouse = new Warehouse();

        Thread threadManufacturer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                warehouse.addProduct(String.valueOf(i));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadBuyer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                warehouse.buyProduct();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadManufacturer.start();
        threadBuyer.start();
    }
}
