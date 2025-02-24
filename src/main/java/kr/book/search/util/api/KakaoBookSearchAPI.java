package kr.book.search.util.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kr.book.search.model.Book;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KakaoBookSearchAPI {
    private static final String API_KEY = "bdbe6b88adda7e2f5b75560c494fe5b0";
    private static final String API_BASE_URL = "https://dapi.kakao.com/v3/search/book";
    private static final OkHttpClient client = new OkHttpClient(); // 위 URL 에 접속하는 클래스(네트워킹)
    private static final Gson gson = new Gson(); // json 파싱을 위해 필요

    // 책 검색 메서드
    public static List<Book> searchBooks(String title) { // 책 이름 입력 받기
        // url 생성
        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_BASE_URL).newBuilder(); // url 연결 객체 생성
        urlBuilder.addQueryParameter("query", title); // query 에 입력받은 책 이름을 연결시켜주기( --data-urlencode "query=미움받을 용기" \ 라고 설명되어 있기 때문에 )

        // url 이용해 서버에 요청하는 객체 생성
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .addHeader("Authorization", "KakaoAK "+API_KEY) // -H "Authorization: KakaoAK ${REST_API_KEY}" \ 헤더를 이런식으로 추가해야 된다고설명되어 있기 때문에 요청에 추가
                .build();

        // 네트워킹: 요청 값을 전달해 접속한 후 응답 받아 처리
        try(Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful())
                throw new IOException("Request failed: "+response);

            JsonObject jsonResponse = gson.fromJson(response.body().charStream(), JsonObject.class); // 받은 stream 을 json object 로 바꿔주기
            JsonArray documents = jsonResponse.getAsJsonArray("documents");
            /* documents는 아래 항목의 하위 항목들을 가져옴
                },
                  "documents": [
                    { 세부 데이터가 책 하나당 하나의 배열로 나열되어 있음.
             */

            List<Book> books = new ArrayList<>();
            for(JsonElement document : documents){ // title 을 가지고 검색된 여러 항목들
                JsonObject bookJson = document.getAsJsonObject(); // 책 정보가 하나씩 json 의 형태로 저장되어 있음
                Book book = new Book(
                        bookJson.get("title").getAsString(),
                        bookJson.get("authors").getAsString(),
                        bookJson.get("publisher").getAsString(),
                        bookJson.get("thumbnail").getAsString()
                );
                books.add(book);
            }
            return books;

        }catch (IOException e){
            System.out.println(e.getMessage());
            return  null;
        }
    }
}
