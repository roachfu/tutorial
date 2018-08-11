package com.roachfu.tutorial.jsoup;

import org.junit.Test;

public class CityStatsTest {

    private static final String COMMON_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/";

    @Test
    public void testParseProvince(){
        String url = COMMON_URL + "index.html";
        CityStats.parseProvince(url);
    }

    @Test
    public void testParseCity(){
        String url = COMMON_URL + "36.html";
        CityStats.parseCity(url, new Node());
    }

    @Test
    public void testParseCounty(){
        String url = COMMON_URL + "36/3611.html";
        CityStats.parseCounty(url, new Node());
    }
}