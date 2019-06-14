package com.roachfu.tutorial.common;

import com.roachfu.tutorial.entity.Group;
import com.roachfu.tutorial.entity.User;
import org.junit.Before;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author roach
 * @date 2018/12/11
 */
public class AbstractCommonTest {

    protected User user;
    protected List<User> userList = new ArrayList<>();
    protected Group group;

    @Before
    public void init() {

        LocalDateTime localDateTime = LocalDateTime.now().withHour(14).withMinute(20).withSecond(0).withNano(0);
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

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

        group = new Group();
        group.setId(1L);
        group.setName("草帽海贼团");
        group.setUserList(userList);
    }

}
