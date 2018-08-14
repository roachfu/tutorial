package com.roachfu.tutorial.jsoup.decorator;

import com.roachfu.tutorial.jsoup.Node;

import java.util.List;

/**
 * @author roach
 * @date 2018/8/12 16:55
 */
public class CityParserDecorator implements ICityParser {

    private ICityParser cityParser;

    public CityParserDecorator(ICityParser cityParser){
        this.cityParser = cityParser;
    }

    @Override
    public List<Node> parseProvinces(String url) {
        return this.cityParser.parseProvinces(url);
    }
}
