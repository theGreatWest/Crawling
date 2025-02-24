package kr.book.search;

import kr.book.search.controller.AppController;
import kr.book.search.util.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        AppController appController = appConfig.appController();

        appController.run();
    }
}
