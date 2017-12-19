package com.dengzi.tree;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Djk
 * @Title: 二叉数
 * @Time: 2017/12/15.
 * @Version:1.0.0
 */
public class DoubleTree {
    private Node rootNode;

    /*
     *            A
     *       B        C
     *    D    E         F
     *            G
     */
    public DoubleTree() {
//        createTree1();
        createTree2();
    }

    /**
     * 前序创建一个二叉数
     * [A, B, D,null,null, E,null, G, null, null,  C,null, F,null,null]
     */
    private void createTree2() {
        String[] strs = new String[]{"A", "B", "D", null, null, "E", null, "G", null, null, "C", null, "F", null, null};
        ArrayList<String> dataList = new ArrayList();
        for (String str : strs) {
            dataList.add(str);
        }
        createTree2(dataList);
    }

    private Node createTree2(ArrayList<String> dataList) {
        if (dataList.size() == 0) {
            return null;
        }
        String data = dataList.get(0);
        dataList.remove(0);
        if (data == null) {
            return null;
        }
        Node newNode = new Node(data);
        if (rootNode == null) {
            rootNode = newNode;
        }
        newNode.left = createTree2(dataList);
        newNode.right = createTree2(dataList);
        return newNode;
    }

    /**
     * 创建二叉数(最lowB的方法)
     */
    private void createTree1() {
        rootNode = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");

        rootNode.left = nodeB;
        rootNode.right = nodeC;
        nodeB.left = nodeD;
        nodeB.right = nodeE;
        nodeE.right = nodeG;
        nodeC.right = nodeF;
    }

    /**
     * 求二叉树的高度
     */
    public int getHeight() {
        return getHeight(rootNode);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = getHeight(node.left);
            int rightHeight = getHeight(node.right);
            return leftHeight < rightHeight ? rightHeight + 1 : leftHeight + 1;
        }
    }

    /**
     * 获取二叉树的结点数
     */
    public int getSize() {
        return getSize(rootNode);
    }

    private int getSize(Node node) {
        if (node == null) {
            return 0;
        } else {
            int leftSize = getSize(node.left);
            int rightSize = getSize(node.right);
            return leftSize + rightSize + 1;
        }
    }

    /**
     * 前序遍历——迭代
     */
    private List<String> preList = new ArrayList<>();

    public void preOrder() {
        preList.clear();
        preOrder(rootNode);
        Log.e("dengzi", "前序遍历—迭代 = " + preList.toString());
    }

    public void preOrder(Node node) {
        if (node == null) {
            return;
        } else {
            preList.add(node.item);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 前序遍历——非迭代
     */
    public void preOrder2() {
        preList.clear();
        preOrder2(rootNode);
        Log.e("dengzi", "前序遍历—非迭代 = " + preList.toString());
    }

    public void preOrder2(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node popNode = stack.pop();
            preList.add(popNode.item);
            if (popNode.right != null) {
                stack.push(popNode.right);
            }
            if (popNode.left != null) {
                stack.push(popNode.left);
            }
        }
    }

    /**
     * 中序遍历——迭代
     */
    private List<String> middleList = new ArrayList<>();

    public void midOrder() {
        middleList.clear();
        midOrder(rootNode);
        Log.e("dengzi", "中序遍历—迭代 = " + middleList.toString());
    }

    public void midOrder(Node node) {
        if (node == null) {
            return;
        } else {
            midOrder(node.left);
            middleList.add(node.item);
            midOrder(node.right);
        }
    }

    /**
     * 中序遍历——非迭代
     */
    public void midOrder2() {
        middleList.clear();
        midOrder2(rootNode);
        Log.e("dengzi", "中序遍历—非迭代 = " + middleList.toString());
    }

    public void midOrder2(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack();
        stack.push(node);
        while (!stack.isEmpty()) {
            while (node != null) {
                node = node.left;
                if (node != null) {
                    stack.push(node);
                }
            }
            Node popNode = stack.pop();
            middleList.add(popNode.item);
            node = popNode.right;
            if (node != null) {
                stack.push(node);
            }
        }
    }

    /**
     * 后序遍历——迭代
     */
    private List<String> postList = new ArrayList<>();

    public void postOrder() {
        postList.clear();
        postOrder(rootNode);
        Log.e("dengzi", "后序遍历—迭代 = " + postList.toString());
    }

    public void postOrder(Node node) {
        if (node == null) {
            return;
        } else {
            postOrder(node.left);
            postOrder(node.right);
            postList.add(node.item);
        }
    }

    /**
     * 后序遍历——非迭代
     */
    public void postOrder2() {
        postList.clear();
        postOrder2(rootNode);
        Log.e("dengzi", "后序遍历—非迭代 = " + postList.toString());
    }

    public void postOrder2(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack();
        stack.push(node);
        while (!stack.isEmpty()) {
            while (node != null) {
                if (node.left != null) {
                    node = node.left;
                    stack.push(node);
                } else {
                    node = node.right;
                    if (node != null) {
                        stack.push(node);
                    }
                }
            }
            Node popNode = stack.pop();
            postList.add(popNode.item);
            if (!stack.isEmpty()) {
                node = stack.peek().right;
                if (node != null && popNode != node) {
                    stack.push(node);
                } else {
                    node = null;
                }
            }
        }
    }

    static class Node {
        public String item;
        public Node left;
        public Node right;

        public Node(String item) {
            this.item = item;
            left = null;
            right = null;
        }
    }
}
