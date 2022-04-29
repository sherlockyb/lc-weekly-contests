### 2244. Minimum Rounds to Complete All Tasks

You are given a **0-indexed** integer array `tasks`, where `tasks[i]` represents the difficulty level of a task. In each round, you can complete either 2 or 3 tasks of the **same difficulty level**.

Return *the **minimum** rounds required to complete all the tasks, or* `-1` *if it is not possible to complete all the tasks.*

 

**Example 1:**

```
Input: tasks = [2,2,3,3,2,4,4,4,4,4]
Output: 4
Explanation: To complete all the tasks, a possible plan is:
- In the first round, you complete 3 tasks of difficulty level 2. 
- In the second round, you complete 2 tasks of difficulty level 3. 
- In the third round, you complete 3 tasks of difficulty level 4. 
- In the fourth round, you complete 2 tasks of difficulty level 4.  
It can be shown that all the tasks cannot be completed in fewer than 4 rounds, so the answer is 4.
```

**Example 2:**

```
Input: tasks = [2,3,3]
Output: -1
Explanation: There is only 1 task of difficulty level 2, but in each round, you can only complete either 2 or 3 tasks of the same difficulty level. Hence, you cannot complete all the tasks, and the answer is -1.
```

 

**Constraints:**

- `1 <= tasks.length <= 105`
- `1 <= tasks[i] <= 109`

#### 思路（[代码](Solution.java)）

将题目翻译成我们的编程逻辑就是，相同的数放一起，成为一组，当每组内的数量 `s` 都满足 `s = 2m + 3n` 时，存在有效解，在此基础上要使 `r = m + n` 最小；否则无解。

我们先看有效解的情况，此时 `n = (s - 2m)/3`，带入第一个公式 `m + n` 后得到 `r = s/3 + 1/m`，要使 `r` 最小，则需 `m` 最大，因此先用 3 作为被除数，然后看余数情况，再考虑 2 的加入。

对于无效解，即在 `m >= 0, n >= 0` 的条件下，找不到组合值使得 `s = 2m + 3n`。那么什么情况下才会出现这种情况呢？或者说是否存在这种 `s` 不满足上述等式。从前面找最优解的过程来看，我们先以 3 作为除数，那么余数只可能是 0、1 和 2 三种。

* 为 0 时直接得到最优解（因为 `s > 0`，测试不存在 `n = 0` 的情况）；
* 为 2 时，补上一个 2 就得到最优解了，即 `m` = 1；
* 为 1 时，是没法直接补 2 的，此时得看 `3n` 是否能匀一个 3 出来。若 `n < 1`，匀不出来，则无解；若 `n >= 1`，则可以匀一个 3，这样的话”余数“就是 4 了，此时 `s` 可以表达为 `2*2 + 3*(n-1)`，得到最优解 `2 + (n - 1)`，即 `s = n + 1`

所以总结一下，思路就是：

> 1. 先排序，然后相同的元素在一起为一组，不同元素各为一组，然后逐组分析
> 2. 对于每组，假设长度为 s，则直接看 s 除以 3 之后的余数，根据余数的取值按照前面的思路处理便是了。若当前组，找到最优解，则继续下一组；否则直接返回 -1 表示无解。