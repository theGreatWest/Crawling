package kr.book.search.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    public String userRequest(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            return br.readLine();
        }catch (IOException e){
            System.out.println("검색에 실패했습니다.");

            return null;
        }
    }
}
