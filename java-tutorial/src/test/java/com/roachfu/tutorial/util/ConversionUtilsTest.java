package com.roachfu.tutorial.util;

import com.roachfu.tutorial.entity.User;
import com.roachfu.tutorial.entity.UserDTO;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author roach
 * @date 2018/11/9
 */
public class ConversionUtilsTest {

    private Date date;

    private Map<String, Object> sourceMap = new HashMap<>(16, 1);

    private User user;

    private List<User> userList = new ArrayList<>();

    @Before
    public void init() {

        LocalDateTime localDateTime = LocalDateTime.now().withHour(14).withMinute(20).withSecond(0).withNano(0);
        date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        sourceMap.put("id", 1L);
        sourceMap.put("name", "蒙奇D路飞");
        sourceMap.put("age", 19);
        sourceMap.put("userGender", 1);
        sourceMap.put("userHeight", "190cm");
        sourceMap.put("creator", 666L);
        sourceMap.put("createDate", date);

        user = User.builder()
                .name("蒙奇D路飞")
                .age(19)
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
                .age(21)
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
    public void testOf() {
        UserDTO userDTO = ConversionUtils.of(user, UserDTO.class);

        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getAge(), user.getAge());
        assertEquals(userDTO.getUserGender(), user.getUserGender());
        assertEquals(userDTO.getUserHeight(), user.getUserHeight());
        assertEquals(userDTO.getCreator(), user.getCreator());
        assertEquals(userDTO.getCreateDate(), user.getCreateDate());

        assertNotEquals(userDTO, user);
    }

    @Test
    public void testObjectToMap() {
        Map<String, Object> target = new HashMap<>(16, 1);
        ConversionUtils.objectToMap(user, target);

        assertEquals(target.get("id"), 1L);
        assertEquals(target.get("name"), "蒙奇D路飞");
        assertEquals(target.get("age"), 19);
        assertEquals(target.get("userGender"), 1);
        assertEquals(target.get("userHeight"), "190cm");
        assertEquals(target.get("creator"), 666L);
        assertEquals(target.get("createDate"), date);

        assertEquals(target, sourceMap);
    }

    @Test
    public void testOfMap() {
        Map<String, Object> target = ConversionUtils.ofMap(user);

        assertEquals(target.get("id"), 1L);
        assertEquals(target.get("name"), "蒙奇D路飞");
        assertEquals(target.get("age"), 19);
        assertEquals(target.get("userGender"), 1);
        assertEquals(target.get("userHeight"), "190cm");
        assertEquals(target.get("creator"), 666L);
        assertEquals(target.get("createDate"), date);

        assertEquals(target, sourceMap);
    }

    @Test
    public void testMapToObject() {
        User newUser = new User();
        ConversionUtils.mapToObject(sourceMap, newUser);

        assertEquals(newUser, user);
    }

    @Test
    public void testOfObject() {
        User newUser = ConversionUtils.ofObject(sourceMap, User.class);

        assertEquals(newUser, user);
    }

    @Test
    public void testListToList() {

        List<UserDTO> userDTOList = new ArrayList<>(userList.size());
        ConversionUtils.listToList(userList, userDTOList, UserDTO.class);

        assertEquals(userDTOList.size(), userList.size());
        assertEquals(userDTOList.get(0).getId(), userList.get(0).getId());
    }

    @Test
    public void testToList() {
        List<UserDTO> userDTOList = ConversionUtils.toList(userList, UserDTO.class);

        assertEquals(userDTOList.size(), userList.size());
        assertEquals(userDTOList.get(0).getId(), userList.get(0).getId());
    }

    @Test
    public void testListToMapList() {
        List<Map<String, Object>> targets = new ArrayList<>();
        ConversionUtils.listToMapList(userList, targets);

        assertEquals(targets.size(), userList.size());
        assertEquals(targets.get(0), sourceMap);
    }

    @Test
    public void testOfMapList() {
        List<Map<String, Object>> targets = ConversionUtils.ofMapList(userList);

        assertEquals(targets.size(), userList.size());
        assertEquals(targets.get(0), sourceMap);
    }

    @Test
    public void testMapListToList() {
        List<Map<String, Object>> sources = ConversionUtils.ofMapList(userList);

        List<User> users = new ArrayList<>();
        ConversionUtils.mapListToList(sources, users, User.class);

        assertEquals(users.size(), sources.size());
        assertEquals(users.get(0), userList.get(0));
        assertEquals(users.get(1), userList.get(1));
        assertEquals(users.get(2), userList.get(2));
    }

    @Test
    public void testOfList() {
        List<Map<String, Object>> sources = ConversionUtils.ofMapList(userList);

        List<User> users = ConversionUtils.ofList(sources, User.class);

        assertEquals(users.size(), sources.size());
        assertEquals(users.get(0), userList.get(0));
        assertEquals(users.get(1), userList.get(1));
        assertEquals(users.get(2), userList.get(2));
    }


}

