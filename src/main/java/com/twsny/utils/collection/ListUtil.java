package com.twsny.utils.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtil {
    public static <T> List<T> arrayToList(T[] array) {
        if (array == null) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(array.length);
        Collections.addAll(list, array);
        return list;
    }

    public static List<Byte> arrayToList(byte[] array) {
        if (array == null) return Collections.emptyList();
        List<Byte> list = new ArrayList<>(array.length);
        for (byte b : array) list.add(b); // 自动装箱
        return list;
    }

    public static List<Integer> arrayToList(int[] array) {
        if (array == null) return Collections.emptyList();
        List<Integer> list = new ArrayList<>(array.length);
        for (int b : array) list.add(b); // 自动装箱
        return list;
    }


}
