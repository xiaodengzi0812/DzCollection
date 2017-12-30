package com.dengzi.graph;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Djk
 * @Title: 图
 * @Time: 2017/12/20.
 * @Version:1.0.0
 */
public class Graph {
    private static final int MAX = 1000;
    private int size;//顶点数量
    private int[] vertexs;//顶点数组
    private int[][] matrix;// 邻接矩阵
    private boolean[] isVisited;// 是否已访问

    public Graph() {
        this(9);
    }

    public Graph(int size) {
        this.size = size;
        vertexs = new int[size];
        matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            vertexs[i] = i;
        }
        initGraph();
    }

    private void initGraph() {
        int[] a0 = new int[]{0, 10, MAX, MAX, MAX, 11, MAX, MAX, MAX};
        int[] a1 = new int[]{10, 0, 18, MAX, MAX, MAX, 16, MAX, 12};
        int[] a2 = new int[]{MAX, MAX, 0, 22, MAX, MAX, MAX, MAX, 8};
        int[] a3 = new int[]{MAX, MAX, 22, 0, 20, MAX, MAX, 16, 21};
        int[] a4 = new int[]{MAX, MAX, MAX, 20, 0, 26, MAX, 7, MAX};
        int[] a5 = new int[]{11, MAX, MAX, MAX, 26, 0, 17, MAX, MAX};
        int[] a6 = new int[]{MAX, 16, MAX, MAX, MAX, 17, 0, 19, MAX};
        int[] a7 = new int[]{MAX, MAX, MAX, 16, 7, MAX, 19, 0, MAX};
        int[] a8 = new int[]{MAX, 12, 8, 21, MAX, MAX, MAX, MAX, 0};

        matrix[0] = a0;
        matrix[1] = a1;
        matrix[2] = a2;
        matrix[3] = a3;
        matrix[4] = a4;
        matrix[5] = a5;
        matrix[6] = a6;
        matrix[7] = a7;
        matrix[8] = a8;
    }

    /**
     * 获取两个顶点之间的权值
     */
    public int getWeight(int v1, int v2) {
        int weight = matrix[v1][v2];
        return weight == 0 ? 0 : (weight == MAX ? -1 : weight);
    }

    /**
     * 获取某个顶点的出度
     */
    public int getOutDegree(int index) {
        int degree = 0;
        for (int i = 0; i < size; i++) {
            // 横向查找为出度
            int indexDegree = matrix[index][i];
            if (indexDegree != 0 && indexDegree != MAX) {
                degree++;
            }
        }
        return degree;
    }


    /**
     * 获取某个顶点的入度
     */
    public int getInnerDegree(int index) {
        int degree = 0;
        for (int i = 0; i < size; i++) {
            // 竖向查找为入度
            int indexDegree = matrix[i][index];
            if (indexDegree != 0 && indexDegree != MAX) {
                degree++;
            }
        }
        return degree;
    }

    /**
     * 获取某个顶点的第一个邻接点
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < size; i++) {
            // 横向查找邻接点
            int indexDegree = matrix[index][i];
            if (indexDegree != 0 && indexDegree != MAX) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接点的下标来取得下一个邻接点
     *
     * @param v     表示要找的顶点
     * @param index 表示该顶点相对于哪个邻接点去获取下一个邻接点
     */
    public int getNextNeighbor(int v, int index) {
        for (int i = index + 1; i < size; i++) {
            // 横向查找index的下一个邻接点
            int indexDegree = matrix[v][i];
            if (indexDegree != 0 && indexDegree != MAX) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 对外公开的深度优先遍历
     */
    ArrayList<Integer> dfsList = new ArrayList<>();

    public void depthFirstSearch() {
        isVisited = new boolean[size];
        dfsList.clear();
        for (int i = 0; i < size; i++) {
            if (!isVisited[i]) {
                dfsList.add(i);
                depthFirstSearch(i);
            }
        }
        isVisited = new boolean[size];
        Log.e("dengzi", "深度优先遍历 = " + dfsList.toString());
    }

    /**
     * 图的深度优先遍历算法(DFS)
     */
    private void depthFirstSearch(int i) {
        isVisited[i] = true;
        int w = getFirstNeighbor(i);

        while (w != -1) {
            if (!isVisited[w]) {
                dfsList.add(w);
                depthFirstSearch(w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 对外公开的广度优先遍历
     */
    ArrayList<Integer> bfsList = new ArrayList<>();

    public void broadFirstSearch() {
        bfsList.clear();
        isVisited = new boolean[size];
        for (int i = 0; i < size; i++) {
            if (!isVisited[i]) {
                broadFirstSearch(i);
            }
        }
        isVisited = new boolean[size];
        Log.e("dengzi", "广度优先遍历 = " + bfsList.toString());
    }

    /**
     * 图的广度优先遍历算法(BFS)
     * 运用队列,先进先出
     */
    private void broadFirstSearch(int i) {
        LinkedList<Integer> tempList = new LinkedList();
        tempList.add(i);
        bfsList.add(i);
        isVisited[i] = true;

        int remove;
        int w;
        while (!tempList.isEmpty()) {
            remove = tempList.removeFirst();
            w = getFirstNeighbor(remove);
            while (w != -1) {
                if (!isVisited[w]) {
                    isVisited[w] = true;
                    tempList.add(w);
                    bfsList.add(w);
                }
                w = getNextNeighbor(remove, w);
            }
        }
    }

    /**
     * prim 普里姆算法,计算最小生成树,也叫做计算路径最小值
     */
    public void prim() {
        int[] lowcost = new int[size];//最小代价顶点权值的数组,为0表示已经获取最小权值

        List<Integer> adjvexList = new ArrayList<>();//放路径顶点
        List<Integer> adjvexList2 = new ArrayList<>();//放顶点权值

        int min = 0;// 遍历到的最小值
        int minIndex = 0;// 遍历到的最小值下标
        int sum = 0;// 权值的和
        adjvexList.add(0);
        adjvexList2.add(0);
        // 将与起始点相连的所有点的权值放入集合
        for (int i = 1; i < size; i++) {
            lowcost[i] = matrix[0][i];
        }
        for (int i = 1; i < size; i++) {
            // 算出当前行的最小值
            min = MAX;
            minIndex = 0;
            for (int j = 1; j < size; j++) {
                int indexNum = lowcost[j];
                if (indexNum > 0 && indexNum < min) {
                    min = indexNum;
                    minIndex = j;
                }
            }

            sum += min;
            // 将最小值的位置置为0
            lowcost[minIndex] = 0;
            adjvexList.add(minIndex);
            adjvexList2.add(min);

            for (int j = 1; j < size; j++) {
                int indexNum = matrix[minIndex][j];
                if (indexNum != 0 && indexNum < lowcost[j]) {
                    lowcost[j] = indexNum;
                }
            }
        }
        Log.e("dengzi", "最小生成树权值和:" + sum);
        Log.e("dengzi", "最小生成树顶点:" + adjvexList.toString());
        Log.e("dengzi", "最小生成树顶点权值:" + adjvexList2.toString());
    }

}
