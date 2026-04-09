package com.twsny.utils.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Red Black Tree
 * 1. 性质1：节点是红色或黑色
 * 2. 性质2：根节点是黑色
 * 3. 性质3：所有叶子节点（NIL）都是黑色
 * 4. 性质4：红色节点的子节点必须是黑色
 * 5. 性质5：从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点
 */
public class RedBlackTree {
    // 颜色常量
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * 红黑树节点类
     */
    class Node {
        int key;
        Node left, right, parent;
        boolean color;

        Node(int key) {
            this.key = key;
            this.color = RED; // 新节点默认为红色
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        @Override
        public String toString() {
            return key + (color ? "(R)" : "(B)");
        }
    }

    // 根节点
    private Node root;
    // 哨兵节点（代表叶子节点）
    private final Node NIL;

    /**
     * 构造函数
     */
    public RedBlackTree() {
        NIL = new Node(0);
        NIL.color = BLACK;
        NIL.left = null;
        NIL.right = null;
        root = NIL;
    }

    // ========== 基本操作 ==========

    /**
     * 左旋转
     *        x                 y
     *       / \               / \
     *      a   y     =>      x   c
     *         / \           / \
     *        b   c         a   b
     */
    private void leftRotate(Node x) {
        Node y = x.right;

        // 将y的左子树变为x的右子树
        x.right = y.left;
        if (y.left != NIL) {
            y.left.parent = x;
        }

        // 更新y的父节点
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        // 将x设置为y的左子树
        y.left = x;
        x.parent = y;
    }

    /**
     * 右旋转
     *        y                 x
     *       / \               / \
     *      x   c     =>      a   y
     *     / \                   / \
     *    a   b                 b   c
     */
    private void rightRotate(Node y) {
        Node x = y.left;

        // 将x的右子树变为y的左子树
        y.left = x.right;
        if (x.right != NIL) {
            x.right.parent = y;
        }

        // 更新x的父节点
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }

        // 将y设置为x的右子树
        x.right = y;
        y.parent = x;
    }

    // ========== 插入操作 ==========

    /**
     * 插入节点
     */
    public void insert(int key) {
        Node z = new Node(key);
        z.left = NIL;
        z.right = NIL;

        Node y = null;
        Node x = root;

        // 1. 找到插入位置（标准BST插入）
        while (x != NIL) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else if (z.key > x.key) {
                x = x.right;
            } else {
                System.out.println("重复键值: " + key);
                return; // 不允许重复值
            }
        }

        z.parent = y;
        if (y == null) {
            root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }

        // 2. 如果是根节点，直接设为黑色
        if (z.parent == null) {
            z.color = BLACK;
            return;
        }

        // 3. 如果是祖父节点为空，直接返回
        if (z.parent.parent == null) {
            return;
        }

