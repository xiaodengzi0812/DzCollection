package com.dengzi.tree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dengzi.R;

public class SearchTreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
    }

    public void treeClick(View view) {

        SearchTree tree = new SearchTree();
        tree.midOrder();

    }

}
