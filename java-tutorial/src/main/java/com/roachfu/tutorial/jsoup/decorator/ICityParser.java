package com.roachfu.tutorial.jsoup.decorator;

import com.roachfu.tutorial.jsoup.Node;

import java.util.List;

/**
 * @author roach
 * @date 2018/8/12 16:43
 */
public interface ICityParser {

    /**
     * 解析得到省市区数据
     *
     * @param url 请求url
     * @return 城市
     */
    List<Node> parseProvinces(String url);
}
