package com.example.memory;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Timer;
import java.util.TimerTask;

public class MemoryActivity extends AppCompatActivity implements View.OnClickListener{

    private int[] pics;
    private Playground field;
    private Position previousCard;
    private ImageButton[][] buttons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_memory);
        pics = getPicsArray();
        field = new Playground(6,5);
        buttons = new ImageButton[6][5];
        generateGrid(6, 5);
    }

    private void generateGrid(int nrRows, int nrCols) {
        TableLayout playField = findViewById(R.id.playfield);
        for(int i=0; i < nrRows;i++) {
                playField.addView(generateAndAddRows(i, nrCols));
        }
    }

    private TableRow generateAndAddRows(int rows, int nrCols) {
        TableRow.LayoutParams tableRowParams=
                new TableRow.LayoutParams
                        (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);

        TableRow tr = new TableRow(this);
        tr.setLayoutParams(tableRowParams);
        for(int i = 0; i < nrCols; i++) {
            tr.addView(generateButton(new Position(rows, i)));
        }
        return tr;
    }

    private ImageButton generateButton(Position position) {
        ImageButton button = new ImageButton(this);
        button.setImageResource(R.drawable.back);
        button.setTag(position);
        button.setOnClickListener(this);
        buttons[position.x][position.y] = button;
        Log.d("Pos", position.x + " " + position.y);
        return button;
    }

    public static int[] getPicsArray() {
        int[] c = new int[21];

        c[0] = R.drawable.i000;
        c[1] = R.drawable.i001;
        c[2] = R.drawable.i002;
        c[3] = R.drawable.i003;
        c[4] = R.drawable.i004;
        c[5] = R.drawable.i005;
        c[6] = R.drawable.i006;
        c[7] = R.drawable.i007;
        c[8] = R.drawable.i008;
        c[9] = R.drawable.i009;
        c[10] = R.drawable.i010;
        c[11] = R.drawable.i011;
        c[12] = R.drawable.i012;
        c[13] = R.drawable.i013;
        c[14] = R.drawable.i014;
        c[15] = R.drawable.i015;
        c[16] = R.drawable.i016;
        c[17] = R.drawable.i017;
        c[18] = R.drawable.i018;
        c[19] = R.drawable.i019;
        c[20] = R.drawable.i020;
        return c;
    }

    @Override
    public void onClick(View view) {
        ImageButton button = (ImageButton) view;
        Position pos = null;
        for(int i=0; i< buttons.length;i++){
            for(int j=0; j<buttons[i].length; j++){
               if(buttons[i][j].equals(button)) {
                   pos = new Position(i, j);
               }
            }
        }
        if(pos == null) {
            throw new RuntimeException("Darf nicht passieren");
        }


        button.setImageResource(pics[field.getCard(pos).getValue()]);

        if(previousCard != null) {
            if(field.isPair(pos, previousCard)) {
                if(field.finished()) {
                    View root = findViewById(R.id.root);
                    Snackbar.make(root, "Das Spiel ist aus!", Snackbar.LENGTH_LONG).show();
                    return;
                }
            } else {
                closeCards(pos, previousCard);
            }
            previousCard = null;
        } else {
            previousCard = pos;
        }


    }

    private void closeCards(Position pos1, Position pos2)
    {
        class CloseTask extends TimerTask
        {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    buttons[pos1.x][pos1.y].setImageResource(R.drawable.back);
                    buttons[pos2.x][pos2.y].setImageResource(R.drawable.back);
                });
            }
        }

        Timer timer = new Timer();
        timer.schedule(new CloseTask(),1000);
    }


}
