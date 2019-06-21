package com.roachfu.tutorial.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SPITest {

    public static void main(String[] args) {
        ServiceLoader<Search> serviceLoader = ServiceLoader.load(Search.class);
        Iterator<Search> iterator= serviceLoader.iterator();
        serviceLoader.spliterator();
        while (iterator.hasNext()){
            Search search = iterator.next();
            search.searchDoc("HelloWorld");
        }
    }
}
