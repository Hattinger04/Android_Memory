package com.example.memory;

import android.util.Log;

import java.util.Arrays;

public class Playground {

    private Card[][] cards;
    // TODO: User kann Anzahl an Spielern noch nicht eingeben (selber schreiben!)
    private int whosOnTurn;
    private int[] score;

    public Playground(int x, int y) {
        cards = new Card[x][y];
        init();
        Log.d("Cards", " " + cards[x-1][y-1].getValue());

    }

    // TODO: Funktioniert noch nicht? Falsches Konzept? Idee zurzeit nur noch mit geraden Arraylaengen
    public void init() {
        int value = 0;
        for(int i=0; i < cards.length;i++){
            for(int j=0; j < cards[i].length - 1 ; j = j + 2) {
                cards[i][j] = new Card();
                cards[i][j+1] = new Card();
                cards[i][j].setValue(value);
                cards[i][j+1].setValue(value);
                Log.d("Value", " " + cards[i][j].getValue() + " Value1: " + value);
                Log.d("Value", " " + cards[i][j+1].getValue() + " Value2: " + value);
                value++;
            }
            cards[i][cards[i].length - 1] = new Card();
            cards[i][cards[i].length - 1].setValue(value);
            value = (i != 0 || i % 2 == 0 ? value + 1 : value);
            Log.d("Value", " Value3: " + cards[i][cards[i].length - 1].getValue());

        }
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
       /* if(getCard(pos1).getValue() == getCard(pos2).getValue()) {
            score[whosOnTurn] = score[whosOnTurn] + 1;
            return true;
        }
        return false;
        */
        return true;
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
