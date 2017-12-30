package com.dengzi.graph;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dengzi.R;
import com.dengzi.tree.DoubleTree;

/**
 * @Title: 测试图的数据结构Activity
 * @Author: djk
 * @Time: 2017/12/20
 * @Version:1.0.0
 */
public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
    }

    public void graphClick(View view) {
        Graph graph = new Graph();
        int innerDegree = graph.getInnerDegree(3);
        int outDegree = graph.getOutDegree(4);

        Log.e("dengzi", "入度 : " + innerDegree + ", 出度 : " + outDegree);
        Log.e("dengzi", "--------------");
        graph.depthFirstSearch();
        Log.e("dengzi", "--------------");
        graph.broadFirstSearch();
        Log.e("dengzi", "--------------");
        graph.prim();
    }

}
