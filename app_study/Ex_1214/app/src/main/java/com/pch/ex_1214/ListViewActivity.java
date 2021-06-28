package com.pch.ex_1214;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    Button addBtn;
    ListView myList;
    EditText et;

    ArrayList<String> arr;

    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        addBtn = findViewById(R.id.addBtn);
        myList = findViewById(R.id.mylist);
        et = findViewById(R.id.et);

        arr = new ArrayList<>();

        //arr가 담고있는 내용을 리스트로 표기하기 위해 MyAdapter를 생성
        adapter = new MyAdapter( ListViewActivity.this, R.layout.list_form, arr, myList );

        //어댑터의 정보를 리스트뷰로 세팅
        //setAdapter가 호출된 순간 adapter클래스의 getView()메서트가 자동으로 동작
        myList.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arr.add( et.getText().toString() );

                //arr이 갱신되면 myList.setAdapter(adapter);로 처음부터 로드하는 것 보다
                //notifyDataSetChanged()를 통해 중복된 값은 무시하고 새로운 값만 갱신하는 것이 유리
                adapter.notifyDataSetChanged();
            }
        });
    }//onCreate()
}