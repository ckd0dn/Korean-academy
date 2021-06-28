package com.pch.ex_1212;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5;
    TextView tv;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        tv = findViewById(R.id.tv);




        Random ran = new Random();
        a = ran.nextInt(3) + 1;

        View.OnClickListener myClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String buttonName = ((Button)view).getText().toString();
                if ( Integer.parseInt(buttonName) == a){
                    tv.setText( "맞았습니다" );
                }else {
                    tv.setText( "꽝" );
                }
            }
        };

        btn1.setOnClickListener(myClick);
        btn2.setOnClickListener(myClick);
        btn3.setOnClickListener(myClick);
        btn4.setOnClickListener(myClick);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = ran.nextInt(3) + 1;
                tv.setText("결과");
            }
        });
    }
}