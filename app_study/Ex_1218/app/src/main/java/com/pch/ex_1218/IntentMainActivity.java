package com.pch.ex_1218;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class IntentMainActivity extends AppCompatActivity {

    EditText edit_name, edit_age, edit_tel, edit_b_date;
    Button btn_date, btn_send;
    Dialog dialog; //추후에 달력 다이얼로그를 표시하기 위한 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);

        edit_name = findViewById(R.id.edit_name);
        edit_age = findViewById(R.id.edit_age);
        edit_tel = findViewById(R.id.edit_tel);
        edit_b_date = findViewById(R.id.edit_b_day);
        btn_date = findViewById(R.id.btn_date);
        btn_send = findViewById(R.id.btn_send);

        //값 전달을 위한 send버튼에 이벤트 감지자 등록
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이름, 나이, 전화, 생일 정보를 Sub액티비티로 전달
                Intent i = new Intent( IntentMainActivity.this, IntentSubActivity.class );

                String name = edit_name.getText().toString();
                String age = edit_age.getText().toString();
                String tel = edit_tel.getText().toString();


                //다른 액티비티로 값 전달하기(방법 1 - Intent를 통한 전달)
               /* i.putExtra("m_name", name);
                i.putExtra("m_age", age);
                i.putExtra("m_tel", tel);*/

                //다른 액티비티로 값 전달하기(방법 2 - Bundle을 통한 전달)
                //Bundle은 여러가지 타입의 값을 저장하기 위해 만들어진 Map구조의 클래스
                //Bundle이 더 많이 쓰이므로 bundle 방법을 추천
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("age", age);
                bundle.putString("tel", tel);

                //Intent에 Bundle 객체 저장
                i.putExtras(bundle);

                startActivity(i);//값 전달하면서 화면전환
            }
        });

        //날짜선택 버튼에 이벤트 감지자 등록
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //캘린더 클래스를 통해 휴대폰에 등록되어 있는 현재 날짜를 가져온다 java캘린더 사용
                Calendar now = Calendar.getInstance();
                int y = now.get(Calendar.YEAR);//년
                int m = now.get(Calendar.MONTH);//월
                int d = now.get(Calendar.DAY_OF_MONTH);//일

                dialog = new DatePickerDialog( IntentMainActivity.this,//달력다이얼로그를 띄워줄 화면
                            dateListener, //달력의 날짜가 변경됨을 감지하는 이벤트 감지자자
                            y, m, d );//연,월,일
                dialog.show();


            }
        });
    }

    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            //파라미터중 m은 1월 -> 0, 2월 -> 1, ..12월 -> 11
            //날짜포멧( 2020-01-15 )
            String StrDate = String.format("%d-%02d-%02d",y,m+1,d);

            //얻어온 생년월일을 editText에 넣는다
            edit_b_date.setText(StrDate);
        }
    };
}