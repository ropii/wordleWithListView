package com.example.wordle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SeeWordsActivity extends AppCompatActivity {

    SharedPreferences sp;
    ArrayList<Words> wordsArLs= new ArrayList<>();
    ListView lvWords;
    WordsAdapter wordsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_words);
        lvWords = findViewById(R.id.lvWords);
        createArLs();
        createAdapter();


    }

    private void createAdapter() {
        sp = getSharedPreferences("words", MODE_PRIVATE);
        int number_of_words=sp.getInt("number_of_words",0);
        for (int i = 1; i <=number_of_words ; i++) {
            String word =sp.getString((i+""),"nadav");
            wordsArLs.add(new Words(sp.getString((i+""),"")));

        }

    }

    private void createArLs() {
        wordsAdapter=new WordsAdapter(this,0,0, wordsArLs);
        lvWords.setAdapter(wordsAdapter);
    }
}