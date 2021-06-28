package com.pch.crawling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Convenience extends AppCompatActivity {

    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenience);

        tv = findViewById( R.id.tv );
        final Bundle bundle = new Bundle();

        new Thread(){
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect("http://cu.bgfretail.com/product/product.do?category=product&depth2=4&depth3=1").get();

                    Elements contents = doc.select(".prodListWrap");          //회차 id값 가져오기
                    String sam = contents.toString();
                    Log.i("test", sam);
                    String src = contents.attr("img src");

                    Log.i("test", src);
                    bundle.putString("src", src);                               //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
            tv.setText(bundle.getString("src"));                      //이런식으로 View를 메인 쓰레드에서 뿌려줘야한다.
        }
    };

}