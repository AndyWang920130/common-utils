package com.twsny.utils.graph;

/**
 * Floyd-Warshall
 */
public class FloydWarshallPath {
    static final int INF = 1_000_000;

    public static void main(String[] args) {

        int[][] dist = {
            {0,   4,  10},
            {INF, 0,   3},
            {INF, INF, 0}
        };

        int n = dist.length;

        for (int k = 0; k < n; k++) {

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    dist[i][j] = Math.min(
                        dist[i][j],
                        dist[i][k] + dist[k][j]
                    );
                }
            }
        }

        print(dist);
    }

    static void print(int[][] dist) {
        for (int[] row : dist) {
            for (int d : row) {
                System.out.print((d >= INF ? "INF" : d) + "\t");
            }
            System.out.println();
        }
    }
}
