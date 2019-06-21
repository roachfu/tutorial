package com.roachfu.tutorial.spi;

import java.util.List;

public class FileSearch implements Search {

    @Override
    public List<String> searchDoc(String key) {
        System.out.println("文件搜索: " + key);
        return null;
    }
}
