package com.wzq.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtils {

    /**
     * 从集合中取出和为target的几个数
     * @param keys
     * @param target
     * @return
     */
    public static List<Long> getNewList(List<Long> keys, long target) {
        int n = keys.size();
        int nbit = 1 << n;
        long in;
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < nbit; i++) {
            in = 0;
            list.clear();
            for (int j = 0; j < n; j++) {
                int tmp = 1 << j; // 由0到n右移位
                if ((tmp & i) != 0) { // 与运算，同为1时才会是1
                    in += keys.get(j);
                    list.add(keys.get(j));
                }
            }
            if (in == target){
                return list;
            }
        }
        return Collections.emptyList();
    }

}
