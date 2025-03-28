package org.knit.solutions.task11;

public class MobileApp implements StockObserver {
    private String name;

    public MobileApp(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("Мобильное приложение: " + name + " - Новая цена акции " + stockName + ": " + price);
    }
}
