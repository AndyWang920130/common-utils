package com.twsny.utils.algorithm;

/**
 * 爬楼梯问题
 * 每次可以爬1或2个台阶，爬到n级台阶有多少种方法
 */
public class ClimbStairs extends DynamicProgrammingAlgorithm {
    public int climbStairs(int n) {
        if (n <= 2) return n;

        // dp[i] 表示爬到第i级台阶的方法数
        int[] dp = new int[n + 1];
        dp[1] = 1;  // 1阶：1种方法
        dp[2] = 2;  // 2阶：2种方法（1+1 或 2）

        for (int i = 3; i <= n; i++) {
            // 状态转移：到第i阶可以从i-1跨1步，或从i-2跨2步
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    // 空间优化
    public int climbStairsOptimized(int n) {
        if (n <= 2) return n;
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public static void main(String[] args) {
        System.out.println(new ClimbStairs().climbStairs(10));
        System.out.println(new ClimbStairs().climbStairsOptimized(10));
    }
}
