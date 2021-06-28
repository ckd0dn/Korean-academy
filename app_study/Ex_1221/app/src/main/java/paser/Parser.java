package paser;

import com.pch.ex_1221.Api;
import com.pch.ex_1221.MainActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vo.BookVO;

public class Parser {
    //웹에서 요소들(제목, 저자, 가격 등...)을 검색하여 준비된 vo에 저장하기 위한 클래스
    //웹의 xml을 분석하여 vo로 가져오는 작업을 xml파싱이라고 한다.
    BookVO vo;
    String myQuery = "";//검색어

    //서버와 통신하는 메서드
    public ArrayList<BookVO> connectNaver( ArrayList<BookVO> list ){

        try {
            //검색하고자 하는 단어(EditText에 쓰여진 문장)
            //URLEncoder 한글깨짐 방지
            myQuery = URLEncoder.encode( Api.search.getText().toString() , "UTF-8");

            int count = 100;//검색결과 100건 표시

            String urlStr = "https://openapi.naver.com/v1/search/book.xml?query="+myQuery+"&start=1&display=" +count;

            //URL클래스를 통해 urlStr경로로 접근
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();//접속시도

            //발급받은 ID와 secret을 네이버 서버로 전달
            connection.setRequestProperty("X-Naver-Client-Id", "euSkZbZyrthRnedfr3hU");
            connection.setRequestProperty("X-Naver-Client-Secret", "bj38000UA9");

            //위에서 접속한 URL을 수행하여 얻어올 자원 파싱하기위한 객체들
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            //connection을 통해 연결된 xml로 접근하여 파싱을 한다
            parser.setInput(connection.getInputStream(), null);

            //위에서 만든 파서를 통해 서버xml의 각 요소들을 반복하여 가지고 온다.
            int parserEvent = parser.getEventType();

            //xml문서의 끝을 만날때까지만 반복
            while ( parserEvent != XmlPullParser.END_DOCUMENT ){

                //시작태그의 이름을 알아ㅏ낸다
                if ( parserEvent == XmlPullParser.START_TAG ){
                    String tagName = parser.getName(); // <title>, <link>, ...

                    if ( tagName.equalsIgnoreCase("title")){
                        vo = new BookVO();
                        String title = parser.nextText();//태그안의 실제 값

                        //네이버 openApi로 얻어온 데이터는 검색한단어를 b태그로 묶어서 돌려준다
                        //이것을 제거하기 위해 정규식을 사용한다
                        //regex패키지
                        Pattern pattern =  Pattern.compile("<.*?>");
                        Matcher matcher = pattern.matcher(title);

                        //정규식 체크
                        if ( matcher.find() ){
                            String s_title = matcher.replaceAll("");
                            vo.setB_title(s_title);
                        }else {
                            vo.setB_title(title);
                        }


                    }else if(tagName.equalsIgnoreCase("image")){
                        String img = parser.nextText();
                        vo.setB_img(img);
                    }else if(tagName.equalsIgnoreCase("author")){
                        String author = parser.nextText();
                        vo.setB_author(author);
                    }else if(tagName.equalsIgnoreCase("price")){
                        String price = parser.nextText();
                        int p = Integer.parseInt(price);

                        //숫자 3자리마다 접찍기
                        DecimalFormat format = new DecimalFormat("###,###");

                        vo.setB_price(format.format(p));
                        list.add(vo);
                    }
                }

                parserEvent = parser.next();//다음요소


            }//while
        }catch (Exception e){

        }

        return list;
    }//connectNaver
}
