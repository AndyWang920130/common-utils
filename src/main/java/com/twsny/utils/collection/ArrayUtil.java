package com.twsny.utils.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayUtil {
    public static <T> T[] listToArray(List<T> list, T[] arr) {
        return list.toArray(arr);
    }

    public static byte[] byteListToArray(List<Byte> list) {
        if (list == null) return new byte[0];
        byte[] arr = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i); // 自动拆箱
        }
        return arr;
    }

    public static int[] intListToArray(List<Integer> list) {
        if (list == null) return new int[0];
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i); // 自动拆箱
        }
        return arr;
    }

    /**
     * 合并数组
     * @param originalArray 原数组
     * @param copyArray 待copy的数组
     * @param from 待copy数组的开始索引
     * @param to 待copy数组的结束索引
     * @param <T>
     * @return
     */
    public static <T> T[] mergeArray(T[] originalArray, T[] copyArray, int from, int to){

        if (copyArray == null || originalArray == null) {
            throw new IllegalArgumentException("数组不能为 null");
        }
        if (from < 0 || to > copyArray.length || from > to) {
            throw new IndexOutOfBoundsException("索引超出范围");
        }

        int lengthToAdd = to - from;

        T[] result = Arrays.copyOf(originalArray, originalArray.length + lengthToAdd);

        for (int i = from; i < to; i++) {
            result[originalArray.length + i - from] = copyArray[i];
        }

        return result;
    }

    public static <T> T[] mergeArray(T[] originalArray, T[] copyArray){
        return mergeArray(originalArray, copyArray, 0, copyArray.length);
    }

    public static String parseString(String[] rawDataArray, String appendStr) {
        return Arrays.stream(rawDataArray).collect(Collectors.joining(appendStr));
    }

    public static String parseString(String[] rawDataArray) {
        return Arrays.stream(rawDataArray).collect(Collectors.joining(""));
    }

    public static void main(String[] args) {
        List<String>  stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        String[] stringArr = ArrayUtil.listToArray(stringList, new String[0]);
        Arrays.stream(stringArr).forEach(System.out::print);
        System.out.println("");

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        int[] intArr = ArrayUtil.intListToArray(integerList);
        Arrays.stream(intArr).forEach(System.out::print);
        System.out.println("");


        String[] strArr1 = new String[] {"a", "b", "c"};
        String[] strArr2 = new String[] {"d", "e", "f", "c"};
        String[] strArr3 = ArrayUtil.mergeArray(strArr1, strArr2);
        Arrays.stream(strArr3).forEach(System.out::print);
        System.out.println("");

        String str = ArrayUtil.parseString(strArr2);
        System.out.println(str);

        String str2 = ArrayUtil.parseString(strArr2, "#");
        System.out.println(str2);
    }

}
