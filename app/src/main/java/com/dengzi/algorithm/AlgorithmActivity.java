package com.dengzi.algorithm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dengzi.R;
import com.dengzi.sort.SortUtil;

import java.util.HashMap;

/**
 * @Title: 算法
 * @Author: djk
 * @Time: 2017/12/20
 * @Version:1.0.0
 */
public class AlgorithmActivity extends AppCompatActivity {

    HashMap map = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);
        map.put("", "");
    }

    public void fibonacciClick(View view) {
        Fibonacci.findFibonacciNum(10);
    }

    public void hanNotaClick(View view) {
        HanNota.hanNota(10, 'A', 'B', 'C');
    }

    public void gcdClick(View view) {
        int num = Gcd.findNum(99, 45);
        Log.e("dengzi", "最大公约数：" + num);
    }

    public void calNClick(View view) {
        CalNFact.getCalNum(10);
    }

    public void shareWineClick(View view) {
        ShareWine shareWine = new ShareWine();
        shareWine.doMethod(12, 0, 0);
    }

}
