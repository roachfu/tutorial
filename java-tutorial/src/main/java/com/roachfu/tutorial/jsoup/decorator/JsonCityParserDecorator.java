package com.roachfu.tutorial.jsoup.decorator;

import com.google.gson.Gson;
import com.roachfu.tutorial.jsoup.Node;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author roach
 * @date 2018/8/12 16:59
 */
@Slf4j
public class JsonCityParserDecorator extends CityParserDecorator{

    public JsonCityParserDecorator(ICityParser cityParser) {
        super(cityParser);
    }

    @Override
    public List<Node> parseProvinces(String url) {
        List<Node> provinces = super.parseProvinces(url);
        // 打印json数据
        log.info(new Gson().toJson(provinces));
        return provinces;
    }
}
