package com.pch.bottom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import vo.FavoriteVO;

public class FavoritesActivity extends AppCompatActivity {

    ListView myListView;
    FavoriteAdapter adapter;
    EditText edit_id;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);


        edit_id = findViewById(R.id.edit_id);
        myListView = findViewById(R.id.myListView);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FavoritesActivity.this);

        String id = sharedPreferences.getString("id", "id");
        Log.i("TEST", id);
        String result = "id="+id;

        new FavoriteAsync().execute(result, "type_regi");





    }//OnCreate

    class FavoriteAsync extends AsyncTask<String, Void, ArrayList<FavoriteVO>>{

        String ip = "192.168.35.68";//서버의 ip주소
        String sendMsg, receiveMsg;
        String serverip = "http://"+ip+":9090/Login/favorite.jsp";//연결할 jsp의 주소






        @Override
        protected ArrayList<FavoriteVO> doInBackground(String... strings) {

            ArrayList list = new ArrayList();

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

                    list.clear();
                    for (int i = 0; i < jarray.length(); i++){
                        FavoriteVO vo = new FavoriteVO();
                        JSONObject jsonObject = jarray.getJSONObject(i);

                        vo.setR_title(jsonObject.optString("restaurant_name"));
                        vo.setR_number(jsonObject.optString("restaurant_number"));

                        Log.i("TEST", "Json " + jsonObject.optString("restaurant_name") );

                        list.add(vo);
                    }




                }//if(conn.getResponseCode() == conn.HTTP_OK){}

            }catch (Exception e){
                Log.i("TEST", "res err:"+e.getMessage());
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<FavoriteVO> favoriteVOS) {

            Log.i("TEST", "size:" + favoriteVOS.size());
            adapter = new FavoriteAdapter( FavoritesActivity.this, R.layout.favorites_item, favoriteVOS, myListView);

            myListView.setAdapter(adapter);
        }
    }//FavoriteAsync
}