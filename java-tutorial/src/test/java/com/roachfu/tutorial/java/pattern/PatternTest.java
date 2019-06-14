package com.roachfu.tutorial.java.pattern;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

/**
 * @author roach
 * @date 2018/8/22
 */
public class PatternTest {

    /**
     * 只有当完全匹配时返回true
     */
    @Test
    public void testMatches() {
        String regex = "^1[3|4|5|7|8|9][0-9]{9}$";

        assertTrue(Pattern.matches(regex, "13523451234"));
    }

    /**
     * Matcher find() 方法：部分匹配或完全匹配时都返回true
     */
    @Test
    public void testFind() {
        String regex = "[\u4e00-\u9fa5]{2,10}?(省|特区|自治区|特别行政区)";
        String sourceAddress = "福建省福州市台江区群众路278号源利明珠大厦6楼";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceAddress);

        while(matcher.find()){
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println(matcher.group());
            String address = sourceAddress.substring(matcher.start(), matcher.end());
            System.out.println(address);
            System.out.println(sourceAddress.substring(matcher.end()));
        }

    }
}
