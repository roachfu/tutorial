package com.roachfu.tutorial.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author roach
 * @date 2018/11/9
 */

@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -1185103649829145304L;

    private Long id;

    private String name;

    private Integer age;

    private Integer userGender;

    private String userHeight;

    private Date createDate;

    private Long creator;

}
