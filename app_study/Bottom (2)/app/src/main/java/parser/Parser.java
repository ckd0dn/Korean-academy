package parser;

import android.util.Log;

import java.util.ArrayList;

import vo.FavoriteVO;

public class Parser {
    FavoriteVO vo;

    public ArrayList<FavoriteVO> connect(ArrayList<FavoriteVO> list){

        vo = new FavoriteVO();

        vo.setAddress("인천검암동");
        vo.setR_title("주먹밥");
        vo.setCategory("한식");
        list.add(vo);

        Log.i("m", String.valueOf(list.size()));
        return list;
    }
}
