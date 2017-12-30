package com.dengzi.sort;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Djk
 * @Title: 排序工具类
 * @Time: 2017/12/20.
 * @Version:1.0.0
 */
public class SortUtil {
    private static int[] mArray = new int[]{18, 4, 22, 77, 45, 97, 12, 65, 23, 43};

    private static void logArray(String logStr, int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int i : array) {
            list.add(i);
        }
        Log.e("dengzi", logStr + list.toString());
    }


    /**
     * 冒泡排序
     */
    public static void maoPaoSort() {
        int[] a = mArray.clone();
        logArray("排序前 = ", a);
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        logArray("排序后 = ", a);
    }

    /**
     * 选择排序
     */
    public static void selectSort() {
        int[] a = mArray.clone();
        logArray("排序前 = ", a);
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[i]) {
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
        logArray("排序后 = ", a);
    }

    /**
     * 插入排序
     */
    public static void insertSort() {
        int[] a = mArray.clone();
        logArray("排序前 = ", a);
        for (int i = 1; i < a.length; i++) {
            int j;
            int temp = a[i];
            for (j = i - 1; j >= 0; j--) {
                if (a[j] > temp) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = temp;
        }
        logArray("排序后 = ", a);
    }

    /**
     * 二分法插入排序
     */
    public static void binaryInsertSort() {
        int[] a = mArray.clone();
        logArray("排序前 = ", a);


        logArray("排序后 = ", a);
    }

}
