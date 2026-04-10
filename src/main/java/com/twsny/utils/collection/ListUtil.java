package com.twsny.utils.collection;

import java.awt.print.Pageable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListUtil {
    public static <T> List<T> arrayToList(T[] array) {
        if (array == null) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(array.length);
        Collections.addAll(list, array);
        return list;
    }

    public static <T> List<T> copyIterator(Iterable<T> iter) {
        List<T> copy = new ArrayList<>();
        Iterator<T> terator = iter.iterator();
        while (terator.hasNext())
            copy.add(terator.next());
        return copy;
    }

    public static <T> List<T> convert(Set<T> set) {
        return new ArrayList<>(set);
    }


    /**
     * 截取指定长度的list
     * @param list
     * @param length
     * @param <T>
     * @return
     */
    public static <T> List<T> sub(List<T> list, int length) {
        return list.subList(0, Math.min(list.size(), length));
    }

    public static <T> List<T> multiplyList(List<T> list, int multiplier) {
        return IntStream.range(0, multiplier)
                .flatMap(i -> IntStream.range(0, list.size()))
                .mapToObj(list::get)
                .collect(Collectors.toList());
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
