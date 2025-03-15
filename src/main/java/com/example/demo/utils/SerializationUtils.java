package com.example.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SerializationUtils {

    @SneakyThrows
    public static <T> List<T> deserializeJsonToList(String json, Class<T> tClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, tClass));
    }

    @SneakyThrows
    public static <T> T deserializeJsonToObject(String json, Class<T> tClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, tClass);
    }
}
