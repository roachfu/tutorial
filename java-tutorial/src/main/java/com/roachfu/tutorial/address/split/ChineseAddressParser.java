package com.roachfu.tutorial.address.split;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author roach
 * @date 2018/8/21
 */
public class ChineseAddressParser {

    private static final String CHINESE_REGEX = "[\u4e00-\u9fa5]";
    private static final Pattern COUNTRY_PATTERN = Pattern.compile("中国");
    private static final Pattern ms_Pattern_jinjiao = Pattern.compile("近郊");
    static final Pattern ms_Pattern_sheng = Pattern.compile(CHINESE_REGEX + "+?省");
    static final Pattern ms_Pattern_autonomous  = Pattern.compile(CHINESE_REGEX + "+?自治区");
    static final Pattern ms_Pattern_shi = Pattern.compile(CHINESE_REGEX + "+?市(?!场)");
    static final Pattern ms_Pattern_qu = Pattern.compile(CHINESE_REGEX + "+?区");
    static final Pattern ms_Pattern_xian = Pattern.compile(CHINESE_REGEX + "+?县");
    static final Pattern ms_Pattern_xiang = Pattern.compile(CHINESE_REGEX + "+?乡");
    static final Pattern ms_Pattern_dao = Pattern.compile(CHINESE_REGEX + "+?道");
    static final Pattern ms_Pattern_hutong = Pattern.compile(CHINESE_REGEX + "+?胡同");
    static final Pattern ms_Pattern_nongtang = Pattern.compile(CHINESE_REGEX + "+?弄堂");
    static final Pattern ms_Pattern_jie = Pattern.compile(CHINESE_REGEX + "+?街");
    static final Pattern ms_Pattern_xiangg = Pattern.compile(CHINESE_REGEX + "+?巷");
    static final Pattern ms_Pattern_lu = Pattern.compile(CHINESE_REGEX + "+?路");
    static final Pattern ms_Pattern_cun = Pattern.compile(CHINESE_REGEX + "+?村");
    static final Pattern ms_Pattern_zhen = Pattern.compile(CHINESE_REGEX + "+?镇");
    static final Pattern ms_Pattern_hao = Pattern.compile("[甲_乙_丙_0-9_-]+?号");
    static final Pattern ms_Pattern_point = Pattern.compile(CHINESE_REGEX + "+?(?:广场|酒店|饭店|宾馆|中心|大厦|百货|大楼|商城)");
    static final Pattern ms_Pattern_ditie = Pattern.compile("地铁" + CHINESE_REGEX + "+?线(?:" + CHINESE_REGEX + "+?站)?");
    static final Pattern ms_Pattern_province = Pattern.compile(CHINESE_REGEX + "{2,10}?(?:省|特区|自治区|特别行政区)");
    static final Pattern ms_Pattern_city = Pattern.compile(CHINESE_REGEX + "+?(?:市|地区|自治州)");
    static final Pattern ms_Pattern_county = Pattern.compile(CHINESE_REGEX + "+?(?:乡|县)");
    static final Pattern ms_Pattern_street = Pattern.compile(CHINESE_REGEX + "+?街道");
    static final Pattern ms_Pattern_road = Pattern.compile(CHINESE_REGEX + "+?(?:胡同|弄堂|街|巷|路|道)");
    static final Pattern ms_Pattern_roadnear = Pattern.compile("(?<=近)" + CHINESE_REGEX + "+?(?:胡同|弄堂|街|巷|路|道)");
    static final Pattern ms_Pattern_ip = Pattern.compile(CHINESE_REGEX + "+?(?:开发区|科技区|园区)");
    static final Pattern ms_Pattern_zone = Pattern.compile(CHINESE_REGEX + "+?(?:小区|社区|新村)");
    static final Pattern ms_Pattern_village = Pattern.compile(CHINESE_REGEX + "+?村");
    static final Pattern ms_Pattern_town = Pattern.compile(CHINESE_REGEX + "+?镇");
    static final Pattern ms_Pattern_number = Pattern.compile("[甲_乙_丙_0-9_-]+号");
    static final Pattern ms_Pattern_plaza = Pattern.compile(CHINESE_REGEX + "+?(?:广场|酒店|饭店|宾馆|中心|大厦|百货|大楼|商城)");
    static final Pattern ms_Pattern_underground = Pattern.compile("地铁" + CHINESE_REGEX + "+?线(?:" + CHINESE_REGEX + "+?站)?");
    static final Splitter ms_splitter_guo = new Splitter(COUNTRY_PATTERN, new Pattern[]{COUNTRY_PATTERN});
    static final Splitter ms_splitter_sheng = new Splitter(ms_Pattern_sheng, new Pattern[]{ms_Pattern_province});
    static final Splitter ms_splitter_autonomous = new Splitter(ms_Pattern_autonomous, new Pattern[]{ms_Pattern_province});
    static final Splitter ms_splitter_shi = new Splitter(ms_Pattern_shi, new Pattern[]{ms_Pattern_city}, false);
    static final Splitter ms_splitter_jinjiao = new Splitter(ms_Pattern_jinjiao, new Pattern[]{ms_Pattern_jinjiao});
    static final Splitter ms_splitter_qu = new Splitter(ms_Pattern_qu, new Pattern[]{ms_Pattern_province, ms_Pattern_city, ms_Pattern_zone, ms_Pattern_ip, ms_Pattern_qu}, false);
    static final Splitter ms_splitter_xiang = new Splitter(ms_Pattern_xiang, new Pattern[]{ms_Pattern_county});
    static final Splitter ms_splitter_xian = new Splitter(ms_Pattern_xian, new Pattern[]{ms_Pattern_county});
    static final Splitter ms_splitter_dao = new Splitter(ms_Pattern_dao, new Pattern[]{ms_Pattern_street, ms_Pattern_roadnear, ms_Pattern_road}, false);
    static final Splitter ms_splitter_hutong = new Splitter(ms_Pattern_hutong, new Pattern[]{ms_Pattern_roadnear, ms_Pattern_road}, false);
    static final Splitter ms_splitter_nongtang = new Splitter(ms_Pattern_nongtang, new Pattern[]{ms_Pattern_roadnear, ms_Pattern_road}, false);
    static final Splitter ms_splitter_jie = new Splitter(ms_Pattern_jie, new Pattern[]{ms_Pattern_roadnear, ms_Pattern_road}, false);
    static final Splitter ms_splitter_lu = new Splitter(ms_Pattern_lu, new Pattern[]{ms_Pattern_roadnear, ms_Pattern_road}, false);
    static final Splitter ms_splitter_xiangg = new Splitter(ms_Pattern_xiangg, new Pattern[]{ms_Pattern_roadnear, ms_Pattern_road}, false);
    static final Splitter ms_splitter_cun = new Splitter(ms_Pattern_cun, new Pattern[]{ms_Pattern_zone, ms_Pattern_village});
    static final Splitter ms_splitter_zhen = new Splitter(ms_Pattern_zhen, new Pattern[]{ms_Pattern_town});
    static final Splitter ms_splitter_hao = new Splitter(ms_Pattern_hao, new Pattern[]{ms_Pattern_number});
    static final Splitter ms_splitter_point = new Splitter(ms_Pattern_point, new Pattern[]{ms_Pattern_plaza});
    static final Splitter ms_splitter_ditie = new Splitter(ms_Pattern_ditie, new Pattern[]{ms_Pattern_underground});
    static final Splitter[] DEFAULT_SPLITTER = new Splitter[]{
            ms_splitter_guo,
            ms_splitter_sheng,
            ms_splitter_autonomous,
            ms_splitter_shi,
            ms_splitter_qu,
            ms_splitter_xiang,
            ms_splitter_xian,
            ms_splitter_dao,
            ms_splitter_hutong,
            ms_splitter_nongtang,
            ms_splitter_jie,
            ms_splitter_xiangg,
            ms_splitter_lu,
            ms_splitter_cun,
            ms_splitter_zhen,
            ms_splitter_hao,
            ms_splitter_point,
            ms_splitter_ditie,
            ms_splitter_jinjiao
    };

