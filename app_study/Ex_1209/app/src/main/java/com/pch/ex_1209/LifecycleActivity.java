package com.pch.ex_1209;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        Log.i("my","--onCreate()--");
        /* console.log 느낌*/
        /* 하단의 Logcat에서 edit filter 들어가서 tag를 맞춰서 써준다 */
    }//onCreate()

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("my","--onDestroy()--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("my","--onPause()--");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("my","--onStart()--");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //makeText( Context, CharSequence, int )
        //Context : 화면제어권자(어떤 화면에서 객체를 뿌려줘야 하는지를 결정하는 클래스)
        //*Toast.makeText( LifecycleActivity.this,"restart 호출됨",Toast.LENGTH_SHORT ).show();
        //getApplicationContext()쓰면 Toast사용할때 메모리를 50%정보 가져와서 절약할수있음
        Toast.makeText( getApplicationContext(),"restart 호출됨",Toast.LENGTH_SHORT ).show();

        Log.i("my","--onRestart()--");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("my","--onResume()--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("my","--onStop()--");
    }

    /*
     앱을 싱행하면
     onCreate() -> 처음 앱을 실행했을 때 딱 한번 호출되는 메서드
     onStart()
     onResume()

     홈버튼으로 앱을 일시정지하면
     onPause()
     onStop()

     다시 앱으로 복귀하면
     onRestart()
     onstart()
     onResume()

     뒤로가기 버튼으로 앱을 완전히 종료하면
     onPause()
     onStop()
     onDestroy() -> 앱을 완전히 종료시켰을 때 딱 한번 호출되는 메서드
     */
}