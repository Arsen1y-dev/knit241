package org.knit.solutions.task13;

public class TV {
    private boolean isOn = false;

    public void turnOn() {
        isOn = true;
        System.out.println("Телевизор включен");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Телевизор выключен");
    }
}
