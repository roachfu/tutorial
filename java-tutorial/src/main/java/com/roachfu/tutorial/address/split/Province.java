package com.roachfu.tutorial.address.split;

/**
 * @author roach
 * @date 2018/8/21
 */
public class Province {

    private static final String[] PROVINCES = {"北京", "天津", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "上海", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "广西壮族", "海南", "重庆", "四川", "贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏回族", "新疆维吾尔"};

    private Province() {
    }

    /**
     * 匹配字符串中的省份
     * <p>
     * 为空返回null
     * </p>
     *
     * @param address 地址
     * @return
     */
    public static String matchProvince(String address) {
        for (String province : PROVINCES) {
            if (address.contains(province)) {
                return province;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String province = Province.matchProvince("安徽合肥市庐阳区城区合肥市庐阳区濉溪路财富广场B座西楼1704室");
        System.out.println(province);
    }

}
