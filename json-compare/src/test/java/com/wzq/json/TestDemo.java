package com.wzq.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.wzq.json.bean.Person;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TestDemo {

    @Test
    public void testFastJson() {
        // 构造对象
        Person person = new Person();
        person.setId(99999);
        person.setUsername("Happyjava");
        person.setAddress("广东省广州市，上海市，北京市，广东省深圳市，浙江省杭州市，");
        person.setAge(100);
        // Java对象转化成为json字符串
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String string = JSON.toJSONString(person);
        }
        System.out.println("Java对象转化成为json字符串耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 集合对象转化成为json字符串
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(person);
        }
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String string = JSON.toJSONString(list);
        }
        System.out.println("集合对象转化成为json字符串耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 字符串转化成json对象
        start = System.currentTimeMillis();
        String jsonsString = JSON.toJSONString(person);
        for (int i = 0; i < 1000000; i++) {
            JSONObject jsonObject = JSON.parseObject(jsonsString);
        }
        System.out.println("字符串转化成json对象耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 字符串转化成java对象
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Person person1 = JSON.parseObject(jsonsString, Person.class);
        }
        System.out.println("字符串转化成java对象耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 字符串转化为json数组
        start = System.currentTimeMillis();
        String jsonArrayStr = JSON.toJSONString(list);
        for (int i = 0; i < 1000000; i++) {
            JSONArray objects = JSON.parseArray(jsonArrayStr);
        }
        System.out.println("字符串转化为json数组耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 字符串转化成为java集合
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            List<Person> list1 = JSON.parseArray(jsonArrayStr, Person.class);
        }
        System.out.println("字符串转化成为java集合耗时：" + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    public void testGson() {
        Gson gson = new Gson();
        // 构造对象
        Person person = new Person();
        person.setId(99999);
        person.setUsername("Happyjava");
        person.setAddress("广东省广州市，上海市，北京市，广东省深圳市，浙江省杭州市，");
        person.setAge(100);
        // Java对象转化成为json字符串
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String string = gson.toJson(person);
        }
        System.out.println("Java对象转化成为json字符串耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 集合对象转化成为json字符串
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(person);
        }
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String string = gson.toJson(list);
        }
        System.out.println("集合对象转化成为json字符串耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 字符串转化成json对象
        start = System.currentTimeMillis();
        String jsonsString = gson.toJson(person);
        for (int i = 0; i < 1000000; i++) {
            JsonObject jsonObject = gson.fromJson(jsonsString, JsonObject.class);
        }
        System.out.println("字符串转化成json对象耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 字符串转化成java对象
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Person person1 = gson.fromJson(jsonsString, Person.class);
        }
        System.out.println("字符串转化成java对象耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 字符串转化为json数组
        start = System.currentTimeMillis();
        String jsonArrayStr = gson.toJson(list);
        for (int i = 0; i < 1000000; i++) {
            JsonArray objects = gson.fromJson(jsonArrayStr, JsonArray.class);
        }
        System.out.println("字符串转化为json数组耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 字符串转化成为java集合
        Type type = new TypeToken<List<Person>>() {
        }.getType();
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            List<Person> list1 = gson.fromJson(jsonArrayStr, type);
        }
        System.out.println("字符串转化成为java集合耗时：" + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    public void testJackson() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        // 构造对象
        Person person = new Person();
        person.setId(99999);
        person.setUsername("Happyjava");
        person.setAddress("广东省广州市，上海市，北京市，广东省深圳市，浙江省杭州市，");
        person.setAge(100);
        // Java对象转化成为json字符串
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String string = mapper.writeValueAsString(person);
        }
        System.out.println("Java对象转化成为json字符串耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 集合对象转化成为json字符串
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(person);
        }
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String string = mapper.writeValueAsString(list);
        }
        System.out.println("集合对象转化成为json字符串耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 字符串转化成json对象
        start = System.currentTimeMillis();
        String jsonsString = mapper.writeValueAsString(person);
        for (int i = 0; i < 1000000; i++) {
            ObjectNode objectNode = mapper.readValue(jsonsString, ObjectNode.class);
        }
        System.out.println("字符串转化成json对象耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 字符串转化成java对象
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Person person1 = mapper.readValue(jsonsString, Person.class);
        }
        System.out.println("字符串转化成java对象耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 字符串转化为json数组
        start = System.currentTimeMillis();
        String jsonArrayStr = mapper.writeValueAsString(list);
        for (int i = 0; i < 1000000; i++) {
            ArrayNode arrayNode = mapper.readValue(jsonArrayStr, ArrayNode.class);
        }
        System.out.println("字符串转化为json数组耗时：" + (System.currentTimeMillis() - start) + "ms");
        // 字符串转化成为java集合
        start = System.currentTimeMillis();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Person.class);
        for (int i = 0; i < 1000000; i++) {
            List<Person> list1 = mapper.readValue(jsonArrayStr, javaType);
        }
        System.out.println("字符串转化成为java集合耗时：" + (System.currentTimeMillis() - start) + "ms");
    }
}
