package com.roachfu.tutorial.lambda;

import com.roachfu.tutorial.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * @author roach
 * @date 2018/9/18
 */
public class LambdaTest {

    private List<User> users;

    @Before
    public void init() {
        users = new ArrayList<>();

        User user1 = User.builder()
                .id(1L)
                .name("蒙奇D路飞")
                .age(19)
            .build();

        User user2 = new User(2L, "诺诺罗亚佐罗", 21);

        User user3 = new User();
        user3.setId(3L);
        user3.setName("文斯莫克山治");
        user3.setAge(21);

        users.add(user1);
        users.add(user2);
        users.add(user3);

    }

    /**
     * 过滤大于19岁的
     */
    @Test
    public void testFilter() {
        /* 常规方式 */
        List<User> userList = new ArrayList<>();
        for (User user : users) {
            if (user.getAge() > 19) {
                userList.add(user);
            }
        }
        assertEquals(2, userList.size());

        /* lambda 方式 */
        List<User> list = users.stream().filter(user -> user.getAge() > 19).collect(Collectors.toList());
        assertEquals(2, list.size());
        assertEquals(3, users.size());
    }

    @Test
    public void testMap() {
        /* 常规方式 */
        List<Long> ids = new ArrayList<>();
        for (User user : users) {
            ids.add(user.getId());
        }
        assertEquals(Long.valueOf(1), ids.get(0));

        /* lambda方式 */
        ids = users.stream().map(User::getId).collect(Collectors.toList());
        assertEquals(Long.valueOf(1), ids.get(0));
    }

    @Test
    public void testJoining() {
        /* 常规方式 */
        StringBuilder idSb = new StringBuilder();
        int i = 0;
        for (User user : users) {
            if (i++ > 0) {
                idSb.append(", ");
            }
            idSb.append(user.getId());
        }
        assertEquals("1, 2, 3", idSb.toString());

        /* lambda 方式 */
        String idStr = users.stream().map(user -> user.getId() + "").collect(Collectors.joining(", "));
        assertEquals("1, 2, 3", idStr);
    }

    @Test
    public void testSort(){
        Random random = new Random();
        random.ints().limit(3).sorted().forEach(System.out::println);
    }

    /**
     * list 快速转 map
     */
    @Test
    public void testListToMap(){
        /* 常规方式 */
        Map<Long, User> map = new HashMap<>();
        for (User u : users){
            map.put(u.getId(), u);
        }
        for (Map.Entry<Long, User> entry : map.entrySet()){
            System.out.println(entry.getKey());
        }
        
        /* lambda方式 */
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, user -> user));
        for (Map.Entry<Long, User> entry : userMap.entrySet()){
            System.out.println(entry.getKey());
        }
    }

}
