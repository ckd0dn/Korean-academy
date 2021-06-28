package com.pch.ex_1223;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class DrawerActivity extends AppCompatActivity {

    DrawerLayout drawer_layout;
    LinearLayout drawer;//열고 닫을 서랍
    Button btn_open, btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        drawer_layout = findViewById( R.id.drawer_layout );
        drawer = findViewById( R.id.drawer );
        btn_open = findViewById( R.id.btn_main ); //서랍열기 버튼
        btn_close = findViewById( R.id.closedrawer ); //서랍닫기 버튼

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //서랍열기
                drawer_layout.openDrawer(drawer);
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //서랍닫기
                drawer_layout.closeDrawer(drawer);
            }
        });
    }//onCreate
}