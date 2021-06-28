package com.pch.ex_1222;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MainActivity extends AppCompatActivity {

    ImageView iv_photo;

    //핀치 투 줌 라이브러리 클래스
    PhotoViewAttacher attacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_photo = findViewById(R.id.iv_photo);

        //attacher와 iv_photo를 연결
        attacher = new PhotoViewAttacher(iv_photo);
        attacher.update();//핀치투 줌 적용


    }//onCreate()
}