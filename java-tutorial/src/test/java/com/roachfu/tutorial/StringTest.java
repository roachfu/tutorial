package com.roachfu.tutorial;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringTest {

    @Test
    public void test() {
        // 一个对象
        String str = "abc";
    }

    @Test
    public void test2() {
        // 两个对象
        String str = new String("123");
    }

    @Test
    public void test3() {
        // 一个对象
        String str = "123" + "abc";
    }

    @Test
    public void test4() {
        String str = "abc";
        String str2 = "ab" + "c";
        // true
        System.out.println(str == str2);

        String str3 = "ab";
        String str4 = str3 + "c";
        // false
        System.out.println(str == str4);
    }

    public static void main(String[] args) {
        System.out.println(DateFormatUtils.format(1546272000000L, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateFormatUtils.format(1548950399999L, "yyyy-MM-dd HH:mm:ss"));

        String auth = "{\"access_from\":\"1\",\"client_sign\":\"{2d21ccc0-8fba-4eca-b96c-9490799ef944}\",\"lang\":\"en-US\",\"timestamp\":\"${__time(/1000,)}\"}";
        String user = "{\"username\":\"liu02@ws.cn\",\"password\":\"123456\",\"request_token\":\"cfbabf81cded1e17b55b897b12158d40\"}";

        JSONObject authJson = JSONObject.parseObject(auth);
        JSONObject userJson = JSONObject.parseObject(user);

        authJson.putAll(userJson);
        Map data = JSONObject.parseObject(authJson.toJSONString());

        BASE64Encoder encoder = new BASE64Encoder();
        data.put("password", encoder.encode(userJson.getString("password").getBytes()));

        List entryList = new ArrayList<>(data.entrySet());
        Collections.sort(entryList, (o1, o2) -> {
            Map.Entry entry1 = (Map.Entry) o1;
            Map.Entry entry2 = (Map.Entry) o2;
            return entry1.getKey().toString().compareTo(entry2.getKey().toString());
        });

        Map result = new LinkedHashMap<>();
        for (Object object : entryList) {
            Map.Entry entry = (Map.Entry) object;
            result.put(entry.getKey(), entry.getValue());
        }

        System.out.println(JSONObject.toJSONString(result));
        System.out.println(JSONObject.toJSONString(result, true));

    }

    @Test
    public void test5() {
        String str = "196|197|198|199|200|201|202|203|204|205|206|207|241|242|243|244|245|246|253|254|255|256|257|258|383|384|385|534|535|536|537|538|539|540|541|542|543|544|545|546|547|548|549|550|551|552|553|554|555|822|823|824|825|826|827|828|829|830|831|832|851|852|853|854|855|856|857|858|859|860|944|945|946|947|948";

        System.out.println(str.replace("|", ","));

    }

    @Test
    public void test6() {
        String[] strs = StringUtils.split("1, 2, 3", ", ");
        System.out.println(strs.length);


    }
}
