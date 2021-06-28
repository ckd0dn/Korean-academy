package com.pch.ex_1215;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class PopupMenuActivity extends AppCompatActivity {

    Button menu_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);

        menu_show = findViewById(R.id.menu_show);
        menu_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//메뉴버튼 클릭시 호출되는 메서드

                //팝업메뉴
                // widget으로 임포트
                PopupMenu popup = new PopupMenu(PopupMenuActivity.this, view);

                //팝업메뉴에 붙일 메뉴 xml파일 등록
                getMenuInflater().inflate(R.menu.my_menu, popup.getMenu());

                //팝업메뉴에 이벤트 감지자 등록
                popup.setOnMenuItemClickListener( menuClick );

                //메뉴 보여주기
                popup.show();
            }
        });
    }

    PopupMenu.OnMenuItemClickListener menuClick  = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            switch ( menuItem.getItemId() ){//클릭한 메뉴의 아이디

                case R.id.menu1:
                    Toast.makeText(getApplicationContext(), "이메일 보내기", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu2:
                    Toast.makeText(getApplicationContext(), "저장하기", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu3:
                    Toast.makeText(getApplicationContext(), "종료하기", Toast.LENGTH_SHORT).show();
                    break;
            }

            return true;
        }
    };
}