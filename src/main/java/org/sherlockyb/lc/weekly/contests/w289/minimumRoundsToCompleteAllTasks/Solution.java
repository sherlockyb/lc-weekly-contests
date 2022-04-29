package org.sherlockyb.lc.weekly.contests.w289.minimumRoundsToCompleteAllTasks;

import java.util.Arrays;

/**
 * @author sherlockyb
 */
public class Solution {
    public int minimumRounds(int[] tasks) {
        if (tasks == null || tasks.length < 1) {
            return -1;
        }
        Arrays.sort(tasks);

        int minSteps = 0;
        int prev = -1;
        int groupLen = 0;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != prev) {
                if (groupLen > 0) {
                    int groupMinSteps = getMinStepsForFinishingTaskGroup(groupLen);
                    if (groupMinSteps == -1) {
                        return -1;
                    }
                    minSteps += groupMinSteps;
                }
                prev = tasks[i];
                groupLen = 1;
            } else {
                groupLen++;
            }
        }

        // tail part
        if (groupLen > 0) {
            int groupMinSteps = getMinStepsForFinishingTaskGroup(groupLen);
            if (groupMinSteps == -1) {
                return -1;
            }
            minSteps += groupMinSteps;
        }

        return minSteps;
    }

    private int getMinStepsForFinishingTaskGroup(int taskGroupLen) {
        int reminder = taskGroupLen % 3;
        if (reminder == 0) {
            return taskGroupLen / 3;
        } else if (reminder == 2) {
            return taskGroupLen / 3 + 1;
        } else {
            // reminder = 1
            int n = taskGroupLen / 3;
            if (n >= 1) {
                return n + 1;
            } else {
                return -1;
            }
        }
    }
}
