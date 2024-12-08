package com.rosy.virosa.utilis;


import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            UtilisTest utilisTest1 = UtilisTest.builder().name("rosy").age(22).build();
            UtilisTest utilisTest2 = UtilisTest.builder().name("vivian").age(21).build();
            List<UtilisTest> utilisTestList = Arrays.asList(utilisTest1, utilisTest2);
            if (CollUtil.isNotEmpty(utilisTestList)) {
                System.out.println(JSON.toJSONString(utilisTestList));
            }
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
        }

    }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UtilisTest {
    String name;
    int age;
}
