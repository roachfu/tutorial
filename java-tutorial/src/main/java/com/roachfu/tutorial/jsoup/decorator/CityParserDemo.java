package com.roachfu.tutorial.jsoup.decorator;

/**
 * @author roach
 * @date 2018/8/13 08:44
 */
public class CityParserDemo {

    public static void main(String[] args) {
        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html";

        ICityParser cityParser = new CityParser();

        // 1. 先查经纬度
        ICityParser locationCityParser = new LocationCityParserDecorator(cityParser);
//        locationCityParser.parseProvinces(url);

        // 展示sql
        ICityParser sqlCityParser = new SqlCityParserDecorator(locationCityParser);
//        sqlCityParser.parseProvinces(url);

        // 打印json
        ICityParser jsonCityParser = new JsonCityParserDecorator(sqlCityParser);
        jsonCityParser.parseProvinces(url);
    }
}
