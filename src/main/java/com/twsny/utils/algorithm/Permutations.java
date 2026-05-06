package com.twsny.utils.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列问题  给定不含重复数字的数组 [1,2,3]，返回所有可能的排列
 */
public class Permutations extends BacktrackingAlgorithm {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, path, used);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> path, boolean[] used) {
        // 结束条件：路径长度等于数组长度
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));  // 记录完整解
            return;
        }

        // 遍历所有选择
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;  // 剪枝：已使用

            // 做选择
            path.add(nums[i]);
            used[i] = true;

            // 下一层
            backtrack(nums, path, used);

            // 撤销选择（回溯核心）
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Permutations p = new Permutations();
        List<List<Integer>> result = p.permute(new int[]{1, 2, 3});
        System.out.println(result);
        // 输出：[[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
    }
}
