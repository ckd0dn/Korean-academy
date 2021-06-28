package com.jyh.ex_1217;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class BackpressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpress);
    }//onCreate()

    @Override
    public void onBackPressed() {

        //뒤로가기 버튼 감지
        AlertDialog.Builder dialog = new AlertDialog.Builder(BackpressActivity.this);
        dialog.setTitle("제목");
        dialog.setMessage("캐캐오톡을 종료하시겠습니까?");

        dialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //앱 종료
                finish();
            }
        });
        dialog.setNegativeButton("아니오", null);

        dialog.setCancelable(false);

        dialog.show();

    }//onBackPressed()
}































