    private static LinkedHashMap<Integer, Splitter> split(String src) {
        LinkedHashMap<Integer, Splitter> splitterDic = new LinkedHashMap<>();
        for (Splitter s : DEFAULT_SPLITTER) {
            Matcher m = s.getPattern().matcher(src);
            while (m.find()) {
                splitterDic.put(m.start() + m.group().length(), s);
                if (s.isFlag()) {
                    break;
                }
            }
        }
        return splitterDic;
    }

    private static ArrayList<Segment> recognize(String src, LinkedHashMap<Integer, Splitter> splitterdic) {
        Segment s;
        int index = 0;
        ArrayList<Segment> segments = new ArrayList<>();
        if (src.length() > 0) {
            for (Integer key : splitterdic.keySet()) {
                Splitter value = splitterdic.get(key);
                if (key > index && key < src.length()) {
                    for (Pattern r : value.getPatterns()) {
                        s = segmentRecognize(src.substring(index, key), r);
                        if (s != null) {
                            segments.add(s);
                            break;
                        }
                    }
                    index = key;
                }
            }
        }
        return segments;
    }

    private static Segment segmentRecognize(String src, Pattern r) {
        Matcher m = r.matcher(src);
        if (m.matches()) {
            return new Segment(m.group(), r);
        } else {
            return null;
        }
    }

