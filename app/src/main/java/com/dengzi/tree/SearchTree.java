package com.dengzi.tree;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/18.
 * @Version:1.0.0
 */

public class SearchTree {

    private Node root;

    public SearchTree() {

        int[] datas = new int[]{16, 33, 85, 50, 66, 13, 32, 22, 99, 44};
        for (int data : datas) {
            put(data);
        }
    }

    /**
     * 中序遍历——迭代
     */
    private List<Integer> middleList = new ArrayList<>();

    public void midOrder() {
        middleList.clear();
        midOrder(root);
        Log.e("dengzi", "中序遍历—迭代 = " + middleList.toString());
    }

    private void midOrder(Node node) {
        if (node == null) {
            return;
        }
        midOrder(node.left);
        middleList.add(node.item);
        midOrder(node.right);
    }


    /*
         *            50
         *       30        65
         *    16    35         88
         *
         */
    public void put(int data) {
        Node parent = null;
        Node node = null;
        if (root == null) {
            node = new Node(data);
            root = node;
            return;
        }
        node = root;
        while (node != null) {
            parent = node;
            if (data > node.item) {
                node = node.right;
            } else if (data < node.item) {
                node = node.left;
            }
        }
        node = new Node(data);
        if (data > parent.item) {
            parent.right = node;
        } else if (data < parent.item) {
            parent.left = node;
        }
        node.parent = parent;
    }

    static class Node {
        public int item;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int item) {
            this.item = item;
            left = null;
            right = null;
            parent = null;
        }
    }
}
