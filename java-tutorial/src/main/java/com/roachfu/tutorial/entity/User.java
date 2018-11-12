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
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8339562928569639116L;

    private String name;

    private Integer age;

    private Integer userGender;

    private String userHeight;
}
