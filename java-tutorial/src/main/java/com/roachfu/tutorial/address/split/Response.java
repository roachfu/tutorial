package com.roachfu.tutorial.address.split;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author roach
 * @date 2018/8/22
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private String target;

    private String source;
}
