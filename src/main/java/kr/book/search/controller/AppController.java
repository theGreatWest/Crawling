package kr.book.search.controller;

import kr.book.search.service.AppService;
import kr.book.search.view.InputView;
import kr.book.search.view.OutputView;

public class AppController {
    private final AppService appService;

    private InputView inputView;
    private OutputView outputView;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    public void run(){

    }
}
