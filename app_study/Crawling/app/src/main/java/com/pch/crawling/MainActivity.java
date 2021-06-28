package com.pch.crawling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String nums;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById( R.id.tv );

        final Bundle bundle = new Bundle();

        new Thread(){
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect("https://dhlottery.co.kr/common.do?method=main").get();
                    Elements contents = doc.select("#lottoDrwNo");          //회차 id값 가져오기
                    nums += contents.text() +"회 :";

                    for(int i = 1; i < 7; i++){
                        contents = doc.select("#drwtNo"+i);                 //복권 번호 6개 가져오기
                        nums += " "+contents.text();
                    }
                    nums += " "+ doc.select("#bnusNo").text();                   //보너스 번호 contents 변수를 사용하지 않고 가져오는 방법

                    bundle.putString("numbers", nums);                               //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            tv.setText(bundle.getString("numbers"));                      //이런식으로 View를 메인 쓰레드에서 뿌려줘야한다.
        }
    };



}