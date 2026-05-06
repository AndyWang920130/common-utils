package com.twsny.utils.algorithm;

import java.util.Arrays;

/**
 * 找零钱问题 有面值 25、10、5、1 的硬币，用最少的硬币数支付 贪心算法
 */
public class GreedyCoinChange extends GreedyAlgorithm {
    public static void greedyCoinChange(int amount, int[] coins) {
        // 硬币面值降序排序
        Arrays.sort(coins);

        int remaining = amount;
        System.out.println("找零 " + amount + " 元：");

        for (int i = coins.length - 1; i >= 0; i--) {
            int count = remaining / coins[i];
            if (count > 0) {
                System.out.println(coins[i] + "元 × " + count);
                remaining -= count * coins[i];
            }
        }
    }

    public static void main(String[] args) {
        int[] coins = {1, 5, 10, 25};
        greedyCoinChange(41, coins);
        // 输出：25×1, 10×1, 5×1, 1×1 → 4枚硬币
    }
}
