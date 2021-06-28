package com.jyh.ex_1217;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class IntentMainActivity extends AppCompatActivity {

    Button btn_link, btn_call, btn_sms, btn_camera, btn_gallery, btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);

        btn_link = findViewById(R.id.btn_link);
        btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent클래스는 화면전환을 위해 사용하는 클래스
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData( Uri.parse("https://www.ruliweb.com") );
                startActivity(intent);//웹페이지로 화면전환
            }
        });

        btn_call = findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다이얼 화면으로 전환
                //Intent i = new Intent( Intent.ACTION_DIAL );
                //i.setData( Uri.parse("tel:010-111-2222") );
                //startActivity(i);

                //전화 즉시 걸기( CALL_PHONE 권한이 필요 )
                Intent i = new Intent( Intent.ACTION_CALL );
                i.setData( Uri.parse("tel:010-111-1111") );
                startActivity(i);
            }
        });

        btn_sms = findViewById(R.id.btn_sms);
        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //문자전송 페이지
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData( Uri.parse("smsto:010-123-1234") );
                i.putExtra("sms_body", "Hello~~");
                startActivity(i);
            }
        });

        btn_camera = findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //내장카메라 연결
                //Intent i = new Intent();
                //i.setAction( MediaStore.ACTION_IMAGE_CAPTURE );
                //startActivity(i);

                //동영상 연결
                Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                //i.setAction( MediaStore.ACTION_VIDEO_CAPTURE );
                startActivity(i);
            }
        });

        btn_gallery = findViewById(R.id.btn_gallery);
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //사진첩 이동
                Intent i = new Intent( Intent.ACTION_GET_CONTENT );
                i.setType("image/*");
                startActivity(i);

            }
        });

        //개발자가 만든 다른 액티비티로 화면 전환
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntentMainActivity.this, IntentSubActivity.class);
                startActivity(i);
            }
        });

    }//onCreate()

}





















































