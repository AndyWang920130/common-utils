package com.twsny.utils.algorithm;

/**
 * 最大子数组和
 * 给定数组 [-2,1,-3,4,-1,2,1,-5,4]，找和最大的连续子数组
 */
public class MaxSubArray extends DynamicProgrammingAlgorithm{
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // dp[i] 表示以nums[i]结尾的最大子数组和
        int[] dp = new int[n];

        dp[0] = nums[0];
        int maxSum = dp[0];

        for (int i = 1; i < n; i++) {
            // 要么自己单独成一段，要么加入前面的子数组
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    // 空间优化（不需要数组）
    public int maxSubArrayOptimized(int[] nums) {
        int currSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaxSubArray msa = new MaxSubArray();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(msa.maxSubArray(nums));  // 输出：6 (子数组[4,-1,2,1])
        System.out.println(msa.maxSubArrayOptimized(nums));  // 输出：6 (子数组[4,-1,2,1])

    }
}
