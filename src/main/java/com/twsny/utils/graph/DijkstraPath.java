package com.twsny.utils.graph;

import java.util.*;

/**
 * Dijkstra
 */
public class DijkstraPath {
    static class Edge {
        String to;
        int weight;

        Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node {
        String name;
        int dist;

        Node(String name, int dist) {
            this.name = name;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {

        Map<String, List<Edge>> graph = new HashMap<>();

        graph.put("A", Arrays.asList(
            new Edge("B", 2),
            new Edge("C", 5)
        ));

        graph.put("B", Arrays.asList(
            new Edge("D", 1)
        ));

        graph.put("C", Arrays.asList(
            new Edge("D", 2)
        ));

        graph.put("D", new ArrayList<>());

        dijkstra(graph, "A");
    }

    public static void dijkstra(Map<String, List<Edge>> graph, String start) {

        Map<String, Integer> dist = new HashMap<>();
        PriorityQueue<Node> pq =
            new PriorityQueue<>(Comparator.comparingInt(a -> a.dist));

        for (String node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }

        dist.put(start, 0);
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {

            Node cur = pq.poll();

            if (cur.dist > dist.get(cur.name)) continue;

            for (Edge edge : graph.get(cur.name)) {

                int newDist = dist.get(cur.name) + edge.weight;

                if (newDist < dist.get(edge.to)) {

                    dist.put(edge.to, newDist);

                    pq.offer(new Node(edge.to, newDist));
                }
            }
        }

        System.out.println(dist);
    }
}
