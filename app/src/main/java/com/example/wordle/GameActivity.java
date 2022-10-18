package com.example.wordle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    TextView[][] textViewArray = new TextView[4][5];
    LinearLayout llMain;
    EditText et_words;
    String word;
    SharedPreferences sp;
    Button btn_submit;
    Random rand = new Random();
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    int row = 0;
    boolean didntWin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        llMain = findViewById(R.id.llMain);
        lp.setMargins(20, 20, 20, 20);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        for (int i = 0; i < textViewArray.length; i++) {
            LinearLayout llTemp = new LinearLayout(this);
            llTemp.setLayoutParams(lp);
            llTemp.setGravity(Gravity.CENTER_HORIZONTAL);
            llMain.addView(llTemp);
            for (int k = 0; k < textViewArray[i].length; k++) {
                textViewArray[i][k] = new TextView(this);
                textViewArray[i][k].setLayoutParams(lp);
                llTemp.addView(textViewArray[i][k]);
                textViewArray[i][k].setText("_");

            }
        }
        et_words = findViewById(R.id.et_words);
        sp = getSharedPreferences("words", MODE_PRIVATE);
        int number_of_words = sp.getInt("number_of_words", 0);
        int random = rand.nextInt(number_of_words + 1);
        word = sp.getString((random + ""), "nadav");

    }

    @Override
    public void onClick(View view) {
        if (view == btn_submit && row < textViewArray.length && didntWin) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            String guess = et_words.getText().toString();
            if (guess.length() == 5) {
                for (int i = 0; i <= 4; i++) {
                    textViewArray[row][i].setTextSize(25);
                    textViewArray[row][i].setText("" + guess.charAt(i));
                    if (word.indexOf(guess.charAt(i)) > -1) {
                        textViewArray[row][i].setBackgroundColor(Color.YELLOW);
                    }
                    if (("" + guess.charAt(i)).equals(word.charAt(i) + "")) {
                        textViewArray[row][i].setBackgroundColor(Color.GREEN);
                    }
                    et_words.setText("");


                }
                if (word.equals(guess) || row == textViewArray.length-1) {
                    didntWin = false;
                    TextView win = new TextView(this);
                    win.setLayoutParams(lp);
                    llMain.addView(win);
                    win.setTextColor(Color.BLUE);
                    win.setTextSize(40);

                    if (word.equals(guess)) {
                        win.setText("you won");
                    }
                    else {
                        win.setText("you lost " + "\""+ word+"\"");
                    }


                }
                row += 1;

            }


        }


    }
}

