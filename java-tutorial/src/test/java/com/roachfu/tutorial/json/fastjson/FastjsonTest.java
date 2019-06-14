package com.roachfu.tutorial.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.roachfu.tutorial.common.AbstractCommonTest;
import com.roachfu.tutorial.entity.Group;
import org.junit.Test;

/**
 * @author roach
 * @date 2018/12/11
 */
public class FastjsonTest extends AbstractCommonTest {


    @Test
    public void test(){
        String groupJson = JSON.toJSONString(group);
        System.out.println(groupJson);

        Group group1 = JSON.parseObject(groupJson, new TypeReference<Group>(){});
        System.out.println(group1.getName());
        System.out.println(group1.getUserList().get(0).getName());
    }


}
