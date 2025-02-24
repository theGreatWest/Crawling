package kr.book.search.controller;

import kr.book.search.model.Book;
import kr.book.search.service.AppService;
import kr.book.search.util.api.KakaoBookSearchAPI;
import kr.book.search.view.InputView;
import kr.book.search.view.OutputView;

import java.util.List;

public class AppController {
    private final AppService appService;

    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();

    public AppController(AppService appService) {
        this.appService = appService;
    }

    public void run(){
        String title = inputBookTitle();

        List<Book> books = getBooks(title);

    }

    private String inputBookTitle(){
        String title = null;
        while(title==null){
            outputView.printMessage("도서 제목을 입력하세요");
            title = inputView.userRequest();
            if(title==null){
                outputView.printMessage("다시 입력해 주세요\n");
            }
        }
        return title;
    }

    private List<Book> getBooks(String title){
        List<Book> books = KakaoBookSearchAPI.searchBooks(title);

        if(books == null) {
            outputView.printMessage("검색 결과가 없습니다.");
            return null;
        }

        return books;
    }
}
