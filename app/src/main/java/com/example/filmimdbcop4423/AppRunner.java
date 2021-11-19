package com.example.filmimdbcop4423;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppRunner {

    private static AppRunner instance;

    public static AppRunner getInstance() {
        if (instance == null) {
            instance = new AppRunner();
        }
        return instance;
    }

    private final ScheduledExecutorService myNetwork = Executors.newScheduledThreadPool(4); //3

    public ScheduledExecutorService getMyNetwork(){
        return myNetwork;
    }

}
