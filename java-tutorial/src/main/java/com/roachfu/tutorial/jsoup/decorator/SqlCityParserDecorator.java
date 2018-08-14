package com.roachfu.tutorial.jsoup.decorator;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.roachfu.tutorial.jsoup.Node;

import java.util.List;

/**
 * @author roach
 * @date 2018/8/13 21:09
 */
public class SqlCityParserDecorator extends CityParserDecorator {

    public SqlCityParserDecorator(ICityParser cityParser) {
        super(cityParser);
    }

    @Override
    public List<Node> parseProvinces(String url) {
        List<Node> provinces = super.parseProvinces(url);
        // TODO 打印sql
        try {
            printSql(provinces);
        } catch (PinyinException e) {
            System.out.println("拼音解析有误. . .");
            e.printStackTrace();
        }
        return provinces;
    }

    /**
     * <p>
     *     insert into tb_province_city_county(`name`, `code`, full_spell, easy_spell, initial, parent_code, depth)
     *      values('江西', '36', 'jiangxi', 'jx', 'j', '', '1');
     * </p>
     * @param provinces
     */
    private void printSql(List<Node> provinces) throws PinyinException {

        for (Node province : provinces){
            doPrintSql(province.getName(), province.getCode(), "", 1);
            if (province.getNodes() != null && !province.getNodes().isEmpty()){
                for (Node city : province.getNodes()){
                    doPrintSql(city.getName(), city.getCode(), province.getCode(), 2);
                    if (city.getNodes() != null && !city.getNodes().isEmpty()){
                        for (Node county : city.getNodes()) {
                            doPrintSql(county.getName(), county.getCode(), city.getCode(), 3);
                        }
                    }
                }
            }
        }
    }

    private static final String SQL = "insert into tb_province_city_county(`name`, `code`, full_spell, easy_spell, initial, parent_code, depth) values";

    private void doPrintSql(String name, String code, String parentCode, Integer depth) throws PinyinException {

        StringBuilder sb = new StringBuilder(SQL)
                .append("(").append("'").append(name).append("', '")
                .append(code).append("', '")
                .append(PinyinHelper.convertToPinyinString(name, "", PinyinFormat.WITHOUT_TONE)).append("', '")
                .append(PinyinHelper.getShortPinyin(name)).append("', '")
                .append(PinyinHelper.getShortPinyin(name).substring(0, 1)).append("', '")
                .append(parentCode).append("', ")
                .append(depth).append(");");

        System.out.println(sb);
    }
}
