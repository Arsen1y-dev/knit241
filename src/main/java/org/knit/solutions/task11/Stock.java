package org.knit.solutions.task11;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private String name;
    private double price;
    private List<StockObserver> observers;

    public Stock(String symbol, double price) {
        this.name = symbol;
        this.price = price;
        this.observers = new ArrayList<>();
    }

    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (StockObserver observer : observers) {
            observer.update(name, price);
        }
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}