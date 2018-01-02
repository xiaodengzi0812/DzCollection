package com.dengzi.algorithm;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Djk
 * @Title: 贪心求背包问题
 * @Time: 2017/12/28.
 * @Version:1.0.0
 */
public class GreedyPackage {
    private int MAX_WEIGHT = 160;// 背包最大装的东西
    private int[] mWeights = new int[]{35, 30, 60, 50, 40, 10, 25};// 重量
    private int[] mValues = new int[]{10, 40, 30, 50, 35, 40, 30};// 价值

    public void getMaxPack() {
        doMethod(MAX_WEIGHT, mWeights, mValues);
    }

    /**
     * @param maxPackage 背包最大装的东西
     * @param weights    重量
     * @param values     价值
     */
    private void doMethod(int maxPackage, int weights[], int[] values) {
        int n = weights.length;
        // 求出性价比
        double[] xinJiaBi = new double[n];
        for (int i = 0; i < n; i++) {
            xinJiaBi[i] = (double) values[i] / weights[i];
        }

        // 性价比记录的位置排序集合
        int[] sort = new int[n];
        for (int i = 0; i < n; i++) {
            sort[i] = i;
        }
        // 对性价比排序
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (xinJiaBi[i] < xinJiaBi[j]) {
                    // 性价比排序
                    double temp = xinJiaBi[i];
                    xinJiaBi[i] = xinJiaBi[j];
                    xinJiaBi[j] = temp;
                    // 性价比记录的位置排序
                    int sortTemp = sort[i];
                    sort[i] = sort[j];
                    sort[j] = sortTemp;
                }
            }
        }

        List<Integer> packList = new ArrayList<>();
        int sumWeight = 0;
        int sumValue = 0;
        for (int i = 0; i < n; i++) {
            int itemWeight = weights[sort[i]];
            int itemValue = values[sort[i]];
            if (itemWeight + sumWeight < maxPackage) {
                packList.add(itemWeight);
                sumWeight += itemWeight;
                sumValue += itemValue;
            }
        }
        Log.e("dengzi", "背包：" + packList.toString());
        Log.e("dengzi", "最大价值：" + sumValue);
    }

}
