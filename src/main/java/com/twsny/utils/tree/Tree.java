package com.twsny.utils.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Tree
 * @param <T>
 */
public class Tree<T> {
    private T data;
    private List<Tree<T>> children = new ArrayList<>();

    public Tree(T data) {
        this.data = data;
    }

    public void addChildren(Tree<T> child) {
        this.children.add(child);
    }

    // 深度优先遍历
    void dfs() {
        System.out.print(data + " ");
        for (Tree<T> child : children) {
            child.dfs();
        }
    }

    // 广度优先
    void bfs() {
        Queue<Tree<T>> queue = new LinkedList<>();
        queue.offer(this);  // 根节点入队

        while (!queue.isEmpty()) {
            Tree<T> current = queue.poll();  // 取出队首
            System.out.print(current.data + " ");  // 访问

            // 所有孩子入队（放到尾部）
            for (Tree<T> child : current.children) {
                queue.offer(child);
            }
        }
    }


    public static void main(String[] args) {
        Tree<String> root = new Tree<>("A");
        Tree<String> b = new Tree<>("B");
        Tree<String> c = new Tree<>("C");
        Tree<String> d = new Tree<>("D");
        Tree<String> e = new Tree<>("E");
        Tree<String> f = new Tree<>("F");
        Tree<String> g = new Tree<>("G");
        root.addChildren(b);
        root.addChildren(c);
        root.addChildren(d);
        d.addChildren(e);
        d.addChildren(g);
        e.addChildren(f);


        System.out.println("dfs: ");
        root.dfs();

        System.out.println("");

        System.out.println("bfs: ");
        root.bfs();
    }

}
