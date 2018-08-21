package com.roachfu.tutorial.address.split;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author roach
 * @date 2018/8/21
 */
public class AddressResolutionUtil {

    /**
     * 解析地址
     * @author lin
     * @param address
     * @return
     */
    public static List<Map<String,String>> addressResolution(String address){
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m= Pattern.compile(regex).matcher(address);
        String province=null,city=null,county=null,town=null,village=null;
        List<Map<String,String>> table=new ArrayList<Map<String,String>>();
        Map<String,String> row=null;
        while(m.find()){
            row=new LinkedHashMap<String,String>();
            province=m.group("province");
            row.put("province", province==null?"":province.trim());
            city=m.group("city");
            row.put("city", city==null?"":city.trim());
            county=m.group("county");
            row.put("county", county==null?"":county.trim());
            town=m.group("town");
            row.put("town", town==null?"":town.trim());
            village=m.group("village");
            row.put("village", village==null?"":village.trim());
            table.add(row);
        }
        return table;
    }

    public static void main(String[] args) {
        System.out.println(addressResolution("湖北省武汉市洪山区"));

        System.out.println(addressResolution("湖北省武汉市洪山区"));
        System.out.println(addressResolution("湖北省恩施土家族苗族自治州恩施市"));
        System.out.println(addressResolution("北京市市辖区朝阳区"));
        System.out.println(addressResolution("内蒙古自治区兴安盟科尔沁右翼前旗"));
        System.out.println(addressResolution("西藏自治区日喀则地区日喀则市"));
        System.out.println(addressResolution("海南省省直辖县级行政单位中沙群岛的岛礁及其海域"));

        System.out.println(addressResolution("北京市海淀区中关村北大街37号天龙大厦3层"));
        System.out.println(addressResolution("福州市台江区群众路278号源利明珠大厦6楼"));
        System.out.println(addressResolution("北京西城区百万庄大街68号6楼"));
        System.out.println(addressResolution("安徽合肥市庐阳区城区合肥市庐阳区濉溪路财富广场B座西楼1704室"));
        System.out.println(addressResolution("天津西青区杨柳青,中北,精武,大寺镇,环外海泰及外环内天津市西青区中北大道枫香园10号底商"));

    }

}

