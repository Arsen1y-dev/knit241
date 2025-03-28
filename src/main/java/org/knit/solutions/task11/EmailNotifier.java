package org.knit.solutions.task11;

public class EmailNotifier implements StockObserver {
    private String email;

    public EmailNotifier(String email) {
        this.email = email;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("Отправлено на email " + email + ": новая цена акции " + stockSymbol + " - " + price);
    }
}
