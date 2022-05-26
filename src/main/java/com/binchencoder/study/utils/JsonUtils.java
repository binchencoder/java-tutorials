package com.binchencoder.study.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * JSON common utilities.
 *
 * @author chenbin
 */
@Slf4j
public final class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final GsonBuilder GSON_BUILDER = new GsonBuilder();

    private JsonUtils() {
    }

    /**
     * Object to JSON string
     *
     * @param obj
     * @return
     * @author chenbin
     */
    public static <T> String objToJson(T obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Failed to parse object to json error", e);
            // pass
        }

        return null;
    }

    public static <T> String formatJson(String json) {
        if (Strings.isNullOrEmpty(json)) {
            return "";
        }

        Gson gson = GSON_BUILDER.create();
        return gson.toJson(json);
    }

    /**
     * List to JSON string
     *
     * @param list
     * @return
     * @author chenbin
     */
    public static <T> String listToJson(List<T> list) {
        return new Gson().toJson(list);
    }

    /**
     * JSON to string list.
     *
     * @param json
     * @return
     * @author chenbin
     */
    @SuppressWarnings("unchecked")
    public static List<String> jsonToList(String json) {
        return new Gson().fromJson(json, List.class);
    }
}
