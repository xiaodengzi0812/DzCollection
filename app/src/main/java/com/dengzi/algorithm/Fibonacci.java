package com.dengzi.algorithm;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Djk
 * @Title: 斐波那契数列
 * @Time: 2017/12/28.
 * @Version:1.0.0
 */
public class Fibonacci {

    private static List<Integer> list = new ArrayList<>();

    public static void findFibonacciNum(int index) {
        int num = method1(index);
        Log.e("dengzi", "斐波那契数列: " + list.toString());
        Log.e("dengzi", "第" + index + "个位置的值为：" + num);
    }


    private static int method1(int index) {
        int prev = 1;
        int current = 1;
        int next;
        list.clear();
        list.add(1);
        list.add(1);
        if (index < 3) {
            return 1;
        }
        for (int i = 2; i < index; i++) {
            next = prev + current;
            prev = current;
            current = next;
            list.add(current);
        }
        return current;
    }
}
