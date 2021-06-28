package com.pch.Ex_1210;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButtonEventActivity extends Activity {

    Button btn_red, btn_green, btn_blue;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_event);

        //이벤트에 사용할 객체들을 검색
        btn_red = findViewById(R.id.btn1);
        btn_green = findViewById(R.id.btn2);
        btn_blue = findViewById(R.id.btn3);
        tv = findViewById(R.id.tv);

        //버튼에 이벤트 감지자 등록
        btn_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tv의 배경색과 text를 변경
                tv.setBackgroundColor(Color.argb(255,255,0,0));
                tv.setText("빨강");
            }
        });

        btn_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv.setBackgroundColor(Color.GREEN);
                tv.setText("초록");
            }
        });

        btn_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv.setBackgroundColor(Color.parseColor("#0000ff"));
                tv.setText("파랑");
            }
        });

    }//onCreate
}