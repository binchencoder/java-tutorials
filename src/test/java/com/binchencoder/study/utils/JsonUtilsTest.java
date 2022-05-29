package com.binchencoder.study.utils;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import org.junit.jupiter.api.Test;

public class JsonUtilsTest {

    @Test
    public void objToJson() {
        TrainingParam param = TrainingParam.builder().iterationNum(100).incrementTraining(false)
            .build();

        String json = JsonUtils.objToJson(param);
        System.out.println(json);
        System.out.println(JsonUtils.formatJson(json));

        System.out.println(new String(new JsonStringEncoder().quoteAsString(json)));
    }
}