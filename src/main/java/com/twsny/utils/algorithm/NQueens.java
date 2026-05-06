package com.twsny.utils.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N 皇后问题  在 n×n 的棋盘上放置 n 个皇后，使它们不能互相攻击（不同行、不同列、不同对角线）
 */
public class NQueens extends BacktrackingAlgorithm {
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        backtrack(board, 0);
        return result;
    }

    private void backtrack(char[][] board, int row) {
        // 结束条件：所有行都放好了皇后
        if (row == board.length) {
            result.add(construct(board));
            return;
        }

        // 在当前行尝试每一列
        for (int col = 0; col < board.length; col++) {
            if (!isValid(board, row, col)) {
                continue;  // 剪枝：当前位置不能放皇后
            }

            // 做选择
            board[row][col] = 'Q';

            // 下一行
            backtrack(board, row + 1);

            // 撤销选择
            board[row][col] = '.';
        }
    }

    // 检查是否可以放置皇后
    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;

        // 检查列
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }

        // 检查左上对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // 检查右上对角线
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }

    public static void main(String[] args) {
        NQueens nq = new NQueens();
        List<List<String>> solutions = nq.solveNQueens(4);
        System.out.println("4皇后问题的解数量：" + solutions.size());  // 输出2
        for (List<String> sol : solutions) {
            System.out.println(sol);
        }
    }
}
