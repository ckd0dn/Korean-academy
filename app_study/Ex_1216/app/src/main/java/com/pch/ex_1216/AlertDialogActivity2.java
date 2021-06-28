package com.pch.ex_1216;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogActivity2 extends AppCompatActivity {

    Button show_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog2);

        show_dialog = findViewById(R.id.show_dialog);
        show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Builder로 만듬
                AlertDialog.Builder dialog = new AlertDialog.Builder( AlertDialogActivity2.this);

                dialog.setTitle("앱이름");

                dialog.setMessage("앱을 평가하시겠습니까?");

                dialog.setPositiveButton("지금할게요", click);
                dialog.setNegativeButton("안할래요", null); //감지자를 잡아두지 않으면 다이얼로그만 종료
                dialog.setNeutralButton("나중에 할래요",click);

                dialog.show();
            }
        });
    }//onCreate

    DialogInterface.OnClickListener click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            switch ( i ){

                case DialogInterface.BUTTON_POSITIVE:
                    Toast.makeText(getApplicationContext(),
                            "평가시작", Toast.LENGTH_SHORT).show();
                    break;

               /* case DialogInterface.BUTTON_NEGATIVE:
                    Button_NEGATIVE는 클릭시 다이얼로그만 종료할 예정이므로 없어도 무관
                    break;*/

                case DialogInterface.BUTTON_NEUTRAL:
                    Toast.makeText(getApplicationContext(),
                            "나중에", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}