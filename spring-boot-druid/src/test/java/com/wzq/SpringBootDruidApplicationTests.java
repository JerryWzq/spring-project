package com.wzq;

import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDruidApplicationTests {

    @Test
    public void contextLoads() {

        Person person1 = new Person(1L, "AAA", 20, "111");
        Person person2 = new Person(2L, "BBB", 24, "222");
        Person person3 = new Person(3L, "CCC", 21, "333");
        Person person4 = new Person(4L, "DDD", 24, "444");
        Person person5 = new Person(5L, "EEE", 22, "555");
        Person person6 = new Person(6L, "FFF", 20, "666");
        Person person7 = new Person(7L, "GGG", 24, "777");

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);

        Map<Integer, Set<String>> personToMap = personList.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toSet())));
        System.err.println(personToMap);
        System.err.println(personToMap.values());

    }

}

@Data
class Person{

    private Long id;
    private String name;
    private Integer age;
    private String phone;

    public Person(Long id, String name, Integer age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
}
