package at.ac.htlinn.androidexamples.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import at.ac.htlinn.androidexamples.R;

/**
 * This Activity should serve as a starting point for Android development
 *
 * This demo shows:
 * * General setup of an Activity
 * * how to deal with a spinner component
 * * Usage and customization of linear layout
 */
public class SimpleCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_calculator);
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void calculate(View view) {
            double res = 0;
            Spinner operSpinner = findViewById(R.id.oper);
            if(((TextView)findViewById(R.id.op1)).getText().toString().equals("") || ((TextView)findViewById(R.id.op2)).getText().toString().equals("")) {
                Snackbar.make(findViewById(R.id.oper), "Bitte gebe eine Zahl ein!", Snackbar.LENGTH_LONG).show();
                return;
            }
            double op1 = Double.parseDouble(((TextView)findViewById(R.id.op1)).getText().toString());
            double op2 = Double.parseDouble(((TextView)findViewById(R.id.op2)).getText().toString());
            String  oper = (String)operSpinner.getSelectedItem();
            switch (oper)
            {
                case "+": res = op1 + op2; break;
                case "-": res = op1 - op2; break;
                case "*": res = op1 * op2; break;
                case "/":
                    if(op2 == 0) {
                        Snackbar.make(findViewById(R.id.oper), "Du darfst nicht durch 0 dividieren!", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    res = op1 / op2;
                    break;
                case "^": res = Math.pow(op1, op2); break;
                case "√":
                    if(op1 <= 0 || op2 <= 0) {
                        Snackbar.make(findViewById(R.id.oper), "Nur natürliche Zahlen ohne die Null!", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    res = Math.round(Math.pow(op1, 1 / op2));
                    break;
            }
            TextView all = findViewById(R.id.textView2);
            all.append(op1 + " " + oper + " " + op2 + " = " + res + "\n");
            TextView resV = findViewById(R.id.result);
            resV.setText(res +"");

    }
}