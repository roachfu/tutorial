package com.roachfu.tutorial.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.roachfu.tutorial.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author roach
 * @date 2018/9/22
 */
public class MultiMapTest {

    private List<User> list = new ArrayList<>();
    @Before
    public void init(){
        User user = new User();
        user.setId(1L);
        user.setName("张三");
        user.setAge(23);
        list.add(user);

        user = new User();
        user.setId(2L);
        user.setName("李四");
        user.setAge(22);
        list.add(user);

        user = new User();
        user.setId(3L);
        user.setName("张三");
        user.setAge(25);
        list.add(user);
    }

    @Test
    public void testMultiMap() {
        Multimap<String, User> multiMap = ArrayListMultimap.create();
        for (User u : list) {
            multiMap.put(u.getName(), u);
        }
        for (Map.Entry<String, Collection<User>> entry : multiMap.asMap().entrySet()){
            System.out.println(entry.getKey());
        }
    }

    @Test
    public void testListToMap(){
        Map<String, List<User>> userMap = new HashMap<>();
        for (User u : list){
            if (userMap.containsKey(u.getName())){
                userMap.get(u.getName()).add(u);
            }else {
                List<User> users = new ArrayList<>();
                users.add(u);
                userMap.put(u.getName(), users);
            }
        }
        for (Map.Entry<String, List<User>> entry : userMap.entrySet()){
            System.out.println(entry.getKey());
        }
    }
}
