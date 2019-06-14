package com.roachfu.tutorial.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author roach
 * @date 2018/8/23
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store implements Comparable<Store>{

    /**
     * 所在市
     */
    private String city;

    /**
     * 编号
     */
    private String number;

    /**
     * 门店名称
     */
    private String name;

    /**
     * 门店地址
     */
    private String address;

    /**
     * 相似度
     */
    private double ratio;

    @Override
    public int compareTo(Store o) {
        if (o == null){
            return -1;
        }

        if (this.getRatio() > o.getRatio()){
            return 1;
        }
        return -1;
    }
}
