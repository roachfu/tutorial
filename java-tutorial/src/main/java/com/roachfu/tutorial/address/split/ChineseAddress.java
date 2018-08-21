package com.roachfu.tutorial.address.split;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author roach
 * @date 2018/8/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChineseAddress {

    private String source;
    private String nation;
    private String province;
    private String city;
    private String county;
    private String district;
    private String street;
    private List<String> roads;
    private String number;
    private String plaza;
    private String ip;
    private String town;
    private String village;
    private String zone;
    private String underground;
    private List<String> notes;
    private List<String> noises;
    private static final String SEPARATOR = System.getProperty("line.separator");

    @Override
    public String toString() {
        String s = "src: " + source + SEPARATOR;
        if (nation != null) {
            s = s + "nat: " + nation + SEPARATOR;
        }
        if (province != null) {
            s = s + "pro: " + province + SEPARATOR;
        }
        if (city != null) {
            s = s + "cit: " + city + SEPARATOR;
        }
        if (county != null) {
            s = s + "cou: " + county + SEPARATOR;
        }
        if (district != null) {
            s = s + "dis: " + district + SEPARATOR;
        }
        if (street != null) {
            s = s + "str: " + street + SEPARATOR;
        }
        if (number != null) {
            s = s + "num: " + number + SEPARATOR;
        }
        if (plaza != null) {
            s = s + "pla: " + plaza + SEPARATOR;
        }
        if (ip != null) {
            s = s + "idp: " + ip + SEPARATOR;
        }
        if (town != null) {
            s = s + "twn: " + town + SEPARATOR;
        }
        if (village != null) {
            s = s + "vil: " + village + SEPARATOR;
        }
        if (zone != null) {
            s = s + "zon: " + zone + SEPARATOR;
        }
        if (underground != null) {
            s = s + "udg: " + underground + SEPARATOR;
        }
        if (roads != null) {
            s = s + "rod: ";
            for (int i = 0; i < roads.size(); i++) {
                String r = roads.get(i);
                if (r == roads.get(0)) {
                    s = s + r;
                } else {
                    s = s + " / " + r;
                }
            }
            s = s + SEPARATOR;
        }
        if (notes != null) {
            s = s + "not: ";
            for (int i = 0; i < notes.size(); i++) {
                String n = notes.get(i);
                if (n == roads.get(0)) {
                    s = s + n;
                } else {
                    s = s + " / " + n;
                }
            }
            s = s + SEPARATOR;
        }
        if (noises != null) {
            s = s + "noi: ";
            for (int i = 0; i < noises.size(); i++) {
                s = s + noises.get(i) + " / ";
            }
            s = s + SEPARATOR;
        }
        return s;
    }

}
