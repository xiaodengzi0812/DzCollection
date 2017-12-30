package com.dengzi.algorithm;

import android.util.Log;

/**
 * @author Djk
 * @Title: 阶乘法
 * @Time: 2017/12/28.
 * @Version:1.0.0
 */
public class CalNFact {

    /**
     * 求n的阶乘
     *
     * @param n
     * @return
     */
    public static void getCalNum(int n) {
        int result0 = getCalNum0(n);
        int result1 = getCalNum1(n);
        Log.e("dengzi", "result0 = " + result0);
        Log.e("dengzi", "result1 = " + result1);
    }


    /**
     * 递归写法
     *
     * @param n
     * @return
     */
    private static int getCalNum0(int n) {
        if (n == 1) {
            return n;
        } else {
            return n * getCalNum0(n - 1);
        }
    }

    /**
     * for循环写法
     *
     * @param n
     * @return
     */
    private static int getCalNum1(int n) {
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
        }
        return sum;
    }

}
