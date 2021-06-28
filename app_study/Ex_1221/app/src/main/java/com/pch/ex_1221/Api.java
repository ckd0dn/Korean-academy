package com.pch.ex_1221;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import paser.Parser;
import vo.BookVO;

public class Api extends AppCompatActivity {

    public static EditText search;
    ListView myListView;
    Button search_btn;
    Parser parser;
    ArrayList<BookVO> list;
    ViewModelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        search = findViewById( R.id.search );
        myListView = findViewById( R.id.myListView);
        search_btn = findViewById( R.id.search_btn);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                list = new ArrayList<BookVO>();
                adapter = null;

                //서버통신을 위한 스레드 호출출
                new NaverAsync().execute();//<--doInBackground를 백그라운드에서 동작하도록 호출하는 메서드
            }
        });

        parser = new Parser();

    }//onCreate

    //서버통신 전용 스레드 클래스
    //에러 alt + enter
    //AsyncTask클래스를 세 개의 제너릭 타입을 갖는다
    //1. doInBackground로 전달될 파라미터의 타입
    //2. UI진행상황을 관리하는 onProgressUpdate()메서드가 오버라이딩 되어 있다면, 이 메서드에서 사용할 타입
    //3. doInBackground의 반환형이자 onPostExecute의 파라미터 타입
    class NaverAsync extends AsyncTask<Void, Void, ArrayList<BookVO>>{

        @Override
        protected ArrayList<BookVO> doInBackground(Void... voids) {
            //각종 반복이나 제얻ㄴ 주된 처리로직을 담당하는 메서드. 반드시 필요
            return parser.connectNaver(list);
        }

        @Override
        protected void onPostExecute(ArrayList<BookVO> bookVOS) {
            //doInBackground의 작업결과를 파라미터로 받는 메서드
           if ( adapter == null ){
               adapter = new ViewModelAdapter(
                       Api.this, R.layout.book_item, bookVOS, myListView);

               //리스트뷰에 어댑터 세팅
               //이때, 자동으로 한 번 어댑터 클래스의 getView() 메서드가 호출
               myListView.setAdapter(adapter);

           }


        }//onPostExecute()

    }//NaverAsync class
}
