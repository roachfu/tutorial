package com.roachfu.tutorial.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author roach
 * @date 2018/9/18
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable, Comparable<User> {

    private static final long serialVersionUID = -8339562928569639116L;

    private String name;

    private Integer age;

    private Integer userGender;

    private String userHeight;

    @Override
    public int compareTo(User user) {
        /* 排序需要返回不等于0的整数，否则无法排序 */
        if (this.age > user.getAge()){
            return 1;
        }
        return 0;
    }
}
