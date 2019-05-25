package com.example.helpdeath2;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText correct, guess;
    TextView result;
    Button submit, check;
    int x = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correct = (EditText) findViewById(R.id.correct_age);
        guess = (EditText) findViewById(R.id.guess);
        result = (TextView) findViewById(R.id.result);
        submit = (Button) findViewById(R.id.button);
        check = (Button) findViewById(R.id.button2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int correctage = getAge();

                Toast.makeText(getApplicationContext(),"Correct age saved",Toast.LENGTH_LONG).show();

            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int correctage = getAge();
                SharedPreferences sh = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sh.edit();
                editor.putInt(getString(R.string.app_name), correctage);
                editor.apply();
                int z = sh.getInt("Help Death 2", correctage);
                int guess = getGuess();
                compare(z,guess);
            }
        });

    }


    public int getAge(){
    String corr = correct.getText().toString();
    int a = Integer.parseInt(corr);
    return a;
    }

    public int getGuess(){
        String corr = guess.getText().toString();
        int a = Integer.parseInt(corr);
        return a;
    }

    public void compare(int ca, int ga){
        String resultlabel = "";
        if(ga>ca){
            resultlabel = "Your guess was wrong and it was more than correct age";
            result.setText(resultlabel);
            guess.setText("");

        }
        if(ga<ca){
            resultlabel = "Your guess was wrong and it was less than correct age";
            result.setText(resultlabel);
            guess.setText("");


        }
        if(ga==ca){
            resultlabel = "Your guess was correct";
            result.setText(resultlabel);
            guess.setText("");

        }

        if(ga!=ca){
            x++;
            SharedPreferences sh = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putInt(getString(R.string.app_name), x);
            editor.apply();
            int y = sh.getInt(getString(R.string.app_name), x);
            if(y>4){
                Toast.makeText(getApplicationContext(),"Game over for you, you failed " + x + " times",Toast.LENGTH_LONG).show();
                y=0;
                guess.setText("");
                correct.setText("");
                result.setText("Result will be shown here");
            }
        }
    }

}
