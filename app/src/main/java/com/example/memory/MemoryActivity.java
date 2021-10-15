package com.example.memory;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MemoryActivity extends AppCompatActivity {

    private int[] pics;
    private Playground field;
    private Position previousCard;
    private ImageButton[][] buttons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void generateGrid(int nrCols, int nrRows) {

    }

    private void generateAndAddRows(int rows, int nrCols) {

    }

    private ImageButton generateButton(Position position) {
        return null;
    }

    private int[] getPicsArray() {
        return null;
    }

    public void onClick(View view) {

    }

    public void closeCards(Position pos1, Position pos2) {

    }
}
