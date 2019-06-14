package com.roachfu.tutorial.address.split;

import lombok.Data;

/**
 * @author roach
 * @date 2018/8/22
 */

@Data
public class Address {

    /**
     * 源地址
     */
    private String source;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

    /**
     * 街道
     */
    private String street;

    /**
     * 其他
     */
    private String other;
}
