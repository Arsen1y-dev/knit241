package org.knit.solutions.task13;

public class Light {
    private boolean isOn = false;

    public void turnOn() {
        isOn = true;
        System.out.println("Свет включен");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Свет выключен");
    }
}
