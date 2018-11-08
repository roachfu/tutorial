package com.roachfu.tutorial.date;

import org.junit.Test;

import java.time.LocalDate;

/**
 * @author roach
 * @date 2018/10/26
 */
public class LocalDateTest {

    @Test
    public void test(){
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(-1);
        System.out.println(localDate.toString());
        localDate = localDate.minusDays(-1);
        System.out.println(localDate);
    }
}
