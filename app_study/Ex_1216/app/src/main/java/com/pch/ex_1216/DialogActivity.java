package com.pch.ex_1216;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {

    Button show_dialog, btn1, btn2;
    Dialog dialog;//팝업창!!


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        show_dialog = findViewById(R.id.show_dialog);

        show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //팝업창 생성
                //context는 액티비티 이름.this  Toast에서만 getApplicationContext()
                dialog = new Dialog( DialogActivity.this );

                //다이얼 로그가 화면으로 사용할 xml을 등록
                dialog.setContentView(R.layout.dialog_layout);

                //다이얼로그에서 이벤트를 처리하기 위한 버튼 감지자 등록
                //layout이 다르기 때문에 dialog_layout이 있는 dialog에서 찾아줘야함
                btn1 = dialog.findViewById( R.id.btn1 );
                btn2 = dialog.findViewById( R.id.btn2 );

                btn1.setOnClickListener( Click );
                btn2.setOnClickListener( Click );

                //주변부 터치, 뒤로가기 버튼으로 다이얼로그가 종료되는 것을 방지
                dialog.setCancelable(false);

                dialog.show();
            }
        });
    }//onCreate()

    View.OnClickListener Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if ( v == btn2 ){
                //다이얼로그 종료
                dialog.dismiss();
            }
        }
    };

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(getApplicationContext(), "뒤로가기", Toast.LENGTH_SHORT);
        finish();//현재 액티비티를 종료하는 메서드
    }
}