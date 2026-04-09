package com.twsny.utils.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bellman-Ford
 */
public class BellmanFordPath {
    static class Edge {
        String from;
        String to;
        int weight;

        Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 4),
            new Edge("A", "C", 5),
            new Edge("B", "C", -2),
            new Edge("C", "D", 3)
        );

        bellmanFord(edges, Arrays.asList("A","B","C","D"), "A");
    }

    public static void bellmanFord(List<Edge> edges,
                                   List<String> nodes,
                                   String start) {

        Map<String, Integer> dist = new HashMap<>();

        for (String node : nodes) {
            dist.put(node, Integer.MAX_VALUE);
        }

        dist.put(start, 0);

        int V = nodes.size();

        for (int i = 1; i <= V - 1; i++) {

            for (Edge edge : edges) {

                if (dist.get(edge.from) == Integer.MAX_VALUE) continue;

                int newDist = dist.get(edge.from) + edge.weight;

                if (newDist < dist.get(edge.to)) {
                    dist.put(edge.to, newDist);
                }
            }
        }

        System.out.println(dist);
    }
}
