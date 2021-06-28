package com.pch.ex_1214;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

//클래스로 생성

public class MyAdapter extends ArrayAdapter<String> {

    //Adapter를 상속받아서 ArrayList의 내용을 ListView로 출력해주는 클래스
    Context context; //리스트뷰를 출력할 화면정보를 담은 클래스
    ArrayList<String> arr;
    int resource;
    ListView myList;//클릭이벤트를 위한 리스트 뷰를 생성자로 받아올 예정

    public MyAdapter(Context context, int resource, ArrayList<String> arr, ListView myList) {
        super(context, resource, arr);

        this.context = context;
        this.resource = resource;
        this.arr = arr;
        this.myList = myList;

        //리스트뷰에 클릭이벤트 감지자 등록
        myList.setOnItemClickListener( listClick );



    }

    //어댑터를 상속받는 클래스라면, 파라미터로 넘어온 ArrayList의 내용을
    //모두 출력하기 위해 반드시 오버라이드 해둬야 하는 메서드
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        //getView메서드는 Myadapter의 생성자로 받은 arr의 사이즈만큼
        //알아서 반복된다

        //getView() 메서드가 호출되면 리스트뷰에 표시할 정보들을 정의할 수 있다.
        LayoutInflater linf = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        convertView = linf.inflate(resource, null);

        //리스트에 표현할 데이터를 arr에서 꺼내온다
        //position은 인덱스 번호라고 생각하면 됌
        String str = arr.get(position);

        TextView txt = convertView.findViewById(R.id.form_txt);
        txt.setText(str);

        return convertView;
    }

    AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Toast.makeText(context.getApplicationContext(),
                arr.get(position), Toast.LENGTH_SHORT).show();
        }
    };
}
