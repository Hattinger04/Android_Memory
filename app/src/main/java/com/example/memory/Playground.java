package com.example.memory;

import java.util.Arrays;

public class Playground {

    private Card[][] cards;
    private int whosOnTurn;
    private int[] score;

    public Playground(int x, int y) {

    }

    public void init() {

    }

    public Card play(Position position) {
        return getCard(position);
    }

    public boolean finished() {
        // TODO: Array durchlaufen? visible abfragen
        return false;
    }

    public boolean isPair(Position pos1, Position pos2) {
        if(getCard(pos1).equals(getCard(pos2))) {
            return true;
        }
        return false;
    }

    public Card getCard(Position position) {
        return cards[position.x][position.y];
    }

    public int getNrPairs() {
        // TODO: Funktioniert dies bei 2 Dimensionalem Array gleich?
        return cards.length / 2;
    }

    @Override
    public String toString() {
        return "Playground{" +
                "cards=" + Arrays.toString(cards) +
                ", whosOnTurn=" + whosOnTurn +
                ", score=" + Arrays.toString(score) +
                '}';
    }
}
