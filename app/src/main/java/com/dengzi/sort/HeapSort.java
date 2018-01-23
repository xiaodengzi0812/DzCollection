package com.dengzi.sort;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Djk
 * @Title: 堆排序
 * @Time: 2017/12/20.
 * @Version:1.0.0
 */
public class HeapSort {
    private static int[] mArray = new int[]{18, 4, 22, 77, 45, 97, 12, 65, 23, 43};

    private static void logArray(String logStr, int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int i : array) {
            list.add(i);
        }
        Log.e("dengzi", logStr + list.toString());
    }


    public static void heapSort() {
        if (mArray == null || mArray.length <= 1) {
            return;
        }
        logArray("堆排序前 = ", mArray);
        for (int i = mArray.length - 1; i > 0; i--) {
            // 创建一个大堆
            makeMaxHeap(mArray, i);
            // 将第0个位置的元素与最排序的最后一个元素交换
            changeArray(mArray, 0, i);
        }
        logArray("堆排序后 = ", mArray);
    }

    /**
     * 创建大堆
     * 大堆: 一个完全二叉数,根节点 > 左右节点
     * 每次创建完的大堆有一个特点: 就是下标为0的位置为整个数组的最大值
     *
     * @param array
     * @param length
     */
    private static void makeMaxHeap(int[] array, int length) {
        // 找到当前循环最大length的一半
        // 创建大堆只需要遍历length / 2个元素即可
        int heaf = length / 2;
        for (int i = heaf; i >= 0; i--) {
            // 找到二叉数的左右节点下标
            int left = i * 2 + 1;
            int right = i * 2 + 2;
            // 默认设置根节点为最大值下标
            int largest = i;
            // 如果左节占大于根节点,则交换下标
            if (left <= length && array[left] > array[largest]) {
                largest = left;
            }
            // 如果右节占大于根节点,则交换下标
            if (right <= length && array[right] > array[largest]) {
                largest = right;
            }
            // 如果最大值下标已改变,则交换数据
            if (largest != i) {
                changeArray(array, largest, i);
            }
        }
    }

    /**
     * 数组元素交换
     */
    private static void changeArray(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
