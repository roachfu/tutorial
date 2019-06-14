package com.roachfu.tutorial.excel;

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
public class GoDeep {

    /**
     * 门店名称
     */
    private String store;

    /**
     * 地址
     */
    private String address;

    /**
     * 市
     */
    private String city;
}
