package com.roachfu.tutorial.address.split;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.regex.Pattern;

/**
 * @author roach
 * @date 2018/8/21
 */

@Data
@AllArgsConstructor
public class Segment {

    private String value;

    private Pattern pattern;
}
