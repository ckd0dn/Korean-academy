package com.pch.ex_1218;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int num = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        if ( num != 3){
            finish();
        }else {
            Toast.makeText( getApplicationContext(),
                    "뒤로가기 버튼을 한번 더 누르면 종료됩니다",Toast.LENGTH_SHORT).show();
            //핸들러 호출
            mHandler.sendEmptyMessage(0);
        }
    }//onBackPressed

    Handler mHandler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            mHandler.sendEmptyMessageDelayed(0, 1000);
            //1초 간격으로 반복하며 3초를 카운팅합니다
            //3초가 지나면 핸들러를 정지하고 num을 다시 3으로 초기화
            if ( num > 0 ){
                --num;
            }else {
                num = 3;
                mHandler.removeMessages(0);//핸들러 정지
            }
        }
    };

}