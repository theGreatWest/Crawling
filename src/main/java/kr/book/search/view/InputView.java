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

            return null;
        }
    }
}
