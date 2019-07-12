package com.wzq;

import com.wzq.util.ListUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class SpringBootTest {

    @Test
    public void test01(){
        System.err.println("Hello World.....");

        Map<String, List<String>> data = new HashMap<>();
        List<String> data01 = new ArrayList<>();
        data01.add("A");
        data01.add("B");
        data01.add("C");
        data01.add("D");
        data01.add("E");
        data.put("first", data01);
        List<String> data02 = new ArrayList<>();
        data02.add("F");
        data02.add("G");
        data02.add("H");
        data.put("second", data02);
        List<String> data03 = new ArrayList<>();
        data03.add("E");
        data03.add("F");
        data.put("third", data03);
        List<String> data04 = new ArrayList<>();
        data04.add("G");
        data04.add("H");
        data04.add("I");
        data04.add("J");
        data04.add("K");
        data04.add("L");
        data.put("fourth", data04);



    }

    @Test
    public void test02(){
        List<Long> list = new ArrayList<>();
        list.add(5L);
        list.add(2L);
        list.add(7L);
        list.add(9L);
        list.add(13L);

        List<Long> newList = ListUtils.getNewList(list, 8L);
        System.err.println(newList);

        System.err.println(1L << 5000);
    }

    @Test
    public void test03(){
        Map<String, Integer> dataOrdered = new HashMap<>();
        Map<String, Integer> data = new HashMap<>();
        data.put("A", 3);
        data.put("B", 1);
        data.put("C", 9);
        data.put("D", 5);
        data.put("E", 8);

        data.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> dataOrdered.put(x.getKey(), x.getValue()));
        System.err.println(dataOrdered);

        //按value倒叙
        data =   data.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue(), (x1, x2) -> x2, LinkedHashMap::new));
        System.err.println(data);

        //按value顺序
        data =   data.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue(), (x1, x2) -> x2, LinkedHashMap::new));
        System.err.println(data);
    }

}
