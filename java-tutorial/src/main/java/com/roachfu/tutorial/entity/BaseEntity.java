package com.roachfu.tutorial.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author roach
 * @date 2018/11/9
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntity extends Id{

    private Date createDate;

    private Long creator;
}
