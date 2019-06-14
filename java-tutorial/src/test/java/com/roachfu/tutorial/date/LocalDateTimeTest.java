package com.roachfu.tutorial.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import javax.annotation.meta.TypeQualifierDefault;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;

/**
 * @author roach
 * @date 2018/11/5
 */
public class LocalDateTimeTest {

    /**
     * 获取指定时间的时间戳
     */
    @Test
    public void testGetTimestamp(){
        /* LocalDateTime方式 */
        LocalDateTime localDateTime = LocalDateTime.now();
        long startTime = localDateTime.minusMonths(2L)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .toInstant(ZoneOffset.of("+8"))
                .toEpochMilli();
        long endTime = localDateTime.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(0)
                .toInstant(ZoneOffset.of("+8"))
                .toEpochMilli();

        System.out.println("startTime=" + startTime + ", endTime="+endTime);

        /* Calendar方式 */
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println("startTime="+ calendar.getTimeInMillis() + ", endTime="+ cal.getTimeInMillis());
    }

    /**
     * 获取月初月末时间
     */
    public void testGetMonth(){

    }

    @Test
    public void test(){
        LocalDateTime localDateTime = LocalDateTime.now();
        long startTime = localDateTime.withDayOfMonth(10).withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .toInstant(ZoneOffset.of("+8"))
                .toEpochMilli();

        System.out.println(startTime);

        long endTime = localDateTime.withDayOfMonth(10).withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(0)
                .toInstant(ZoneOffset.of("+8"))
                .toEpochMilli();

        System.out.println("startTime=" + startTime + ", endTime="+endTime);
    }

    @Test
    public void testTimestamp(){
        String s = DateFormatUtils.format(1541001600000L, "yyyy-MM-dd HH:mm:ss");
        String s1 = DateFormatUtils.format(1541174399000L, "yyyy-MM-dd HH:mm:ss");
        System.out.println(s);
        System.out.println(s1);

        s = DateFormatUtils.format(1541001600000L, "yyyy-MM-dd HH:mm:ss");
        s1 = DateFormatUtils.format(1541087999000L, "yyyy-MM-dd HH:mm:ss");
        System.out.println(s);
        System.out.println(s1);

        s = DateFormatUtils.format(1541001600000L, "yyyy-MM-dd HH:mm:ss");
        s1 = DateFormatUtils.format(1541087999000L, "yyyy-MM-dd HH:mm:ss");
        System.out.println(s);
        System.out.println(s1);
    }
}
