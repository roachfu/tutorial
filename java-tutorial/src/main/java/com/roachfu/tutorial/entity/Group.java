package com.roachfu.tutorial.entity;

import lombok.Data;

import java.util.List;

/**
 * 组
 *
 * @author roach
 * @date 2018/12/12
 */
@Data
public class Group {

    private Long id;

    /**
     * 组名
     */
    private String name;

    /**
     * 对应的用户组
     */
    private List<User> userList;
}
