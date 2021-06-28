package com.pch.ex_1224;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {

    EditText edit_id, edit_pwd;
    Button btn_regi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edit_id = findViewById( R.id.edit_id);
        edit_pwd = findViewById( R.id.edit_pwd);
        btn_regi = findViewById( R.id.btn_regi );

        //회원가입 버튼 클릭 감지
        btn_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edit_id.getText().toString();
                String pwd = edit_pwd.getText().toString();
                String result = "id="+id+"&pwd="+pwd;//서버로 전달할 파라미터

                new Task().execute(result, "type_regi");
            }
        });
    }//onCreate()

    //회원가입용 Task클래스
    class Task extends AsyncTask< String, Void, String>{

        String ip = "192.168.35.68";//서버의 ip주소
        String sendMsg, receiveMsg;
        String serverip = "http://"+ip+":9090/AndJSPTest/list.jsp";//연결할 jsp의 주소소

       @Override
        protected String doInBackground(String... strings) {

           String result = "";

           try {
               String str= "";
               URL url = new URL(serverip);

               //url경로에 문제가 없다면 서버와 접속
               HttpURLConnection conn = (HttpURLConnection)url.openConnection();
               conn.setRequestMethod("POST");
               OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

               //서버로 전달하고자 하는 내용
               //list.jsp?id=aaa&pwd=1111&type=type_regi
               sendMsg = strings[0]+"&type="+strings[1];

               osw.write((sendMsg));
               osw.flush();

               //서버로 데이터의 전송이 완료되면
               //서버측(jsp)에서 처리한 결과값을 돌려받아야 한다

               if ( conn.getResponseCode() == conn.HTTP_OK ){//200이면 정상, 404,500등은 비정상
                   InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");

                   //reader가 서버로부터 결과데이터를 가지고 있으므로
                   //이를 BufferedReader를 통해 한줄 단위로 읽어들인다
                   BufferedReader reader = new BufferedReader(tmp);

                   //{res:[{'result':'%s'}]}
                   StringBuffer buffer = new StringBuffer();
                   while ((str = reader.readLine()) != null ){
                       buffer.append(str);
                   }

                   receiveMsg = buffer.toString();

                   JSONArray jarray = new JSONObject(receiveMsg).getJSONArray("res");
                   JSONObject jsonObject = jarray.getJSONObject(0);
                   result = jsonObject.optString("result");

                   if ( result.equalsIgnoreCase("success")){
                       receiveMsg = "가입성공";
                   }else {
                       receiveMsg = "이미 사용중인 아이디입니다";
                   }
               }//if(conn.getResponseCode() == conn.HTTP_OK){}

           }catch (Exception e){

           }
            return receiveMsg;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
    }
}