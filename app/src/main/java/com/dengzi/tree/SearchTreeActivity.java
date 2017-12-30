package com.dengzi.tree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dengzi.R;

/**
 * @Title: 测试查询二叉数Activity
 * @Author: djk
 * @Time: 2017/12/20
 * @Version:1.0.0
 */
public class SearchTreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
    }

    public void treeClick(View view) {

        SearchTree tree = new SearchTree();
        tree.midOrder();
        tree.dele(50);
        Log.e("dengzi", "--------------------");
        tree.midOrder();
    }

}
