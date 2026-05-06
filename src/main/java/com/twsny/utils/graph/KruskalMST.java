package com.twsny.utils.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Kruskal 算法求最小生成树
 * 适用场景：稀疏图（边数较少）
 * 时间复杂度：O(E log E)
 */
public class KruskalMST extends MSTTree {
    // 并查集（Union-Find）
    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        // 查找根节点（路径压缩）
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // 合并两个集合（按秩合并）
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false; // 已经连通，会形成环
            }

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    // 边的定义
    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }

        @Override
        public String toString() {
            return String.format("%d -- %d [%d]", u, v, weight);
        }
    }

    /**
     * Kruskal 算法主方法
     * @param n 顶点数（0 到 n-1）
     * @param edges 所有边的列表
     * @return 最小生成树的边列表
     */
    public static List<Edge> kruskal(int n, List<Edge> edges) {
        // 1. 按权重排序
        Collections.sort(edges);

        // 2. 初始化并查集
        UnionFind uf = new UnionFind(n);

        // 3. 贪心选择边
        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        for (Edge edge : edges) {
            // 如果加入这条边不会形成环
            if (uf.union(edge.u, edge.v)) {
                mst.add(edge);
                totalWeight += edge.weight;

                // 已经有 n-1 条边，提前结束
                if (mst.size() == n - 1) {
                    break;
                }
            }
        }

        // 检查是否连通
        if (mst.size() != n - 1) {
            System.out.println("图不连通，无法生成最小生成树");
            return new ArrayList<>();
        }

        System.out.println("MST 总权重: " + totalWeight);
        return mst;
    }

    // 测试代码
    public static void main(String[] args) {
        int n = 4; // 顶点: 0=A, 1=B, 2=C, 3=D
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 3),  // A-B
                new Edge(0, 2, 4),  // A-C
                new Edge(1, 3, 1),  // B-D
                new Edge(2, 3, 2),  // C-D
                new Edge(1, 2, 5)   // B-C
        );

        List<Edge> mst = kruskal(n, edges);
        System.out.println("MST 边列表:");
        for (Edge e : mst) {
            System.out.println(e);
        }
    }
}
