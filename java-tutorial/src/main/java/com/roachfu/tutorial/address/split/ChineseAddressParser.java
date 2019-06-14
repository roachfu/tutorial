package com.roachfu.tutorial.address.split;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author roach
 * @date 2018/8/21
 */
public class ChineseAddressParser {

    private static final String CHINESE_REGEX = "[\u4e00-\u9fa5]+";

    private static final String PROVINCE_REGEX = CHINESE_REGEX + "?(省|特区|自治区|特别行政区)";

    private static final String CITY_REGEX = CHINESE_REGEX + "?(市|自治州|地区)";

    private static final String COUNTY_REGEX = CHINESE_REGEX + "?(区|县|市)";

    private static final String STREET_REGEX = CHINESE_REGEX + "?(街道|街|乡|路|道)";

    private static Response parseAddress(String address, String regex) {

        Response response = null;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(address);

        if (matcher.find()) {
            String source = address.substring(matcher.end());
            response = Response.builder()
                    .target(matcher.group())
                    .source(source)
                    .build();
        }

        if (response == null) {
            response = Response.builder()
                    .target("")
                    .source(address)
                    .build();
        }

        return response;
    }

    /**
     * 解析省份
     *
     * @param response
     * @return
     */
    private static Response parseProvince(Response response) {
        if (StringUtils.isBlank(response.getTarget())) {
            String province = ProvinceCity.matchProvince(response.getSource());
            if (province != null) {
                response = Response.builder()
                        .target(province)
                        .source(response.getSource().substring(response.getSource().indexOf(province) + province.length()))
                        .build();
            }
        }
        return response;
    }

    /**
     * 解析城市
     *
     * @param response
     * @return
     */
    private static Response parseCity(Response response) {
        if (StringUtils.isBlank(response.getTarget())) {
            String city = ProvinceCity.matchCity(response.getSource());
            if (city != null) {
                response = Response.builder()
                        .target(city)
                        .source(response.getSource().substring(response.getSource().indexOf(city) + city.length()))
                        .build();
            }
        }
        return response;
    }

    public static Address assembleAddress(String source) {
        Address address = new Address();
        address.setSource(source);

        // 解析省
        Response response = parseAddress(source, PROVINCE_REGEX);
        response = parseProvince(response);
        address.setProvince(response.getTarget());

        // 解析市
        response = parseAddress(response.getSource(), CITY_REGEX);
        response = parseCity(response);
        address.setCity(response.getTarget());

        // 解析县区
        response = parseAddress(response.getSource(), COUNTY_REGEX);
        address.setCounty(response.getTarget());

        // 其他
        response = parseAddress(response.getSource(), STREET_REGEX);
        address.setStreet(response.getTarget());
        address.setOther(response.getSource());

        return address;
    }

    public static void main(String[] args) {
        System.out.println(assembleAddress("江西省上饶市广丰县丰溪街道白堂门3号"));
        System.out.println(assembleAddress("江西广丰县丰溪街道白堂门3号"));
    }
}
