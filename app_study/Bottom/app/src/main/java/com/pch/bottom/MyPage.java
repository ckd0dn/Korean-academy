package com.pch.bottom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyPage extends AppCompatActivity {

    Button favorites, edit_my_information, recent_retaurant, logout;
    TextView email, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        favorites = findViewById( R.id.favorites );
        edit_my_information = findViewById( R.id.edit_my_information );
        recent_retaurant = findViewById( R.id.recent_retaurant );
        logout = findViewById( R.id.logout );

        email = findViewById(R.id.email);
        name = findViewById(R.id.name);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyPage.this);

        String str_name = sharedPreferences.getString("name", "name");
        String str_email = sharedPreferences.getString("email", "email");

        name.setText(str_name);
        email.setText(str_email);

        //즐겨찾기
        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyPage.this, FavoritesActivity.class);
                startActivity(i);
            }
        });

        //내정보 수정
        edit_my_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyPage.this, ModifyActivity.class);
                startActivity(i);
            }
        });

        //로그아웃
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyPage.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }

        });
    }
}