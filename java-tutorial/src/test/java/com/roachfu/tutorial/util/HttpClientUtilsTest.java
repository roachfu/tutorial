package com.roachfu.tutorial.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author roach
 * @date 2018/8/14 08:20
 */
public class HttpClientUtilsTest {

    @Test
    public void test(){
        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/36.html";
        String html = HttpClientUtils.httpGetRequest(url, "GBK");
        System.out.println(html);
    }

}