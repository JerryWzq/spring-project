package com.wzq.guava.optional;

import com.google.common.base.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        Integer value1 = null;
        Integer value2 = new Integer(10);

        Optional<Integer> a = Optional.fromNullable(value1);
        Optional<Integer> b = Optional.of(value2);
        System.err.println(sum(a, b));
    }

    public static Integer sum(Optional<Integer> a, Optional<Integer> b){
        System.err.println("First paramter is present: " + a.isPresent());

        System.err.println("Second paramter is present: " + b.isPresent());

        Integer value1 = a.or(new Integer(0));

        Integer value2 = b.get();

        return value1 + value2;
    }

}
