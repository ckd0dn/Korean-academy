package com.pch.ex_1211;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class HoiMoonsoo extends AppCompatActivity {

    Button btn;
    TextView tv;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoi_moonsoo);

        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = et.getText().toString(); //원본
                String revStr = "";

                for ( int i = str.length()-1; i >= 0; i--){
                    revStr += str.charAt(i);
                }//for

                if ( str.equals(revStr) ){
                    //회문수의 경우
                    tv.setText("회문수 입니다");
                }else {

                    tv.setText("회문수가 아닙니다");
                }

            }
        });
    }
}