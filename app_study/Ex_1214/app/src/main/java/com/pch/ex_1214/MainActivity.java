package com.pch.ex_1214;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button[] btns = new Button[4]; //btn1 ~ btn4
    Button btn_re;
    TextView txt_result;
    int n; // 난수발생용 변수


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRandom();

        btn_re = findViewById(R.id.btn_re);
        txt_result = findViewById(R.id.txt_result);

        //버튼 1~4까지 검색
        for ( int i = 0; i < btns.length; i++){

            //id중에 btn 1~4의 데이터를 가지고 온다
            int getID = getResources().getIdentifier("btn" + (i+1), "id", "com.pch.ex_1214");
            btns[i] = findViewById(getID);
            btns[i].setOnClickListener(click);
        }

        //다시하기 버튼 클릭시 이벤트 감지자 등록하기
        btn_re.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRandom();
                txt_result.setText("결과");
            }
        });
    }//onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //버튼에 쓰여진 텍스트 가져오기(
            int num = Integer.parseInt( ((Button)view).getText().toString() );
            if (num == n){
                //당첨인 경우
                txt_result.setText("당첨");
            }else {
                txt_result.setText("꽝");
            }
        }
    };
    //난수발생용 메서드
    public void setRandom(){
        n = new Random().nextInt(4) + 1;
    }
}