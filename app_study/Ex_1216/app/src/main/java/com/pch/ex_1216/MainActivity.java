package com.pch.ex_1216;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button btn_yes, btn_no, btn_restart;
    TextView show_num;
    LinearLayout linear;

    int phase = 1;
    int result = 0;

    final int YES = 1;
    final int NO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linear = findViewById(R.id.linear);
        btn_yes = findViewById( R.id.btn_yes);
        btn_no = findViewById( R.id.btn_no);
        show_num = findViewById(R.id.show_num);
        btn_restart = findViewById( R.id.btn_restart );

        btn_yes.setOnClickListener( Click );
        btn_no.setOnClickListener( Click );
        btn_restart.setOnClickListener( Click );

    }//onCreate();

    View.OnClickListener Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch ( view.getId() ){

                case R.id.btn_yes:
                    showPhase(YES);
                    break;

                case R.id.btn_no:
                    showPhase(NO);
                    break;

                case R.id.btn_restart:
                    show_num.setText("04 05 06 07 12\n13 14 15 20 21\n22 23 28 29 30");
                    linear.setVisibility(View.VISIBLE);
                    btn_restart.setVisibility(View.INVISIBLE);
                    phase = 1;
                    result = 0;
                    break;

            }//switch


        }
    };

    //단계 구별 메서드

    public void showPhase( int select ){

        String str = "";

        switch ( phase ){

            case 1:
                if (select == YES)
                    result += 4;

                str = "16 17 18 19 20\n21 22 23 24 25\n26 27 28 29 30";
                break;

            case 2:
                if (select == YES)
                    result += 16;

                str = "01 03 05 07 09\n11 13 15 17 19\n21 23 25 27 29";
                break;

            case 3:
                if (select == YES)
                    result += 1;

                str = "08 09 10 11 12\n13 14 15 24 25\n26 27 28 29 30";
                break;

            case 4:
                if (select == YES)
                    result += 8;

                str = "02 03 06 07 10\n11 14 15 18 19\n22 23 26 27 30";
                break;

            case 5:
                if (select == YES)
                    result += 2;

                //결과갑 result가 31이거나 0이 나온경우 잘못 선택한 문항이 있다는 이야기
                if (result == 0 || result == 31 ){
                    str = "잘 못 선택한 문항이 있습니다";
                }else {
                    str = "당신이 생각한 숫자는 \n" + result + "입니다";
                }
                //다시하기 버튼 활성화
                btn_restart.setVisibility(View.VISIBLE);
                linear.setVisibility(View.INVISIBLE);
                break;
        }//switch

        show_num.setText(str);
        phase++;
    }//showPhase()

}