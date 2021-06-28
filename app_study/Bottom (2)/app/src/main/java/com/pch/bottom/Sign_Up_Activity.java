package com.pch.bottom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

public class Sign_Up_Activity extends AppCompatActivity {

    EditText edit_name, edit_id, edit_password, edit_password_check, edit_phone_number, edit_email;
    Button btn_cancel, btn_sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up_);

        edit_name = findViewById( R.id.name );
        edit_id = findViewById( R.id.id );
        edit_password = findViewById( R.id.password );
        edit_password_check = findViewById( R.id.password_check );
        edit_phone_number = findViewById( R.id.phone_number );
        edit_email = findViewById( R.id.email );

        btn_cancel = findViewById( R.id.cancel );
        btn_sign = findViewById( R.id.sign );

        //취소버튼(로그인으로 돌아가기)
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //회원가입 버튼 클릭 감지
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edit_name.getText().toString();
                String id = edit_id.getText().toString();
                String pwd = edit_password.getText().toString();
                String pwd_check = edit_password_check.getText().toString();
                String phone_number = edit_phone_number.getText().toString();
                String email = edit_email.getText().toString();

                //유효성 체크
                Pattern pattern = Patterns.EMAIL_ADDRESS;


                if (name.equals("")){
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (id.equals("")){
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pwd.equals("")){
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pwd_check.equals("")){
                    Toast.makeText(getApplicationContext(), "비밀번호확인을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (phone_number.equals("")){
                    Toast.makeText(getApplicationContext(), "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email.equals("")){
                    Toast.makeText(getApplicationContext(), "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pattern.matcher(email).matches()){
                    Toast.makeText(getApplicationContext(), "이메일형식이 아닙니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pwd.equals(pwd_check) == false){
                    Toast.makeText(getApplicationContext(), "비밀번호가 같지않습니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                String result = "name="+name+"&id="+id+"&pwd="+pwd+"&pwd_check="+pwd_check+
                        "&phone_number="+phone_number+"&email="+email;//서버로 전달할 파라미터

                new Task().execute(result, "type_regi");

            }
        });

    }//onCreate

    class Task extends AsyncTask<String, Void, String>{

        String ip = "192.168.0.2";//서버의 ip주소
        String sendMsg, receiveMsg;
        String serverip = "http://"+ip+":9090/Login/sign_up.jsp";//연결할 jsp의 주소

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
            finish();
        }
    }//Task




}