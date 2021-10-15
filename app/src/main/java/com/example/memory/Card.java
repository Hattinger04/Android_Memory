package com.example.memory;

public class Card {
    private boolean visible;
    private int value;

    @Override
    public String toString() {
        return "Card{" +
                "visible=" + visible +
                ", value=" + value +
                '}';
    }

    public boolean isVisible() {
        return visible;
    }

    public int getValue() {
        return value;
    }
}
