package com.pch.bottom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import parser.Parser;
import vo.FavoriteVO;

public class FavoritesActivity extends AppCompatActivity {

    ListView myListView;
    ArrayList<FavoriteVO> list;
    FavoriteAdapter adapter;
    FavoriteVO vo;
    Parser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);



        myListView = findViewById(R.id.myListView);

        list = new ArrayList<FavoriteVO>();

        parser = new Parser();


    }//OnCreate

    class FavoriteAsync extends AsyncTask<Void, Void, ArrayList<FavoriteVO>>{


        @Override
        protected ArrayList<FavoriteVO> doInBackground(Void... voids) {
            return parser.connect(list);
        }

        @Override
        protected void onPostExecute(ArrayList<FavoriteVO> favoriteVOS) {

            adapter = new FavoriteAdapter( FavoritesActivity.this, R.layout.favorites_item, favoriteVOS, myListView);

            myListView.setAdapter(adapter);
        }
    }
}