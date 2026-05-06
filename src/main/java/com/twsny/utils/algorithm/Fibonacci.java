package com.twsny.utils.algorithm;

import java.util.Arrays;

/**
 * 斐波那契数列 三种实现方式
 * 1. 暴力递归 时间复杂度：O(2^n) 性能差
 * 2. 记忆化搜索（自顶向下）
 * 3. 动态规划（自底向上）
 */
public class Fibonacci extends DynamicProgrammingAlgorithm{
    /**
     * 1. 暴力递归
     */
    static class Fib1 {
        public int fib(int n) {
            if (n <= 1) return n;
            return fib(n-1) + fib(n-2);
        }
    }

    static class Fib2 {
        int[] memo;
        public int fib(int n) {
            memo = new int[n + 1];
            Arrays.fill(memo, -1);
            return dfs(n);
        }

        private int dfs(int n) {
            if (n <= 1) return n;
            if (memo[n] != -1) return memo[n];
            memo[n] = dfs(n - 1) + dfs(n - 2);
            return memo[n];
        }
    }

    static class Fib3 {
        public int fib(int n) {
            if (n <= 1) return n;

            int[] dp = new int[n + 1];
            dp[0] = 0;  // 初始化
            dp[1] = 1;

            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i-1] + dp[i-2];  // 状态转移方程
            }

            return dp[n];
        }

        // 空间优化（滚动数组）
        public int fibOptimized(int n) {
            if (n <= 1) return n;
            int prev2 = 0, prev1 = 1;
            for (int i = 2; i <= n; i++) {
                int curr = prev1 + prev2;
                prev2 = prev1;
                prev1 = curr;
            }
            return prev1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Fib1().fib(10));
        System.out.println(new Fib2().fib(10));
        System.out.println(new Fib3().fib(10));
        System.out.println(new Fib3().fibOptimized(10));
    }
}
