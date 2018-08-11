package com.roachfu.tutorial.jsoup;

import lombok.*;

import java.util.List;

/**
 * @author roach
 * @data 2018-08-11
 */

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Node {

    private String name;

    private String code;

    private List<Node> nodes;
}
