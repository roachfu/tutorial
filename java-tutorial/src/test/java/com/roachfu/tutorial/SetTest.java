package com.roachfu.tutorial;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author roach
 * @date 2018/11/12
 */
public class SetTest {

    @Test
    public void testSet(){

        Set<String> set = new HashSet<>();
        set.add("665_190201");
        set.add("665_190202");
        set.add("665_190203");
        set.add("665_190204");
        System.out.println(set);

        List<String> list = new ArrayList<>();
        list.add("665_190201");
        list.add("665_190202");
        list.add("665_190203");
        list.add("665_190204");
        set = new LinkedHashSet<>(list);
        System.out.println(set);

        list = new ArrayList<>(set);
        System.out.println(list);

    }

    @Test
    public void test(){
        Set<Integer> set = new HashSet<>();
        System.out.println(set.stream().map(id -> id + "").collect(Collectors.joining(",")));

        set.add(1);
        set.add(2);
        System.out.println(set.stream().map(id -> id + "").collect(Collectors.joining(",")));

    }
}
