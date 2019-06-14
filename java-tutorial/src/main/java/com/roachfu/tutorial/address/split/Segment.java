package com.roachfu.tutorial.address.split;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * @author roach
 * @date 2018/8/21
 */

@Data
@AllArgsConstructor
public class Segment{

    /**
     * 正则匹配到的值
     */
    private String value;

    /**
     * 对应的正则
     */
    private Pattern pattern;
}
