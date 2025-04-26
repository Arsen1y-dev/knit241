package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task9.Factory;

/*
2.9 Задача «Конвейер сборки деталей»

Описание задачи

На заводе работают три типа рабочих, каждый из которых выполняет свою часть работы в конвейерном режиме:

Штамповщик – вырезает заготовку (создает объект детали).
Сборщик – собирает из заготовки готовую деталь.
Оператор контроля качества – проверяет деталь и отправляет на склад.
Каждый рабочий – отдельный поток, и они должны работать последовательно, используя общую очередь для передачи деталей.

Требования к решению

Использовать потоки (Thread или ExecutorService).
Использовать синхронизацию (wait(), notify(), BlockingQueue).
Реализовать конвейерную передачу данных между потоками.
 */

@TaskDescription(taskNumber = 9, taskDescription = "Задача «Конвейер сборки деталей»")

public class Task9 implements Solution{

    @Override
    public void execute() {
        Factory factory = new Factory();
        factory.initProduction();
    }
}
