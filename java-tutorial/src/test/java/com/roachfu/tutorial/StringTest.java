package com.roachfu.tutorial;

import org.junit.Test;

public class StringTest {

    @Test
    public void test(){
        // 一个对象
        String str = "abc";
    }

    @Test
    public void test2(){
        // 两个对象
        String str = new String("123");
    }

    @Test
    public void test3(){
        // 一个对象
        String str = "123" + "abc";
    }

    @Test
    public void test4(){
        String str = "abc";
        String str2 = "ab" + "c";
        // true
        System.out.println(str == str2);

        String str3 = "ab";
        String str4 = str3 + "c";
        // false
        System.out.println(str == str4);
    }
}
