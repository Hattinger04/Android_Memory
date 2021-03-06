package com.example.memory;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

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

    private int[] feld;
    private int anzSpieler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_memory);
        getExtras();
        pics = getPicsArray();
        field = new Playground(feld[0],feld[1]);
        field.setScore(new int[anzSpieler]);
        buttons = new ImageButton[feld[0]][feld[1]];
        generateGrid(feld[0],feld[1]);
    }

    public void getExtras() {
        Bundle extras = getIntent().getExtras();
        feld = new int[2];
        if(extras != null) {
            anzSpieler = Integer.parseInt(extras.getString("anzSpieler"));
            feld[0] = Integer.parseInt(extras.getString("felder").substring(0,1));
            feld[1] = Integer.parseInt(extras.getString("felder").substring(2,3));
            return;
        }
        throw new RuntimeException("Sollte nicht passieren!");
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
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    if (buttons[i][j].equals(button)) {
                        pos = new Position(i, j);
                    }
                }
            }
            if (pos == null) {
                throw new RuntimeException("Darf nicht passieren");
            }
            if (field.getCard(pos).isVisible()) {
                return;
            }

            button.setImageResource(pics[field.getCard(pos).getValue()]);

            if (previousCard != null) {
                field.switchPlayer();
                setAcitiveButtons(false);

                switch (field.play(pos, previousCard)) {
                    case finished:
                        gameFinished();
                        return;
                    case isPair:
                        setAcitiveButtons(true);
                        break;
                    case isNothing:
                        closeCards(pos, previousCard);
                        break;
                }
                previousCard = null;
            } else {
                previousCard = pos;
            }
        }

    private void setAcitiveButtons(boolean active) {
        for(int i = 0; i < buttons.length; i++) {
            for(int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setClickable(active);
            }
        }
    }

    private void closeCards(Position pos1, Position pos2) {
        class CloseTask extends TimerTask
        {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    buttons[pos1.x][pos1.y].setImageResource(R.drawable.back);
                    buttons[pos2.x][pos2.y].setImageResource(R.drawable.back);
                    setAcitiveButtons(true);
                });
            }
        }
        Timer timer = new Timer();
        timer.schedule(new CloseTask(),1000);
    }

    public void gameFinished() {
        View root = findViewById(R.id.root);
        Object[] player = field.getWinner().keySet().toArray();
        Object[] values = field.getWinner().values().toArray();
        boolean remis = values[values.length - 1] == values[values.length - 2];

        if(remis) {
            Snackbar.make(root, "Unentschieden!", Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(root, "Gewonnen hat Spieler: " + player[player.length-1] + " mit " + values[values.length-1] + " Punkten!", Snackbar.LENGTH_LONG).show();
        }
        return;
    }
}
