package com.pch.bottom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    EditText edit_id, edit_pwd;
    Button btn_login;
    TextView sign_up;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_id = findViewById( R.id.edit_id );
        edit_pwd = findViewById( R.id.edit_pwd );
        btn_login = findViewById( R.id.login );
        sign_up = findViewById( R.id.sign_up );

        //회원가입 이동
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( LoginActivity.this, Sign_Up_Activity.class);
                startActivity(i);
            }
        });

        //로그인 버튼 감지
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = edit_id.getText().toString();
                String pwd = edit_pwd.getText().toString();

                String result = "id="+id+"&pwd="+pwd;

                //유효성 체크
                if (id.equals("")){
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pwd.equals("")){
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }


                new login().execute(result, "type_regi");


            }
        });


    }//onCreate

    class login extends AsyncTask<String, Void, String>{

        String ip = "192.168.35.68";//서버의 ip주소
        String sendMsg, receiveMsg;
        String serverip = "http://"+ip+":9090/Login/login.jsp";//연결할 jsp의 주소
        HttpURLConnection conn;
        JSONArray jsonArray;
        JSONObject jsonObject;

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            String id = "";
            try {
                String str= "";
                URL url = new URL(serverip);

                //url경로에 문제가 없다면 서버와 접속
                conn = (HttpURLConnection)url.openConnection();
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

                    jsonArray = new JSONObject(receiveMsg).getJSONArray("res");
                    jsonObject = jsonArray.getJSONObject(0);
                    result = jsonObject.optString("result");
                    id = jsonObject.optString("id");
                    Log.i("c12", id);

                    Log.i("jsonArray12","jsonArray12"+jsonArray);
                    Log.i("jsonObject12","jsonObject12"+jsonObject);

                    if ( result.equalsIgnoreCase("no_id")){
                        receiveMsg = "없는 아이디 입니다";
                    }else if (result.equalsIgnoreCase("no_pwd")){
                        receiveMsg = "비밀번호가 다릅니다";
                    }else{


                        receiveMsg = "success";


                    }
                }//if(conn.getResponseCode() == conn.HTTP_OK){}

            }catch (Exception e){

            }
            return receiveMsg;
        }

        @Override
        protected void onPostExecute(String s) {

            if ( s.equalsIgnoreCase("success")){

                JSONObject jObject = null;

                try {
                    jObject = jsonArray.getJSONObject(0);

                    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("idx", jObject.optInt("idx"));
                    editor.putString("name",jObject.optString("name"));
                    editor.putString("id",jObject.optString("id"));
                    editor.putString("pwd",jObject.optString("pwd"));
                    editor.putString("tel",jObject.optString("phone_number"));
                    editor.putString("email",jObject.optString("email"));

                    editor.commit();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(LoginActivity.this, MyPage.class);
                finish();
                startActivity(i);


                Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }


        }



    }



}