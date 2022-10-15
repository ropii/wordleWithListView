package com.example.wordle;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class WordsAdapter extends ArrayAdapter<Words> {

    Context context;
    List<Words> objects;

    public WordsAdapter(Context context, int resource, int textViewResourceId, List<Words> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.objects = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.activity_list_view,parent,false);

        TextView tvWord = view.findViewById(R.id.tvWord);
        Words temp = objects.get(position);
        tvWord.setText(temp.getWord());



        return view;









}








}
