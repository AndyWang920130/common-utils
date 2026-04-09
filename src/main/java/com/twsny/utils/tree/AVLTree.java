package com.twsny.utils.tree;

/**
 * Adelson-Velsky and Landis Tree
 */
public class AVLTree {
    class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            height = 1; // 新节点高度为1
        }
    }

    private Node root;

    // 获取高度
    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    // 获取平衡因子
    private int getBalance(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    // 右旋转
    //      30
    //      /  \
    //     20  40
    //    /  \
    //   10  25
    //  /
    // 5

    //       20
    //      /  \
    //     10   30
    //    /    /  \
    //   5   25   40
    private Node rightRotate(Node y) {
        System.out.println("节点: " + y.key + "右旋");
        Node x = y.left;
        Node T2 = x.right;

        // 旋转
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 左旋转
    //       20
    //      /  \
    //     10   30
    //         /  \
    //        25   35
    //              \
    //              40

    //       30
    //      /  \
    //     20   35
    //    /  \    \
    //   10  25   40
    private Node leftRotate(Node x) {
        System.out.println("节点: " + x.key + "左旋");
        Node y = x.right;
        Node T2 = y.left;

        // 旋转
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // 插入（带平衡）
    void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        // 1. 标准BST插入
        if (node == null) {
            System.out.println("插入 " + key);
            return new Node(key);
        }

        if (key < node.key)
            node.left = insertRec(node.left, key);
        else if (key > node.key)
            node.right = insertRec(node.right, key);
        else
            return node; // 重复值

        // 2. 更新高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. 获取平衡因子
        int balance = getBalance(node);

        // 4. 四种不平衡情况

        // LL情况
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // RR情况
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);



        // LR情况
        //       30
        //      /  \
        //     20  40
        //    /  \
        //   10   25
        //         \
        //         28

        //       30
        //      /  \
        //     25  40  ← 20变成了25的左子树
        //    /  \
        //   20  28
        //  /
        // 10

        //        25
        //      /  \
        //     20   30
        //    /    /  \
        //   10   28  40
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL情况
        //       20
        //      /  \
        //     10   30
        //         /  \
        //        25   35
        //       /
        //     22

        //       20
        //      /  \
        //     10   25  ← 30变成了25的右子树
        //         /  \
        //       22   30
        //             \
        //             35

        //       25
        //      /  \
        //     20   30
        //    /  \    \
        //   10  22   35
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 中序遍历
    void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + "(h=" + root.height + ") ");
            inorderRec(root.right);
        }
    }


    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // 测试插入会引发旋转的情况
        tree.insert(10);
        tree.insert(20);
        tree.insert(30); // 会触发左旋转
        tree.insert(5);
        tree.insert(15);
        tree.insert(25);
        tree.insert(23);
        tree.insert(4);
        tree.insert(3);

        System.out.println("AVL树中序遍历:");
        tree.inorder(); // 有序输出，且每个节点高度平衡
    }
}
