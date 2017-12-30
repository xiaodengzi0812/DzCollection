package com.dengzi.algorithm;

import android.util.Log;

/**
 * @author Djk
 * @Title: 穷举法泊松分酒
 * @Time: 2017/12/28.
 * @Version:1.0.0
 */
public class ShareWine {
    private int w1 = 12;
    private int w2 = 8;
    private int w3 = 5;
    private int to = 7;//目标酒量

    public void doMethod(int a1, int a2, int a3) {
        Log.e("dengzi", "第1个:" + a1 + " -> 第2个:" + a2 + " -> 第3个:" + a3);

        if (a1 == to || a2 == to || a3 == to) {
            return;
        }
        if (a2 == 0) {
            // 从第一个往第二个倒
            if (w1 > a2) {
                doMethod(a1 - w2, w2, a3);
            } else {
                doMethod(0, a1, a3);
            }
        } else if (a2 != 0 && a3 != w3) {
            // 从第二个往第三个倒
            if (a3 + a2 > w3) {// 能倒满
                doMethod(a1, a2 - (w3 - a3), w3);
            } else {
                doMethod(a1, 0, a2 + a3);
            }
        } else if (a3 == w3) {
            // 从第三个往第一个倒
            if (a3 + a1 > w1) {// 能倒满
                doMethod(w1, a2, a3 - (w1 - a1));
            } else {
                doMethod(a1 + a3, a2, 0);
            }
        }
    }
}
