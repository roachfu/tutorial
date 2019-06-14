package com.roachfu.tutorial.date;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author roach
 * @date 2019/1/25
 */
public class LocalDateTimeTutorial {

    public static void main(String[] args) {

        // 获取2019-01-01 0:0:0的时间戳
        long start = LocalDateTime.now()
                .withYear(2018)
                .withMonth(5)
                .withDayOfMonth(2)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .toInstant(ZoneOffset.of("+8"))
                .toEpochMilli();

        // 获取2019-01-31 23:59:59的时间戳
        long end = LocalDateTime.now()
                .withYear(2018)
                .withMonth(5)
                .withDayOfMonth(2)
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(0)
                .toInstant(ZoneOffset.of("+8"))
                .toEpochMilli();

        System.out.println("start:" + start + ", end:" + end);
        System.out.println(DateFormatUtils.format(start, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateFormatUtils.format(end, "yyyy-MM-dd HH:mm:ss"));

        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        List<String> subList = list.subList(0, 2);
        System.out.println(subList);
        subList = list.subList(2, 4);
        System.out.println(subList);

    }
}
