package com.twsny.utils.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 活动选择问题 有 n 个活动，每个活动有开始时间 s[i] 和结束时间 f[i]，如何选最多的互不冲突的活动
 */
public class GreedyActivitySelection extends GreedyAlgorithm {
    public static List<Activity> selectActivities(List<Activity> activities) {
        // 按结束时间排序
        activities.sort(Comparator.comparingInt(a -> a.end));

        List<Activity> selected = new ArrayList<>();
        int lastEndTime = Integer.MIN_VALUE;

        for (Activity act : activities) {
            if (act.start >= lastEndTime) {
                selected.add(act);
                lastEndTime = act.end;
            }
        }
        return selected;
    }

    public static void main(String[] args) {
        List<Activity> acts = Arrays.asList(
                new Activity(1, 4),   // 活动1
                new Activity(3, 5),   // 活动2
                new Activity(0, 6),   // 活动3
                new Activity(5, 7),   // 活动4
                new Activity(8, 9),   // 活动5
                new Activity(5, 9)    // 活动6
        );

        List<Activity> result = selectActivities(acts);
        System.out.println("选择的活动数量：" + result.size());
        for (Activity a : result) {
            System.out.println("[" + a.start + ", " + a.end + "]");
        }
        // 输出：[1,4] [5,7] [8,9] → 共3个活动
    }

    static class Activity {
        int start, end;
        Activity(int s, int e) {
            start = s;
            end = e;
        }
    }
}
