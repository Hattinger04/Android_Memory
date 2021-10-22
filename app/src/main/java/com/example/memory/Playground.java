package com.example.memory;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Playground {

    private Card[][] cards;
    private int whosOnTurn;
    private int[] score;

    public Playground(int x, int y) {
        cards = new Card[x][y];
        whosOnTurn = 0;
        init();
    }

    public void init() {
        List<Integer> liste = new ArrayList<Integer>();
        int zaehler = 0;
        int count = cards.length * cards[0].length;
        for(int i=0; i < count; i = i + 2) {
            liste.add(zaehler);
            liste.add(zaehler);
            zaehler++;
        }

        Collections.shuffle(liste);

        for(int i=0; i < cards.length;i++){
            for(int j=0; j < cards[i].length; j++) {
                cards[i][j] = new Card();
                int value = liste.get(0);
                liste.remove(0);
                cards[i][j].setValue(value);
            }
        }

    }

    public void switchPlayer() {
        if(whosOnTurn >= score.length - 1) {
            whosOnTurn = 0;
        } else {
            whosOnTurn++;
        }
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
            getCard(pos1).setVisible(true);
            getCard(pos2).setVisible(true);
            return true;
        }
        return false;

    }

    public Card getCard(Position position) {
        return cards[position.x][position.y];
    }

    public void setScore(int[] score) {
        this.score = score;
    }

    /**
     * Methode soll ausgefuehrt werden, wenn man 2 verschiedene
     * Karten aufgedeckt hat
     *
     * Gibt Enum "Status" Wert zurueck
     *
     * @param card
     * @param previousCard
     */
    public Status play(Position card, Position previousCard) {
        switchPlayer();
        if(isPair(card, previousCard)) {
            if(finished()) {
                return Status.finished;
            }
            return Status.isPair;
        }
        return Status.isNothing;
    }

    @Override
    public String toString() {
        return "Playground{" +
                "cards=" + Arrays.toString(cards) +
                ", whosOnTurn=" + whosOnTurn +
                ", score=" + Arrays.toString(score) +
                '}';
    }

    public HashMap<Integer, Integer> getWinner() {
        HashMap rangliste = new HashMap<String, Integer>();
        for(int i = 0; i < score.length; i++) {
            rangliste.put(i + 1, score[i]);
        }

        List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(rangliste.entrySet());

        // Sortiere die Liste
        Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

        // Fuege Daten in neue HashMap ein
        LinkedHashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public void reset() {
        init();
        for(int i = 0; i < score.length; i++) {
            score[i] = 0;
        }
        whosOnTurn = 0;
    }
}
