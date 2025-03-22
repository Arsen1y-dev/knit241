package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task10.Runner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Задача 10: Гонка бегунов с использованием CyclicBarrier 🏃‍♂️🏃‍♀️
        📌 Описание
Группа спортсменов участвует в забеге. Однако перед началом гонки все должны собраться на старте. Как только все участники будут готовы, забег начнётся одновременно. Используйте CyclicBarrier, чтобы синхронизировать запуск гонки.

Каждый бегун стартует одновременно, затем бежит разное время (симулируется Thread.sleep), после чего финиширует. Как только все бегуны завершат дистанцию, программа выводит сообщение о завершении гонки.

🎯 Требования к задаче:
Создать CyclicBarrier для синхронизации начала забега.
Реализовать класс Runner, который будет выполнять следующую логику в потоке:
Ожидание старта (использование barrier.await()).
Симуляция бега (Thread.sleep(randomTime)).
Вывод сообщения о финише.
После финиша всех участников программа должна сообщить, что гонка завершена.
Количество бегунов передаётся в аргументах командной строки или задаётся константой.
*/

@TaskDescription(taskNumber = 10, taskDescription = "Задача «Гонка бегунов с использованием CyclicBarrier»")

public class Task10 implements Solution{

    @Override
    public void execute() {
        final int NUMBER_OF_RUNNERS = 7;
        CyclicBarrier startLine = new CyclicBarrier(NUMBER_OF_RUNNERS, () -> System.out.println("Гонка началась!"));
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_RUNNERS);
        CountDownLatch finishLine = new CountDownLatch(NUMBER_OF_RUNNERS);

        for (int i = 0; i < NUMBER_OF_RUNNERS; i++) {
            executorService.submit(new Runner(i + 1, startLine, finishLine));
        }

        try {
            finishLine.await();
            System.out.println("Гонка завершена!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}