package com.jyh.ex_1217;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {

    TextView txt;
    Button btn_start, btn_stop;
    int num = 0;//txt의 내용을 갱신하기 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        txt = findViewById(R.id.txt);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);

        btn_start.setOnClickListener(click);
        btn_stop.setOnClickListener(click);

    }//onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch ( v.getId() ){

                case R.id.btn_start://핸들러 호출

                    //핸들러의 handleMessage()를 호출
                    myHandler.sendEmptyMessage(0);

                    //시작버튼 비활성화
                    btn_start.setEnabled(false);
                    break;

                case R.id.btn_stop://핸들러 정지
                    myHandler.removeMessages(0);

                    //시작버튼 활성화
                    btn_start.setEnabled(true);
                    break;
            }//switch

        }
    };

    //핸들러는 자바의 스레드와 비슷한 개념으로써, 액티비티의 순환주기와는 별개로
    //독립적인 작업을 수행하기 위한 클래스다.
    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //스레드의 run()메서드와 같은 역할
            //1초에 한번씩 handleMessage()를 반복수행
            myHandler.sendEmptyMessageDelayed(0, 1000);

            ++num;
            txt.setText("" + num);

        }
    };

}










































