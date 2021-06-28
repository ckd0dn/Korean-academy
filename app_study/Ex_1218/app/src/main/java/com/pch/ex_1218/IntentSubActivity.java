package com.pch.ex_1218;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IntentSubActivity extends AppCompatActivity {

    TextView txt_name, txt_age, txt_tel, txt_birth;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);

        txt_name = findViewById(R.id.txt_name);
        txt_age = findViewById(R.id.txt_age);
        txt_tel = findViewById(R.id.txt_tel);
        txt_birth = findViewById(R.id.txt_birth);

        intent = getIntent();//Main에서 전달된 intent

        //Intent에서 파라미터 추출하기
        //String name = intent.getStringExtra("m_name");
        //String age = intent.getStringExtra("m_age");
        //String tel = intent.getStringExtra("m_tel");

        //파라미터 추출하기2
        Bundle bundle = intent.getExtras();//intent에서 추출된 bundle객체
        String name = bundle.getString("name");
        String age = bundle.getString("age");
        String tel = bundle.getString("tel");

        txt_name.setText(name);
        txt_age.setText(age);
        txt_tel.setText(tel);
    }//OnCreate()
}