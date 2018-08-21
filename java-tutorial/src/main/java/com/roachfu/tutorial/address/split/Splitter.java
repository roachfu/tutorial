package com.roachfu.tutorial.address.split;

import lombok.Data;

import java.util.regex.Pattern;

/**
 * 地址分解器
 *
 * @author roach
 * @date 2018/8/21
 */
@Data
public class Splitter {

    private Pattern pattern;

    private Pattern[] patterns;

    private boolean flag = true;

    public Splitter(Pattern pattern) {
        this.pattern = pattern;
    }

    public Splitter(Pattern pattern, Pattern[] patterns) {
        this.pattern = pattern;
        this.patterns = patterns;
    }

    public Splitter(Pattern pattern, Pattern[] patterns, boolean flag) {
        this.pattern = pattern;
        this.flag = flag;
        this.patterns = patterns;
    }

}
