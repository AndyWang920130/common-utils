package com.twsny.utils.graph;

import java.util.*;

/**
 * BFS
 */
public class BFSShortestPath {
    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();

        graph.put("A", Arrays.asList("B", "D"));
        graph.put("B", Arrays.asList("A", "C", "E"));
        graph.put("C", Arrays.asList("B"));
        graph.put("D", Arrays.asList("A", "E"));
        graph.put("E", Arrays.asList("B", "D"));

        new BFSShortestPath().bfs(graph, "A");
        new BFSShortestPath().findPath(graph, "A", "D");
    }

    /**
     * 广度优先查找
     * @param graph
     * @param start
     */
    public static void bfs(Map<String, List<String>> graph, String start) {

        Queue<String> queue = new LinkedList<>();

        Set<String> visited = new HashSet<>();

        Map<String, Integer> distance = new HashMap<>();

        queue.offer(start);
        visited.add(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {

            String cur = queue.poll();

            for (String next : graph.get(cur)) {

                if (!visited.contains(next)) {

                    visited.add(next);

                    queue.offer(next);

                    distance.put(next, distance.get(cur) + 1);
                }
            }
        }

        System.out.println("最短距离：");
        System.out.println(distance);
    }

    /**
     * 查找两点间的路径
     * @param graph
     * @param start
     * @param target
     */
    public static void findPath(Map<String, List<String>> graph,
                                String start,
                                String target) {

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> prev = new HashMap<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            String cur = queue.poll();

            if (cur.equals(target)) break;

            for (String next : graph.get(cur)) {

                if (!visited.contains(next)) {

                    visited.add(next);
                    queue.offer(next);

                    prev.put(next, cur);
                }
            }
        }

        List<String> path = new ArrayList<>();

        for (String at = target; at != null; at = prev.get(at)) {
            path.add(at);
        }

        Collections.reverse(path);

        System.out.println("最短路径：" + path);
    }
}
