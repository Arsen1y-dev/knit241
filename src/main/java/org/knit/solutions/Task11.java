package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task11.*;

/*
1. Паттерн Observer (Наблюдатель)
Задача 11: Реализация системы уведомлений в биржевом приложении

Вы разрабатываете приложение для биржевой торговли, где пользователи могут подписываться на обновления акций определённых компаний.

Требуется:

Создать класс Stock (акция), который хранит информацию о текущей цене и может уведомлять подписчиков об изменении цены.
Создать интерфейс StockObserver и реализовать его в классах MobileApp и EmailNotifier, которые получают уведомления при изменении цены.
Реализовать механизм подписки и отписки для StockObserver.
Пример работы:

Пользователь подписывается на акции компании "Tesla".
Когда цена акции изменяется, приложение отправляет уведомления подписчикам.
Если пользователь отписался, он больше не получает уведомления.
Дополнительно: Добавьте возможность подписки на акции нескольких компаний.
*/

@TaskDescription(taskNumber = 11, taskDescription = "Задача «Реализация системы уведомлений в биржевом приложении»")

public class Task11 implements Solution{

    @Override
    public void execute() {
        Stock teslaStock = new Stock("Tesla", 650.00);
        Stock appleStock = new Stock("Газпром", 150.00);

        MobileApp mobileApp1 = new MobileApp("User1");
        MobileApp mobileApp2 = new MobileApp("User2");
        MobileApp mobileApp3 = new MobileApp("User3");
        EmailNotifier emailNotifier1 = new EmailNotifier("ivanpetrov@mail.com");
        EmailNotifier emailNotifier2 = new EmailNotifier("alexsidorov@yandex.com");
        EmailNotifier emailNotifier3 = new EmailNotifier("elenaelena@mail.com");

        teslaStock.addObserver(mobileApp1);
        teslaStock.addObserver(emailNotifier1);
        appleStock.addObserver(mobileApp2);
        appleStock.addObserver(emailNotifier2);
        teslaStock.addObserver(mobileApp3);
        teslaStock.addObserver(emailNotifier3);

        System.out.println("\nОбновление цены акции Tesla:");
        teslaStock.setPrice(680.00);

        System.out.println("\nОбновление цены акции Газпром:");
        appleStock.setPrice(155.00);

        teslaStock.removeObserver(mobileApp1);

        System.out.println("\nПосле отписки пользователя от акции Tesla:");

        teslaStock.setPrice(700.00);
    }
}
