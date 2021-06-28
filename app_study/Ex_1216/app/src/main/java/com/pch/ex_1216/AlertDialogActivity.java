package com.pch.ex_1216;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity {

    Button show_dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

        show_dialog = findViewById(R.id.show_dialog);
        show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AlertDialog : 간단한 경고창을 띄워주는 다이얼로그이며, 최대 세 개 까지만
                //버튼을 가지고 있을 수 있다다
                AlertDialog.Builder dialog = new AlertDialog.Builder(AlertDialogActivity.this);

                //AlertDialog에서 기본적으로 제공하는 세가지의 버튼
                dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();//현재 액티비티 종료
                    }
                });

                dialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //다이얼로그의 내용으로 아무것도 입력하지 않으면
                        //알아서 dismiss처리가 된다.
                    }
                });
                dialog.setNeutralButton("나중에", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "나중에 종료할게요", Toast.LENGTH_SHORT).show();
                    }
                });

                //다이얼로그에 제목지정
                dialog.setTitle("깨깨오톡");

                //다이얼로그에 메시지 설정
                dialog.setMessage("우리앱을 평가 해주시겠어요?");

                dialog.show();

            }
       });
    }
}