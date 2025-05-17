package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task17.MontyHallParadoxGame;

/*
 Описание:

Парадокс Монти Холла — это задача из теории вероятностей, основанная на популярном шоу "Let's Make a Deal".
Правила такие:

Участник выбирает одну из трёх дверей.
Ведущий, который знает, что за дверями, открывает одну из оставшихся дверей, за которой нет приза.
Участнику предлагается либо сменить выбор на оставшуюся дверь, либо оставить свой выбор неизменным.
📌 Вопрос:

Реализуйте симуляцию этой игры на Java, чтобы проверить, что лучше: менять выбор или оставаться при своём?.
Программа должна провести большое количество экспериментов (например, 1000000) и показать вероятность выигрыша в каждом из двух случаев.
📌 Ожидаемый результат:

При достаточно большом количестве игр (например, 1 миллион):

Вероятность выигрыша при переключении: около 66.66% (или 2/3).
Вероятность выигрыша без переключения: около 33.33% (или 1/3).
 */

@TaskDescription(taskNumber = 17, taskDescription = "Реализация и проверка парадокса Монти Холла")
public class Task17 implements Solution {
    final int TOTAL_GAMES = 1_000_000;

    @Override
    public void execute() {
        MontyHallParadoxGame game = new MontyHallParadoxGame();
        double winsCountWithoutChange = 0;
        double winsCountWithChange = 0;

        for (int i = 0; i < TOTAL_GAMES; i++) {
            game.play();
            winsCountWithoutChange += game.isDidNotChangedChoiceWon() ? 1 : 0;
            winsCountWithChange += game.isDidChangedChoiceWon() ? 1 : 0;
        }

        System.out.println("Вероятность победить не меняя выбор: " + winsCountWithoutChange / TOTAL_GAMES);
        System.out.println("Вероятность победить меняя выбор: " + winsCountWithChange / TOTAL_GAMES);
    }
}