    private static ArrayList<String> segmentsGetStringListForPattern(ArrayList<Segment> segments, Pattern r) {
        ArrayList<String> ss = new ArrayList<>();
        for (Iterator<Segment> it = segments.iterator(); it.hasNext(); ) {
            Segment s = it.next();
            if (s.getPattern() == r) {
                ss.add(s.getValue());
            }
        }
        return ss;
    }

    private static String segmentsGetStringForPattern(ArrayList<Segment> segments, Pattern r) {
        for (Iterator<Segment> it = segments.iterator(); it.hasNext(); ) {
            Segment s = it.next();
            if (s.getPattern() == r) {
                return s.getValue();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(ChineseAddressParser.parse("北京市海淀区中关村北大街37号天龙大厦3层"));
        System.out.println(ChineseAddressParser.parse("福建省福州市台江区群众路278号源利明珠大厦6楼"));
        System.out.println(ChineseAddressParser.parse("北京西城区百万庄大街68号6楼"));
        System.out.println(ChineseAddressParser.parse("安徽合肥市庐阳区城区合肥市庐阳区濉溪路财富广场B座西楼1704室"));
        System.out.println(ChineseAddressParser.parse("中国新疆维吾尔自治区合肥市庐阳区城区合肥市庐阳区濉溪路财富广场B座西楼1704室"));
    }


    public static ChineseAddress parse(String source) {
        source = source.replace(".", "").replace("，", "").replace(",", "");
        ArrayList<Segment> segments = recognize(source, split(source));

        ArrayList<String> roads = segmentsGetStringListForPattern(segments, ms_Pattern_road);
        ArrayList<String> near = segmentsGetStringListForPattern(segments, ms_Pattern_roadnear);
        roads.addAll(near);

        return ChineseAddress.builder()
                .source(source)
                .nation(segmentsGetStringForPattern(segments, COUNTRY_PATTERN))
                .province(segmentsGetStringForPattern(segments, ms_Pattern_province))
                .city(segmentsGetStringForPattern(segments, ms_Pattern_city))
                .district(segmentsGetStringForPattern(segments, ms_Pattern_qu))
                .county(segmentsGetStringForPattern(segments, ms_Pattern_county))
                .street(segmentsGetStringForPattern(segments, ms_Pattern_street))
                .underground(segmentsGetStringForPattern(segments, ms_Pattern_underground))
                .number(segmentsGetStringForPattern(segments, ms_Pattern_number))
                .plaza(segmentsGetStringForPattern(segments, ms_Pattern_plaza))
                .ip(segmentsGetStringForPattern(segments, ms_Pattern_ip))
                .town(segmentsGetStringForPattern(segments, ms_Pattern_town))
                .village(segmentsGetStringForPattern(segments, ms_Pattern_village))
                .roads(roads)
                .build();
    }
}
