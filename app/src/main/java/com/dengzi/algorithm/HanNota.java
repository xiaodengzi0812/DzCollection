package com.dengzi.algorithm;

import android.util.Log;

/**
 * @author Djk
 * @Title: 经典的汉诺塔算法
 * @Time: 2017/12/28.
 * @Version:1.0.0
 */
public class HanNota {
    static int mNum = 1;

    public static void hanNota(int num, char from, char middle, char to) {
        if (num == 1) {
            move(1, from, to);
        } else {
            hanNota(num - 1, from, to, middle);//第一步，先将n-1个盘子从A利用C挪到B
            move(num, from, to);//讲n这个盘子（底盘）从A挪到C
            hanNota(num - 1, middle, from, to);//讲n-1个盘子从B利用A挪到C
        }
    }

    private static void move(int num, char from, char to) {
        Log.e("dengzi", "第 " + mNum++ + " 步从 " + from + " --> " + to);
    }

}
