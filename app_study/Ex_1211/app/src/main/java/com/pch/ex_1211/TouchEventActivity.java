package com.pch.ex_1211;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TouchEventActivity extends AppCompatActivity {

    Button btn;
    TextView box, txt_view;
    Boolean isCheck =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);

        btn = findViewById(R.id.event_btn);
        box = findViewById(R.id.box);
        txt_view = findViewById(R.id.txt_view);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isCheck = !isCheck;

                if ( isCheck ){
                    box.setText("Event!");
                }else{
                    box.setText("NO Event");
                }

            }
        });

        //빨간색 상자의 터치를 감지
        box.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                //대표적인 터치의 종류
                //DOWN, UP, MOVE
                String msg = "";
                int x = 0;
                int y = 0;

                switch ( motionEvent.getAction() ){//현재 발생한 이벤트의 종류를 감지

                    case MotionEvent.ACTION_DOWN://화면터치
                        msg = "ACTION DWON";
                        break;
                    case  MotionEvent.ACTION_UP://화면에서 터치된 손이 떨어졌을 때
                        msg = "ACTION UP";
                        break;
                    case  MotionEvent.ACTION_MOVE://터치한 상대로 움직일때
                        x = (int)motionEvent.getX();
                        y = (int)motionEvent.getY();
                        msg = String.format("결과 : x:%d, y%d", x, y);
                        break;

                }//switch

                txt_view.setText(msg);

                //return으로 false가 반환되면 이벤트 처리가 아직 남았다고 판단하여
                //화면 UI를 갱신하지 못한다
                return isCheck;
            }
        });

    }
}