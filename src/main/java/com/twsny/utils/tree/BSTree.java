package com.twsny.utils.tree;

/**
 * Binary Search Tree
 */
public class BSTree {
    private class Node {
        int key;
        Node left, right;
        Node(int key) {
            this.key = key;
            left = right = null;
        }
    }

    private Node root;

    // 插入（无平衡）
    void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root; // 无平衡操作
    }


    // 查找
    boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(Node root, int key) {
        if (root == null) return false;
        if (root.key == key) return true;

        return key < root.key ?
            searchRec(root.left, key) :
            searchRec(root.right, key);
    }


    // 中序遍历（有序输出）
    void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    public static void main(String[] args) {
        BSTree tree = new BSTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);

        System.out.println("BST中序遍历:");
        tree.inorder(); // 20 30 40 50 70

        System.out.println("查找40: " + tree.search(40)); // true
        System.out.println("查找60: " + tree.search(60)); // false
    }

}
