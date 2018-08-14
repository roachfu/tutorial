package com.roachfu.tutorial.jsoup.decorator;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.roachfu.tutorial.jsoup.Node;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * sql打印装饰器
 *
 * @author roach
 * @date 2018/8/13 21:09
 */

@Slf4j
public class SqlCityParserDecorator extends CityParserDecorator {

    private static final String SQL = "insert into tb_province_city_county(`name`, `code`, full_spell, easy_spell, initial, parent_code, depth) values";

    public SqlCityParserDecorator(ICityParser cityParser) {
        super(cityParser);
    }

    @Override
    public List<Node> parseProvinces(String url) {
        List<Node> provinces = super.parseProvinces(url);
        // 打印sql
        printSql(provinces);

        return provinces;
    }

    /**
     * 打印省市县sql
     *
     * @param provinces 省市县数据
     */
    private void printSql(List<Node> provinces) {

        for (Node province : provinces) {
            doPrintSql(province.getName(), province.getCode(), "", 1);
            printCitySql(province.getNodes(), province.getCode());
        }
    }

    private void printCitySql(List<Node> cities, String parentCode) {
        if (cities != null && !cities.isEmpty()) {
            for (Node city : cities) {
                doPrintSql(city.getName(), city.getCode(), parentCode, 2);
                printCountySql(city.getNodes(), city.getCode());
            }
        }
    }

    private void printCountySql(List<Node> counties, String parentCode) {
        if (counties != null && !counties.isEmpty()) {
            for (Node county : counties) {
                doPrintSql(county.getName(), county.getCode(), parentCode, 3);
            }
        }
    }

    /**
     * 打印sql
     * <p>
     * insert into tb_province_city_county(`name`, `code`, full_spell, easy_spell, initial, parent_code, depth)
     * values('江西', '36', 'jiangxi', 'jx', 'j', '', '1');
     * </p>
     *
     * @param name       城市名
     * @param code       城市代码
     * @param parentCode 父级城市代码
     * @param depth      等级
     */
    private void doPrintSql(String name, String code, String parentCode, Integer depth) {

        /*
        StringBuilder sb = new StringBuilder(SQL)
                .append("(").append("'").append(name).append("', '")
                .append(code).append("', '")
                .append(PinyinHelper.convertToPinyinString(name, "", PinyinFormat.WITHOUT_TONE)).append("', '")
                .append(PinyinHelper.getShortPinyin(name)).append("', '")
                .append(PinyinHelper.getShortPinyin(name).substring(0, 1)).append("', '")
                .append(parentCode).append("', ")
                .append(depth).append(");")
        */
        try {
            String insertSql = SQL + "('"
                    + name + "', '"
                    + code + "', '"
                    + PinyinHelper.convertToPinyinString(name, "", PinyinFormat.WITHOUT_TONE) + "', '"
                    + PinyinHelper.getShortPinyin(name) + "', '"
                    + PinyinHelper.getShortPinyin(name).substring(0, 1) + "', '"
                    + parentCode + "', "
                    + depth + ");";

            log.info(insertSql);
        } catch (PinyinException e) {
            log.error("拼音解析有误. . .", e);
        }
    }
}
