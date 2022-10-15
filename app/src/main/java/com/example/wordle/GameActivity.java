package com.example.wordle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    TextView[][] textViewArray = new TextView[5][6];
    EditText et_words;
    TextView tv_answer;
    String word;
    SharedPreferences sp;
    Button btn_submit;
    Random rand = new Random();
    int row =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btn_submit= findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        textViewArray[1][1] = findViewById(R.id.tv1_1);
        textViewArray[1][2] = findViewById(R.id.tv1_2);
        textViewArray[1][3] = findViewById(R.id.tv1_3);
        textViewArray[1][4] = findViewById(R.id.tv1_4);
        textViewArray[1][5] = findViewById(R.id.tv1_5);

        textViewArray[2][1] = findViewById(R.id.tv2_1);
        textViewArray[2][2] = findViewById(R.id.tv2_2);
        textViewArray[2][3] = findViewById(R.id.tv2_3);
        textViewArray[2][4] = findViewById(R.id.tv2_4);
        textViewArray[2][5] = findViewById(R.id.tv2_5);

        textViewArray[3][1] = findViewById(R.id.tv3_1);
        textViewArray[3][2] = findViewById(R.id.tv3_2);
        textViewArray[3][3] = findViewById(R.id.tv3_3);
        textViewArray[3][4] = findViewById(R.id.tv3_4);
        textViewArray[3][5] = findViewById(R.id.tv3_5);

        textViewArray[4][1] = findViewById(R.id.tv4_1);
        textViewArray[4][2] = findViewById(R.id.tv4_2);
        textViewArray[4][3] = findViewById(R.id.tv4_3);
        textViewArray[4][4] = findViewById(R.id.tv4_4);
        textViewArray[4][5] = findViewById(R.id.tv4_5);

        et_words = findViewById(R.id.et_words);
        tv_answer = findViewById(R.id.tv_answer);
        sp = getSharedPreferences("words", MODE_PRIVATE);
        int number_of_words=sp.getInt("number_of_words",0);
        int random = rand.nextInt(number_of_words+1);
        word =sp.getString((random+""),"nadav");

        tv_answer.setText(word);

    }

    @Override
    public void onClick(View view) {
        if (view==btn_submit&&row<5){
            String guess = et_words.getText().toString();
            if(guess.length()==5){
                for (int i = 1; i <=5 ; i++) {
                    textViewArray[row][i].setTextSize(25);
                    textViewArray[row][i].setText(""+guess.charAt(i-1));
                    if(word.indexOf(guess.charAt(i-1))>-1){
                        textViewArray[row][i].setBackgroundColor(Color.YELLOW);
                    }
                    if ( (""+guess.charAt(i-1)).equals(word.charAt(i-1)+"")){
                        textViewArray[row][i].setBackgroundColor(Color.GREEN);
                    }
                    et_words.setText("");


                }
                if (word.equals(guess)){
                    tv_answer.setText("u wonnnnnnnnnnnn");

                }
                row+=1;

            }


        }
    }
}
