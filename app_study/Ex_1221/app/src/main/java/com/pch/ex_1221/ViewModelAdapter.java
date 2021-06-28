package com.pch.ex_1221;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

import vo.BookVO;

public class ViewModelAdapter extends ArrayAdapter<BookVO> {

    Context context;
    int resource;
    BookVO vo;
    ArrayList<BookVO> list;
    ListView myListView;

    public ViewModelAdapter(@NonNull Context context, int resource, ArrayList<BookVO> list, ListView myListView) {
        super(context, resource, list);

        this.context = context;
        this.resource = resource;
        this.list = list;
        this.myListView = myListView;

        //리스트뷰에 이벤트 감지자 등록!!
        this.myListView.setOnItemClickListener( list_click );
    }//생성자

    AdapterView.OnItemClickListener list_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            String bookImg = list.get(position).getB_img();

            //위의 이미지 경로에서 이미지 이름만 추출
            String bookId = bookImg.substring( bookImg.lastIndexOf('/')+1, bookImg.lastIndexOf(".jpg") ); //마지막/의 다음칸부터

            String bookLink = "https://book.naver.com/bookdb/book_detail.nhn?bid=" + bookId;

            //상세보기 페이지로 전환하기 위한 Intent
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(bookLink));
            context.startActivity(intent);//Adapter이기 때문에 context에서 startActivity참조

        }
    };


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        //Adapter클래스의 생성자로 넘어온 list의 사이즈만큼 position을 1씩 증가시키며
        //자동으로 호출되는 메서드

        //파라미터로 넘어온 resource를 Inflater를 통해 convertView로 객체화
        LayoutInflater linf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = linf.inflate(resource, null);

        vo = list.get(position);
        TextView title = convertView.findViewById(R.id.book_title);
        TextView author = convertView.findViewById(R.id.book_author);
        TextView price = convertView.findViewById(R.id.book_price);
        ImageView img = convertView.findViewById(R.id.book_img);

        title.setText( vo.getB_title() );
        author.setText("저자 : " + vo.getB_author() );
        price.setText("가격 : " + vo.getB_price() );

        //이미지 가져오기
        new ImgAsync(img, vo).execute(vo.getB_img());

        return convertView;
    }//getView()

    //이미지 로드를 위한 Async클래스
    //alt+ enter implement 메서드
    class ImgAsync extends AsyncTask< String , Void , Bitmap> {

        Bitmap bm;
        ImageView mImg;
        BookVO vo;

        //alt + insert constructor 선택, 순서대로 선택한대로 파라미터가 들어감
        public ImgAsync(ImageView mImg, BookVO vo) {
            this.mImg = mImg;
            this.vo = vo;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                //가져오고자 하는 이미지 경로로 URL접근
                //strings[0] 은 execute의 vo.getB_img
                URL img_url = new URL( strings[0] );

                //url에 접근해서 오류가 발생하지 않았다면, 인풋 스트림을 이용하여
                //해당 이미지를 byte단위로 읽어온다
                BufferedInputStream bis = new BufferedInputStream( img_url.openStream() );
                bm = BitmapFactory.decodeStream(bis);
                bis.close();


            }catch (Exception e){

            }

            if ( bm != null ){//정상적으로 이미지를 읽어왔다면..!!
                return bm;
            }else {
                //이미지를 읽어오지 못한 경우에는 개발자가 준비한 기본 이미지를 사용
                //context.getResources() => res경로
                Bitmap bitmap = BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.ic_launcher_round );
                return bitmap;
            }


        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //doInBackground에서 return된 결과값을 파라미터로 받는다. bm, bitmap
            mImg.setImageBitmap(bitmap);
        }
    }//ImgAsync
}
