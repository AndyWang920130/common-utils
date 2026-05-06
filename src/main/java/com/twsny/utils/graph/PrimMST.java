package com.twsny.utils.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Prim 算法求最小生成树
 * 适用场景：稠密图（边数较多）
 * 时间复杂度：O(E log V)
 */
public class PrimMST extends MSTTree {
    // 边的定义（用于邻接表）
    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // 优先队列中的节点
    static class Node implements Comparable<Node> {
        int vertex;
        int key;  // 到树的最小边权

        public Node(int vertex, int key) {
            this.vertex = vertex;
            this.key = key;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.key, other.key);
        }
    }

    /**
     * Prim 算法主方法
     * @param graph 邻接表表示的无向图
     * @param start 起始顶点
     * @return 最小生成树的边（父节点数组）
     */
    public static int[] prim(List<List<Edge>> graph, int start) {
        int n = graph.size();

        // 记录每个顶点到当前树的最小边权
        int[] key = new int[n];
        Arrays.fill(key, Integer.MAX_VALUE);

        // 记录最小生成树中每个顶点的父节点
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        // 标记顶点是否已在树中
        boolean[] inMST = new boolean[n];

        // 优先队列（小顶堆）
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 从起始顶点开始
        key[start] = 0;
        pq.offer(new Node(start, 0));

        int totalWeight = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;

            // 如果已经加入树中，跳过
            if (inMST[u]) {
                continue;
            }

            // 将 u 加入 MST
            inMST[u] = true;
            totalWeight += key[u];

            // 遍历 u 的所有邻接边
            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                // 如果 v 不在树中，且当前边权更小
                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = u;
                    pq.offer(new Node(v, key[v]));
                }
            }
        }

        System.out.println("MST 总权重: " + totalWeight);

        // 检查是否所有顶点都连通
        for (int i = 0; i < n; i++) {
            if (!inMST[i]) {
                System.out.println("图不连通，无法生成最小生成树");
                return new int[0];
            }
        }

        return parent;
    }

    // 打印 MST
    public static void printMST(int[] parent, List<List<Edge>> graph) {
        System.out.println("MST 边列表:");
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] != -1) {
                // 找到权重
                int weight = 0;
                for (Edge edge : graph.get(i)) {
                    if (edge.to == parent[i]) {
                        weight = edge.weight;
                        break;
                    }
                }
                System.out.printf("%d -- %d [%d]\n", parent[i], i, weight);
            }
        }
    }

    // 测试代码
    public static void main(String[] args) {
        int n = 4; // 顶点: 0=A, 1=B, 2=C, 3=D

        // 构建邻接表
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 添加边（无向图）
        addEdge(graph, 0, 1, 3);  // A-B
        addEdge(graph, 0, 2, 4);  // A-C
        addEdge(graph, 1, 3, 1);  // B-D
        addEdge(graph, 2, 3, 2);  // C-D
        addEdge(graph, 1, 2, 5);  // B-C

        int[] parent = prim(graph, 0);
        printMST(parent, graph);
    }

    // 辅助方法：添加无向边
    private static void addEdge(List<List<Edge>> graph, int u, int v, int weight) {
        graph.get(u).add(new Edge(v, weight));
        graph.get(v).add(new Edge(u, weight));
    }
}
