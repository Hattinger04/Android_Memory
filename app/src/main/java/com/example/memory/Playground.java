package com.example.memory;

import java.util.Arrays;

public class Playground {

    private Card[][] cards;
    // TODO: wie viel Spieler sind es? Wo abfragen?
    private int whosOnTurn;
    private int[] score;

    public Playground(int x, int y) {
        cards = new Card[x][y];

    }

    public void init() {

    }

    public Card play(Position position) {
        // TODO: kommt sehr wahrscheinlich noch was
        return getCard(position);
    }

    public boolean finished() {
        for(int i=0; i< cards.length;i++){
            for(int j=0; j<cards[i].length; j++){
                if(!cards[i][j].isVisible()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPair(Position pos1, Position pos2) {
        if(getCard(pos1).getValue() == getCard(pos2).getValue()) {
            score[whosOnTurn] = score[whosOnTurn] + 1;

            return true;
        }
        return false;
    }

    public Card getCard(Position position) {
        return cards[position.x][position.y];
    }

    private int getNrPairs() {
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
