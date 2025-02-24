package kr.book.search.controller;

import kr.book.search.model.Book;
import kr.book.search.service.AppService;
import kr.book.search.util.api.KakaoAddressAPI;
import kr.book.search.util.api.KakaoBookSearchAPI;
import kr.book.search.util.pdf.PdfGenerator;
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

    public void run() {
//        String title = inputBookTitle();
//
//        List<Book> books = getBooks(title);
//
//        createPDF(books);

        String address = inputAddress();

        findCoordinate(address);
    }

    private String inputBookTitle() {
        String title = null;
        while (title == null) {
            outputView.printMessage("도서 제목을 입력하세요");
            title = inputView.userRequest();
            if (title == null) {
                outputView.printMessage("다시 입력해 주세요\n");
            }
        }
        return title;
    }

    private String inputAddress() {
        String address = null;
        while (address == null) {
            outputView.printMessage("주소를 입력하세요");
            address = inputView.userRequest();
            if (address == null) {
                outputView.printMessage("다시 입력해 주세요\n");
            }
        }
        return address;
    }

    private List<Book> getBooks(String title) {
        List<Book> books = KakaoBookSearchAPI.searchBooks(title);

        if (books == null) {
            outputView.printMessage("검색 결과가 없습니다.");
            return null;
        }

        return books;
    }

    private void createPDF(List<Book> books) {
        PdfGenerator.generateBookListPdf(books, "result");
    }

    private void findCoordinate(String address){
        double[] coordinate = KakaoAddressAPI.getAddressCoordinate(address);

        if(coordinate != null){
            outputView.printMessage("주소: "+address);
            outputView.printMessage("위도: "+coordinate[0]);
            outputView.printMessage("경도: "+coordinate[1]);
        }else{
            outputView.printMessage("주소를 찾을 수 없습니다.");
        }
    }


}
