package xyz.zerxoi.leetcode.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;

public class ActivitySelectionProblem {
    public static void main(String[] args) {
        Activity[] activities = {
            new Activity(1, 4, 5),
            new Activity(3, 5, 1),
            new Activity(0, 6, 8),
            new Activity(4, 7, 4),
            new Activity(3, 8, 6),
            new Activity(5, 9, 3),
            new Activity(6, 10, 2),
            new Activity(8, 11, 4)
        };
        preActivity(activities);
        OptimalActivitySelection(activities,11);
    }

    static void OptimalActivitySelection(Activity[] activities, int time) {
        Arrays.sort(activities);
        int[] pre = preActivity(activities);
        int[] max = new int[activities.length];
        int res = OptimalActivitySelectionAux(activities, activities.length-1, pre, max);
        System.out.println("Max salary:"+ res);
        
        for (int i=0; i < activities.length; i++) {
            System.out.println("Activity#" + i +":{" + "pre = "+ pre[i]+","+  "max = " + max[i] +"}");
        }

        int[] solution = optimalSolution(activities,pre,max);
        System.out.println();
        for (int i=0; i< solution.length;i++) {
            System.out.print(solution[i]+"\t");
        }
    }

    static int OptimalActivitySelectionAux(Activity[] activities, int index, int[] pre, int[] max) {
        if (index < 0)
            return 0;
        if (max[index] > 0)
            return max[index];
        int r1 = OptimalActivitySelectionAux(activities,index-1,pre,max);
        int r2 = OptimalActivitySelectionAux(activities,pre[index],pre,max) + activities[index].salary;
        if (r1 > r2) {
            max[index] = r1;
        } else {
            max[index] = r2;
        }
        return max[index];
    }

    // preActivity 返回活动的前一个最晚结束活动的数组
    // 数组元素大于0表示活动索引，数组元素等于-1表示无前一个活动
    static int[] preActivity(Activity[] activities) {
        int[] pre = new int[activities.length];
        pre[0] = -1;
        for (int i = 1; i < activities.length; i++) {
            int j = i-1;
            while (j >= 0) {
                if (activities[i].begin >= activities[j].end){
                    break;
                }
                j--;
            }
            pre[i] = j;
        }
        return pre;
    }

    static int[] optimalSolution(Activity[] activities, int[] pre, int[] max) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = activities.length-1; i >= 0; ) {
            if (activities[i].salary == max[i]) {
                list.add(i);
                break;
            } else if (pre[i] >= 0) {
                if (max[pre[i]] + activities[i].salary == max[i]) {
                    // System.out.println(i);
                    list.add(i);
                    i = pre[i];
                    continue;
                }
            }
            i--;
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

}

class Activity implements Comparable<Activity> {
    public int begin;
    public int end;
    public int salary;

    public Activity(int begin, int end, int salary) {
        this.begin = begin;
        this.end = end;
        this.salary = salary;
    }

    @Override
    public int compareTo(Activity activity) {
        return this.end - activity.end;
    }
}