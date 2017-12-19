package com.dengzi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dengzi.collection.DzIterator;
import com.dengzi.collection.list.DZLinkedList;
import com.dengzi.collection.list.DzArrayList;
import com.dengzi.collection.list.DzList;
import com.dengzi.map.DzHashMap;
import com.dengzi.map.DzLinkedHashMap;
import com.dengzi.map.DzMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void hashSetTest(View view) {
        //创建HashSet集合：
        Set<String> hashSet = new HashSet<String>();
        Log.e("dengzi", "HashSet初始容量大小：" + hashSet.size());

        //元素添加：
        hashSet.add("my");
        hashSet.add("name");
        hashSet.add("is");
        hashSet.add("jiaboyan");
        hashSet.add(",");
        hashSet.add("hello");
        hashSet.add("world");
        hashSet.add("!");
        Log.e("dengzi", "HashSet容量大小：" + hashSet.size());

        Log.e("dengzi", "------------------------");
        //迭代器遍历：
        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            Log.e("dengzi", str);
        }
        Log.e("dengzi", "------------------------");
        //增强for循环
        for (String str : hashSet) {
            if ("jiaboyan".equals(str)) {
                Log.e("dengzi", "你就是我想要的元素:" + str);
            }
            Log.e("dengzi", str);
        }
        Log.e("dengzi", "------------------------");

        //元素删除：
        hashSet.remove("jiaboyan");
        Log.e("dengzi", "HashSet元素大小：" + hashSet.size());
        hashSet.clear();
        Log.e("dengzi", "HashSet元素大小：" + hashSet.size());
        Log.e("dengzi", "------------------------");
        //集合判断：
        boolean isEmpty = hashSet.isEmpty();
        Log.e("dengzi", "HashSet是否为空：" + isEmpty);
        boolean isContains = hashSet.contains("hello");
        Log.e("dengzi", "HashSet是否为空：" + isContains);
        Log.e("dengzi", "------------------------");
    }

    public void hashMapTest(View view) {
        //创建HashMap集合：
        DzMap<String, String> map = new DzHashMap<>();
        Log.e("dengzi", "HashMap元素大小:" + map.size());

        //元素添加:
        map.put("hi", "hello");
        map.put("my", "hello");
        map.put("name", "hello");
        map.put("is", "hello");
        map.put("jiaboyan", "hello");
        map.put("dengzi", "hello");
        map.put("pizi", "hello");

        //遍历1：获取key的Set集合
        for (String key : map.keySet()) {
            Log.e("dengzi", "key:" + key + ", value:" + map.get(key));
        }
        Log.e("dengzi", "------------------------");
        ArrayList<String> valueList = (ArrayList<String>) map.values();
        for (String value : valueList) {
            Log.e("dengzi", "value:" + value);
        }
        Log.e("dengzi", "------------------------");
        //元素获取：通过key获取value
        String keyValue = map.get("jiaboyan");
        Log.e("dengzi", "jiaboyan 对应的value:" + keyValue);

        //元素替换：map没有提供直接set方法，而是使用新增来完成更新操作
        map.put("jiaboyan", "helloworld");
        Log.e("dengzi", "jiaboyan对应的value:" + map.get("jiaboyan"));

        //元素删除：
        String value = map.remove("jiaboyan");
        Log.e("dengzi", "删除元素的value:" + value);
    }

    public void linkedHashMapTest(View view) {
        //创建HashMap集合：
        DzMap<String, String> map = new DzLinkedHashMap<>();
        //元素添加:
        map.put("1", "hello");
        map.put("2", "hello");
        map.put("3", "hello");
        map.put("4", "hello");
//        map.put("5", "hello");
//        map.put("6", "hello");
//        map.put("7", "hello");
//        map.put("8", "hello");
//        map.put("9", "hello");
//        map.put("10", "hello");
//        map.put("11", "hello");
//        map.put("12", "hello");

        //遍历
//        for (String key : map.keySet()) {
//            Log.e("dengzi", "key:" + key + ", value:" + map.get(key));
//        }
        Log.e("dengzi", "------------------------");
        // 查询和删除操作
        map.get("2");
//        map.get("6");
//        map.remove("10");
        map.put("15", "hello");
        //遍历
//        for (String key : map.keySet()) {
//            Log.e("dengzi", "key:" + key + ", value:" + map.get(key));
//        }

        Log.e("dengzi", "------------------------");

        for (Object key : ((DzLinkedHashMap) map).keyList()) {
            Log.e("dengzi", "key:" + key + ", value:" + map.get(key));
        }
    }

}
