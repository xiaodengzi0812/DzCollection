package com.dengzi.tree;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Djk
 * @Title: 查找二叉树
 * @Time: 2017/12/18.
 * @Version:1.0.0
 */
public class SearchTree {

    private Node root;

    public SearchTree() {

        int[] datas = new int[]{50, 16, 33, 85, 66, 13, 32, 22, 99, 44};
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


    /* 二叉数的添加
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

    /**
     * 删除
     *
     * @param data
     */
    public void dele(int data) {
        Node node = searchData(data);
        if (node == null) {
            return;
        } else {
            dele(node);
        }
    }

    /**
     * 删除一个节点
     *
     * @param node
     */
    private void dele(Node node) {
        if (node == null) {
            return;
        }
        Node parent = node.parent;
        // 左右都为null,直接删除
        if (node.left == null && node.right == null) {
            if (node.item > parent.item) {
                parent.right = null;
            } else {
                parent.left = null;
            }
            return;
        }

        // 有左无右,将左节点替换为当前节点
        if (node.left != null && node.right == null) {
            if (node.item > parent.item) {
                parent.right = node.left;
            } else {
                parent.left = node.left;
            }
            return;
        }

        // 有右无左,将右节点替换为当前节点
        if (node.left == null && node.right != null) {
            if (node.item > parent.item) {
                parent.right = node.right;
            } else {
                parent.left = node.right;
            }
            return;
        }

        // 左右都有
        // 找要删除的后继节点,然后将后继节点的值赋值给当前节点,并删除后继节点
        Node nextNode = findNextNode(node.right);
        node.item = nextNode.item;
        dele(nextNode);
    }

    /**
     * 查找后继节点
     *
     * @param node
     * @return
     */
    private Node findNextNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 查询
     *
     * @param data
     * @return
     */
    private Node searchData(int data) {
        Node node = root;
        while (node != null) {
            if (data > node.item) {
                node = node.right;
            } else if (data < node.item) {
                node = node.left;
            } else if (data == node.item) {
                return node;
            }
        }
        return null;
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
