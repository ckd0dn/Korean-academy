package com.pch.bottom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class ModifyActivity extends AppCompatActivity {

    EditText name, id, password, password_check, tel, email;
    Button btn_cancel, btn_sign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        name = findViewById( R.id.m_name );
        id = findViewById( R.id.m_id );
        password = findViewById( R.id.m_password );
        password_check = findViewById( R.id.m_password_check );
        tel = findViewById( R.id.m_tel );
        email = findViewById( R.id.m_email );

        btn_cancel = findViewById( R.id.m_btn_cancel );
        btn_sign = findViewById( R.id.m_btn_modify );

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ModifyActivity.this);

        String str_name = sharedPreferences.getString("name", "name");
        String str_id = sharedPreferences.getString("id", "id");
        String str_pwd = sharedPreferences.getString("pwd", "pwd");
        String str_tel = sharedPreferences.getString("tel", "tel");
        String str_email = sharedPreferences.getString("email", "email");
        int c_idx = sharedPreferences.getInt("idx",0);

        Log.i("tel123", str_tel);
        Log.i("tel123", str_email);

        name.setText(str_name);
        id.setText(str_id);
        password.setText(str_pwd);
        password_check.setText(str_pwd);
        tel.setText(str_tel);
        email.setText(str_email);


        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String c_name = name.getText().toString();
                String c_id = id.getText().toString();
                String c_pwd = password.getText().toString();
                String c_pwd_check = password_check.getText().toString();
                String c_phone_number = tel.getText().toString();
                String c_email = email.getText().toString();

                //유효성 체크
                Pattern pattern = Patterns.EMAIL_ADDRESS;


                if (c_name.equals("")){
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (c_id.equals("")){
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (c_pwd.equals("")){
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (c_pwd_check.equals("")){
                    Toast.makeText(getApplicationContext(), "비밀번호확인을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (c_phone_number.equals("")){
                    Toast.makeText(getApplicationContext(), "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (c_email.equals("")){
                    Toast.makeText(getApplicationContext(), "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pattern.matcher(c_email).matches()){
                    Toast.makeText(getApplicationContext(), "이메일형식이 아닙니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (c_pwd.equals(c_pwd_check) == false){
                    Toast.makeText(getApplicationContext(), "비밀번호가 같지않습니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                String result = "name="+c_name+"&id="+c_id+"&pwd="+c_pwd+"&pwd_check="+c_pwd_check+
                        "&phone_number="+c_phone_number+"&email="+c_email+"&idx="+c_idx;//서버로 전달할 파라미터

                Log.i("url", result);

                new Task().execute(result, "modify");

            }
        });

    }//onCreate()

    class Task extends AsyncTask<String, Void, String> {

        String ip = "192.168.0.2";//서버의 ip주소
        String sendMsg, receiveMsg;
        String serverip = "http://"+ip+":9090/Login/modify.jsp";//연결할 jsp의 주소

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
                        receiveMsg = "회원정보 수정 성공";
                    }else {
                        receiveMsg = "수정 실패";
                    }
                }//if(conn.getResponseCode() == conn.HTTP_OK){}

            }catch (Exception e){

            }
            return receiveMsg;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ModifyActivity.this);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name.getText().toString());
            editor.putString("id", id.getText().toString());
            editor.putString("pwd", password.getText().toString());
            editor.putString("tel", tel.getText().toString());
            editor.putString("email", email.getText().toString());

            editor.commit();


            finish();
        }
    }//Task


}















