package com.dengzi.algorithm;

/**
 * @author Djk
 * @Title: 欧几里德法算法，求最大公约数
 * (m>n)m和n的最大公约数 = n 和m%n的最大公约数
 * @Time: 2017/12/28.
 * @Version:1.0.0
 */
public class Gcd {

    /**
     * 求两个数的最大公约数，假设m > n
     */
    public static int findNum(int m, int n) {
        if (n == 0) {
            return m;
        } else {
            return findNum(n, m % n);
        }
    }
}
