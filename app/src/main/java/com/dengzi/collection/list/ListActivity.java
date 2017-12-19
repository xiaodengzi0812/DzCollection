package com.dengzi.collection.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dengzi.R;
import com.dengzi.collection.DzIterator;
import com.dengzi.map.DzHashMap;
import com.dengzi.map.DzLinkedHashMap;
import com.dengzi.map.DzMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    public void arrayListTest(View view) {
        DzList<String> list = new DzArrayList();
        list.add("dengzi");
        list.add("pizi");
        list.add("guozi");
        list.add("fei");

        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.remove(i);
            i--;
            size--;
        }

        Log.e("dengzi", "------------------------");

        DzIterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = (String) iterator.next();
            Log.e("dengzi", "str = " + str);
            iterator.remove();
        }
    }

    public void linkedListTest(View view) {
        DzList<String> list = new DZLinkedList<>();
        list.add("dengzi");
        list.add("pizi");
        list.add("guozi");
        list.add("fei");

        DzIterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = (String) iterator.next();
            Log.e("dengzi", "str = " + str);
            iterator.remove();
        }

        Log.e("dengzi", "------------------------");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Log.e("dengzi", "str = " + list.get(i));
        }
    }

}
