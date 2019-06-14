package com.roachfu.tutorial;

import com.roachfu.tutorial.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author roach
 * @date 2018/11/16
 */
public class ComparableTest {

    private Date date;

    private User user;

    private List<User> userList = new ArrayList<>();

    @Before
    public void init() {

        LocalDateTime localDateTime = LocalDateTime.now().withHour(14).withMinute(20).withSecond(0).withNano(0);
        date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        user = User.builder()
                .name("蒙奇D路飞")
                .age(23)
                .userGender(1)
                .userHeight("190cm")
                .build();
        user.setId(1L);
        user.setCreator(666L);
        user.setCreateDate(date);

        User user2 = User.builder()
                .name("诺诺罗亚索隆")
                .age(21)
                .userGender(1)
                .userHeight("211cm")
                .build();
        user2.setId(2L);
        user2.setCreator(666L);
        user2.setCreateDate(new Date());

        User user3 = User.builder()
                .name("文斯莫克山治")
                .age(25)
                .userGender(1)
                .userHeight("210cm")
                .build();
        user3.setId(3L);
        user3.setCreator(666L);
        user3.setCreateDate(new Date());

        userList.add(user);
        userList.add(user2);
        userList.add(user3);
    }

    @Test
    public void test(){
        Collections.sort(userList);
        System.out.println(userList);
    }
}
