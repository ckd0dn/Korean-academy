package com.pch.ex_1214;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class InflaterActivity extends AppCompatActivity {

    FrameLayout frame;
    View view;
    Button btn_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflater);

        frame = findViewById(R.id.frame);

        //LayoutInflater는 xml 레이아웃을 객체화 시켜주는(눈으로 확인가능하도록) 역할을 한다
        LayoutInflater in = (LayoutInflater)getSystemService( LAYOUT_INFLATER_SERVICE );
        /*view = in.inflate(R.layout.inner_layout, null);*/
        view = in.inflate(R.layout.inner_layout, frame);
        //프레임 레이아웃에 view를 추가
        /*frame.addView( view );*/

        btn_bottom = view.findViewById( R.id .btn_bottom);
        btn_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "토스트 실행함", Toast.LENGTH_SHORT).show();
            }
        });
    }
}