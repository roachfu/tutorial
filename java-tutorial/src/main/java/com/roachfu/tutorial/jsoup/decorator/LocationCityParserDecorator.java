package com.roachfu.tutorial.jsoup.decorator;

import com.roachfu.tutorial.jsoup.Node;

import java.util.List;

/**
 * @author roach
 * @date 2018/8/13 21:13
 */
public class LocationCityParserDecorator extends CityParserDecorator {

    public LocationCityParserDecorator(ICityParser cityParser) {
        super(cityParser);
    }

    @Override
    public List<Node> parseProvinces(String url) {
        List<Node> provinces = super.parseProvinces(url);
        //TODO 查询出经纬度
        System.out.println("查询出经纬度了. . . ");
        return provinces;
    }
}
