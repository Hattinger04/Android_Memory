package com.example.memory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void start(View view) {

        Spinner felder = findViewById(R.id.felder);
        Spinner spieler = findViewById(R.id.spieler);


        String feld = (String) felder.getSelectedItem();
        String anzSpieler = (String) spieler.getSelectedItem();


        Intent intent = new Intent(this, StartAcitvity.class);
        intent.putExtra("felder", feld);
        intent.putExtra("anzSpieler", anzSpieler);
        startActivity(intent);
    }
}