        // 4. 修复红黑树性质
        fixInsert(z);
    }

    /**
     * 插入后修复红黑树性质
     */
    private void fixInsert(Node z) {
        Node uncle;

        while (z.parent != null && z.parent.color == RED) {
            if (z.parent == z.parent.parent.left) {
                // 情况A：父节点是祖父的左孩子
                uncle = z.parent.parent.right;

                if (uncle.color == RED) {
                    // 情况1：叔叔是红色
                    z.parent.color = BLACK;
                    uncle.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent; // 继续向上检查
                } else {
                    if (z == z.parent.right) {
                        // 情况2：z是右孩子
                        z = z.parent;
                        leftRotate(z);
                    }
                    // 情况3：z是左孩子
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                // 情况B：父节点是祖父的右孩子（对称情况）
                uncle = z.parent.parent.left;

                if (uncle.color == RED) {
                    // 情况1：叔叔是红色
                    z.parent.color = BLACK;
                    uncle.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        // 情况2：z是左孩子
                        z = z.parent;
                        rightRotate(z);
                    }
                    // 情况3：z是右孩子
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    leftRotate(z.parent.parent);
                }
            }

            // 如果z成为根节点，跳出循环
            if (z == root) break;
        }

        // 确保根节点为黑色
        root.color = BLACK;
    }

    // ========== 删除操作 ==========

    /**
     * 查找最小节点
     */
    private Node minimum(Node node) {
        while (node.left != NIL) {
            node = node.left;
        }
        return node;
    }

    /**
     * 节点移植（用v替换u）
     */
    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    /**
     * 删除节点
     */
    public void delete(int key) {
        Node z = searchNode(root, key);
        if (z == NIL) {
            System.out.println("未找到节点: " + key);
            return;
        }

        Node y = z;
        Node x;
        boolean yOriginalColor = y.color;

        if (z.left == NIL) {
            // 情况1：没有左孩子
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == NIL) {
            // 情况2：没有右孩子
            x = z.left;
            transplant(z, z.left);
        } else {
            // 情况3：有两个孩子
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;

            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOriginalColor == BLACK) {
            fixDelete(x);
        }
    }

    /**
     * 删除后修复红黑树性质
     */
    private void fixDelete(Node x) {
        Node sibling;

        while (x != root && x.color == BLACK) {
            if (x == x.parent.left) {
                sibling = x.parent.right;

                if (sibling.color == RED) {
                    // 情况1：兄弟节点是红色
                    sibling.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    sibling = x.parent.right;
                }

                if (sibling.left.color == BLACK && sibling.right.color == BLACK) {
                    // 情况2：兄弟节点的两个孩子都是黑色
                    sibling.color = RED;
                    x = x.parent;
                } else {
                    if (sibling.right.color == BLACK) {
                        // 情况3：兄弟节点的右孩子是黑色
                        sibling.left.color = BLACK;
                        sibling.color = RED;
                        rightRotate(sibling);
                        sibling = x.parent.right;
                    }
                    // 情况4：兄弟节点的右孩子是红色
                    sibling.color = x.parent.color;
                    x.parent.color = BLACK;
                    sibling.right.color = BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                // 对称情况
                sibling = x.parent.left;

                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    sibling = x.parent.left;
                }

                if (sibling.right.color == BLACK && sibling.left.color == BLACK) {
                    sibling.color = RED;
                    x = x.parent;
                } else {
                    if (sibling.left.color == BLACK) {
                        sibling.right.color = BLACK;
                        sibling.color = RED;
                        leftRotate(sibling);
                        sibling = x.parent.left;
                    }

                    sibling.color = x.parent.color;
                    x.parent.color = BLACK;
                    sibling.left.color = BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }

        x.color = BLACK;
    }

    // ========== 查找操作 ==========

    /**
     * 查找节点
     */
    public boolean search(int key) {
        return searchNode(root, key) != NIL;
    }

    private Node searchNode(Node node, int key) {
        if (node == NIL || key == node.key) {
            return node;
        }

        if (key < node.key) {
            return searchNode(node.left, key);
        } else {
            return searchNode(node.right, key);
        }
    }

    // ========== 遍历操作 ==========

    /**
     * 中序遍历（有序输出）
     */
    public void inorder() {
        System.out.print("中序遍历: ");
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node node) {
        if (node != NIL) {
            inorderRec(node.left);
            System.out.print(node + " ");
            inorderRec(node.right);
        }
    }

    /**
     * 前序遍历
     */
    public void preorder() {
        System.out.print("前序遍历: ");
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(Node node) {
        if (node != NIL) {
            System.out.print(node + " ");
            preorderRec(node.left);
            preorderRec(node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void postorder() {
        System.out.print("后序遍历: ");
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(Node node) {
        if (node != NIL) {
            postorderRec(node.left);
            postorderRec(node.right);
            System.out.print(node + " ");
        }
    }

    /**
     * 层序遍历（广度优先）
     */
    public void levelOrder() {
        System.out.print("层序遍历: ");
        if (root == NIL) return;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node + " ");

            if (node.left != NIL) queue.offer(node.left);
            if (node.right != NIL) queue.offer(node.right);
        }
        System.out.println();
    }

    // ========== 验证红黑树性质 ==========

    /**
     * 验证红黑树性质
     */
    public boolean validate() {
        if (root == NIL) {
            System.out.println("空树是有效的红黑树");
            return true;
        }

        // 性质2：根节点必须是黑色
        if (root.color != BLACK) {
            System.out.println("违反性质2: 根节点不是黑色");
            return false;
        }

        // 性质3：所有叶子节点是黑色（通过NIL实现）

        // 性质4：红色节点不能有红色子节点
        if (!checkRedProperty(root)) {
            System.out.println("违反性质4: 存在连续的红色节点");
            return false;
        }

        // 性质5：所有路径黑色节点数相同
        int blackCount = -1;
        if (!checkBlackHeight(root, 0, blackCount)) {
            System.out.println("违反性质5: 黑色高度不一致");
            return false;
        }

        System.out.println("红黑树性质验证通过");
        return true;
    }

    /**
     * 检查性质4：红色节点的子节点必须是黑色
     */
    private boolean checkRedProperty(Node node) {
        if (node == NIL) return true;

        if (node.color == RED) {
            if (node.left.color == RED || node.right.color == RED) {
                return false;
            }
        }

        return checkRedProperty(node.left) && checkRedProperty(node.right);
    }

    /**
     * 检查性质5：黑色高度一致性
     */
    private boolean checkBlackHeight(Node node, int blackCount, int target) {
        if (node == NIL) {
            // 到达叶子节点
            if (target == -1) {
                target = blackCount; // 第一条路径的黑色高度
            }
            return blackCount == target;
        }

        // 增加黑色节点计数
        if (node.color == BLACK) blackCount++;

        return checkBlackHeight(node.left, blackCount, target) &&
            checkBlackHeight(node.right, blackCount, target);
    }

    // ========== 工具方法 ==========

    /**
     * 获取树的高度
     */
    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node node) {
        if (node == NIL) return 0;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }

    /**
     * 获取节点数量
     */
    public int size() {
        return sizeRec(root);
    }

    private int sizeRec(Node node) {
        if (node == NIL) return 0;
        return 1 + sizeRec(node.left) + sizeRec(node.right);
    }

    /**
     * 清空树
     */
    public void clear() {
        root = NIL;
    }

    /**
     * 获取最小值
     */
    public int min() {
        Node node = root;
        while (node.left != NIL) {
            node = node.left;
        }
        return node.key;
    }

    /**
     * 获取最大值
     */
    public int max() {
        Node node = root;
        while (node.right != NIL) {
            node = node.right;
        }
        return node.key;
    }


    // ========== 可视化输出 ==========

    /**
     * 打印树结构
     */
    public void printTree() {
        System.out.println("\n红黑树结构:");
        printTreeRec(root, "", true);
    }

    private void printTreeRec(Node node, String prefix, boolean isLeft) {
        if (node != NIL) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node);
            printTreeRec(node.left, prefix + (isLeft ? "│   " : "    "), true);
            printTreeRec(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    /**
     * 图形化显示
     */
    public void display() {
        if (root == NIL) {
            System.out.println("空树");
            return;
        }

        List<List<String>> lines = new ArrayList<>();
        List<Node> level = new ArrayList<>();
        List<Node> next = new ArrayList<>();

        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;

            for (Node node : level) {
                if (node == NIL) {
                    line.add(null);
                    next.add(NIL);
                    next.add(NIL);
                } else {
                    String aa = node.key + (node.color == RED ? "R" : "B");
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(node.left);
                    next.add(node.right);

                    if (node.left != NIL) nn++;
                    if (node.right != NIL) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<Node> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    // 打印连接线
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // 打印空格
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // 打印节点值
            for (int j = 0; j < line.size(); j++) {
                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }

    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();

        System.out.println("=== 红黑树插入测试 ===");

        // 测试用例1：基本插入
        int[] testData = {10, 20, 30, 15, 25, 5, 35, 40, 50, 45};
        for (int val : testData) {
            System.out.println("\n插入: " + val);
            rbt.insert(val);
            rbt.validate();
        }

        // 遍历测试
        System.out.println("\n=== 遍历测试 ===");
        rbt.inorder();
        rbt.preorder();
        rbt.postorder();
        rbt.levelOrder();

        // 打印树结构
        System.out.println("\n=== 树结构 ===");
        rbt.printTree();

        // 图形化显示
        System.out.println("\n=== 图形化显示 ===");
        rbt.display();

        // 树的基本信息
        System.out.println("\n=== 树的基本信息 ===");
        System.out.println("高度: " + rbt.height());
        System.out.println("节点数: " + rbt.size());
        System.out.println("最小值: " + rbt.min());
        System.out.println("最大值: " + rbt.max());

        // 查找测试
        System.out.println("\n=== 查找测试 ===");
        System.out.println("查找25: " + rbt.search(25)); // true
        System.out.println("查找100: " + rbt.search(100)); // false

        // 删除测试
        System.out.println("\n=== 删除测试 ===");
        System.out.println("删除20:");
        rbt.delete(20);
        rbt.validate();
        rbt.inorder();

        System.out.println("删除根节点10:");
        rbt.delete(10);
        rbt.validate();
        rbt.inorder();

        // 边界测试
        System.out.println("\n=== 边界测试 ===");
        RedBlackTree emptyTree = new RedBlackTree();
        System.out.println("空树验证: " + emptyTree.validate());

        // 插入重复值测试
        System.out.println("\n插入重复值30:");
        rbt.insert(30); // 应该提示重复

        // 清空测试
        System.out.println("\n清空树:");
        rbt.clear();
        System.out.println("节点数: " + rbt.size());
        System.out.println("空树验证: " + rbt.validate());

        // 复杂场景测试
        System.out.println("\n=== 复杂场景测试 ===");
        RedBlackTree complexTree = new RedBlackTree();
        // 插入有序序列（最坏情况）
        for (int i = 1; i <= 20; i++) {
            complexTree.insert(i);
        }
        System.out.println("插入有序序列1-20");
        System.out.println("高度: " + complexTree.height());
        System.out.println("验证: " + complexTree.validate());
        complexTree.inorder();
    }
}
