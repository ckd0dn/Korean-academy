package com.pch.ex_1223;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class SweetAlertActivity extends AppCompatActivity {

    SweetAlertDialog sweetAlertDialog;
    Button warning, error, success, custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweet_alert);

        warning = findViewById(R.id.warning);
        error = findViewById(R.id.error);
        success = findViewById(R.id.success);
        custom = findViewById(R.id.custom);

        warning.setOnClickListener(click);
        error.setOnClickListener(click);
        success.setOnClickListener(click);
        custom.setOnClickListener(click);
    }//onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch ( v.getId() ){
                case R.id.warning:
                    sweetAlertDialog = new SweetAlertDialog( SweetAlertActivity.this, SweetAlertDialog.WARNING_TYPE );
                    sweetAlertDialog.setTitleText("경고");
                    sweetAlertDialog.setContentText("데이터 사용량이 초과되었습니다");
                    sweetAlertDialog.show();
                    break;

                case R.id.error:
                    sweetAlertDialog = new SweetAlertDialog( SweetAlertActivity.this, SweetAlertDialog.ERROR_TYPE );
                    sweetAlertDialog.setTitleText("에러");
                    sweetAlertDialog.setContentText("에러에러에러");
                    sweetAlertDialog.show();
                    break;
                case R.id.success:
                    break;
                case R.id.custom:
                    break;
            }//switch

        }
    };
}