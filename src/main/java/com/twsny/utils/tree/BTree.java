package com.twsny.utils.tree;

/***
 * Binary Tree
 * @param <T>
 */
public class BTree<T> {
    BinaryTreeNode<T> root;

    // 前序遍历
    void preOrder(BinaryTreeNode<T> node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // 中序遍历
    void inOrder(BinaryTreeNode<T> node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    // 后序遍历
    void postOrder(BinaryTreeNode<T> node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    private static class BinaryTreeNode<T> {
        T data;
        BinaryTreeNode<T> left;
        BinaryTreeNode<T> right;

        BinaryTreeNode(T data) {
            this.data = data;
            left = right = null;
        }

        public void addLeft(BinaryTreeNode<T> left) {
            this.left = left;
        }

        public void addRight(BinaryTreeNode<T> right) {
            this.right = right;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode<String> a = new BinaryTreeNode<>("A");
        BinaryTreeNode<String> b = new BinaryTreeNode<>("B");
        BinaryTreeNode<String> c = new BinaryTreeNode<>("C");
        BinaryTreeNode<String> d = new BinaryTreeNode<>("D");
        BinaryTreeNode<String> e = new BinaryTreeNode<>("E");
        BinaryTreeNode<String> f = new BinaryTreeNode<>("F");
        BinaryTreeNode<String> g = new BinaryTreeNode<>("G");
        a.addLeft(b);
        a.addRight(c);
        b.addLeft(d);
        c.addLeft(e);
        e.addLeft(f);
        c.addRight(g);

        BTree<String> bTree = new BTree<>();
        System.out.println("preOrder:");
        bTree.preOrder(a);
        System.out.println();
        System.out.println("inOrder:");
        bTree.inOrder(a);
        System.out.println();
        System.out.println("postOrder:");
        bTree.postOrder(a);
    }

}
