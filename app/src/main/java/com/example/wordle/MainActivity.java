package com.example.wordle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_add;
    Button btn_save,btn_move_to_game,btn_see_words;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    int number_of_words;
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_add = findViewById(R.id.et_add);
        btn_see_words = findViewById(R.id.btn_see_words);
        btn_save = findViewById(R.id.btn_save);
        btn_move_to_game = findViewById(R.id.btn_move_to_game);
        btn_save.setOnClickListener(this);
        btn_move_to_game.setOnClickListener(this);
        btn_see_words.setOnClickListener(this);

        sp = getSharedPreferences("words", MODE_PRIVATE);
        editor = sp.edit();
        number_of_words=sp.getInt("number_of_words",0);
    }

    @Override
    public void onClick(View view) {
        if (view==btn_save){
            if (et_add.getText().toString().length()==5){
                number_of_words+=1;
                editor.putInt("number_of_words", number_of_words);
                String word = et_add.getText().toString();
                editor.putString((number_of_words+""), word);
                editor.commit();
                et_add.setText("");
            }
        }
        if (view==btn_move_to_game){
            it = new Intent(this, GameActivity.class);
            startActivity(it);
        }
        if (view==btn_see_words){
            it = new Intent(this, SeeWordsActivity.class);
            startActivity(it);
        }
    }
}