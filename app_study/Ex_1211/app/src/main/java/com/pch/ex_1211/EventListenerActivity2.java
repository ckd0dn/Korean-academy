package com.pch.ex_1211;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventListenerActivity2 extends AppCompatActivity {

    Button b1, b2, b3;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_listener2);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);
        tv = findViewById(R.id.txt);

        b1.setOnClickListener(myClick);
        b2.setOnClickListener(myClick);
        b3.setOnClickListener(myClick);

    }

    //버튼들이 공통적으로 사용할 이벤트 감지자
    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /* view에 getText 메서드가 없으므로 자식격인 BUtton으로 캐스팅하여 getText를 사용한다*/
            String name = ((Button)view).getText().toString();
            tv.setText(name + "님 환영합니다");
        }
    };

}