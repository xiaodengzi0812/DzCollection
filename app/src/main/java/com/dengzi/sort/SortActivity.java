package com.dengzi.sort;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dengzi.R;
import com.dengzi.collection.DzIterator;
import com.dengzi.collection.list.DZLinkedList;
import com.dengzi.collection.list.DzArrayList;
import com.dengzi.collection.list.DzList;
import com.dengzi.map.DzHashMap;
import com.dengzi.map.DzLinkedHashMap;
import com.dengzi.map.DzMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Title: 排序
 * @Author: djk
 * @Time: 2017/12/20
 * @Version:1.0.0
 */
public class SortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
    }

    public void maoPaoClick(View view) {
        SortUtil.maoPaoSort();
    }

    public void selectClick(View view) {
        SortUtil.selectSort();
    }

    public void insertClick(View view) {
        SortUtil.insertSort();
    }

    public void binaryInsertClick(View view) {
        SortUtil.binaryInsertSort();
    }

    public void heapClick(View view) {
        HeapSort.heapSort();
    }

}
