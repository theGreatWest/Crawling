package kr.book.search.util.config;

import kr.book.search.controller.AppController;
import kr.book.search.service.AppService;

public class AppConfig {
    public AppController appController(){
        return new AppController(appService());
    }

    private AppService appService(){
        return new AppService();
    }
}
