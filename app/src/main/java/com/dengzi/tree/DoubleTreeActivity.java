package com.dengzi.tree;

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

public class DoubleTreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
    }

    public void treeClick(View view) {
        DoubleTree tree = new DoubleTree();
        // 二叉树的高度
        int treeHeight = tree.getHeight();
        // 二叉树的结点数
        int treeSize = tree.getSize();
        Log.e("dengzi", "二叉树的高度 = " + treeHeight);
        Log.e("dengzi", "二叉树的结点数 = " + treeSize);

        Log.e("dengzi", "--------------------");
        tree.preOrder();
        tree.preOrder2();

        Log.e("dengzi", "--------------------");
        tree.midOrder();
        tree.midOrder2();

        Log.e("dengzi", "--------------------");
        tree.postOrder();
        tree.postOrder2();
    }

}
