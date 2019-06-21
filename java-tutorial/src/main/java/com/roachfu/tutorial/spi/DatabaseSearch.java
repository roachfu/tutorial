package com.roachfu.tutorial.spi;

import java.util.List;

public class DatabaseSearch implements Search {

    @Override
    public List<String> searchDoc(String key) {
        System.out.println("database search : " + key);

        return null;
    }
}
