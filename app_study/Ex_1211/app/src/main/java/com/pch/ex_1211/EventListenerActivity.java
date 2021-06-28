package com.pch.ex_1211;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventListenerActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_listener);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        txt = findViewById(R.id.txt);

        btn1.setOnClickListener( click );
        btn2.setOnClickListener( click );
        btn3.setOnClickListener( click );


    }//onCreate()

    //버튼 클릭을 감지하는 이벤트 감지자
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) { // view(Button, Textview, EditText, Linear 등): 눈에 보이는 모든 객체들의 부모

            //현재 클릭을 발생시킨 객체의 아이디를 가져온다
            switch (view.getId()){
                case R.id.btn1:
                    txt.setText("홍길동님 반갑습니다");
                    break;

                case R.id.btn2:
                    txt.setText("박길동님 반갑습니다");
                    break;

                case R.id.btn3:
                    txt.setText("오오길동님 반갑습니다");
                   break;

            }

        }
    };
}