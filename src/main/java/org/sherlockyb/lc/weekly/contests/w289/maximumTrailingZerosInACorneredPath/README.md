### 2245. Maximum Trailing Zeros in a Cornered Path

You are given a 2D integer array `grid` of size `m x n`, where each cell contains a positive integer.

A **cornered path** is defined as a set of adjacent cells with **at most** one turn. More specifically, the path should exclusively move either **horizontally** or **vertically** up to the turn (if there is one), without returning to a previously visited cell. After the turn, the path will then move exclusively in the **alternate** direction: move vertically if it moved horizontally, and vice versa, also without returning to a previously visited cell.

The **product** of a path is defined as the product of all the values in the path.

Return *the **maximum** number of **trailing zeros** in the product of a cornered path found in* `grid`.

Note:

- **Horizontal** movement means moving in either the left or right direction.
- **Vertical** movement means moving in either the up or down direction.

 

**Example 1:**

![img](https://assets.leetcode.com/uploads/2022/03/23/ex1new2.jpg)

```
Input: grid = [[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]
Output: 3
Explanation: The grid on the left shows a valid cornered path.
It has a product of 15 * 20 * 6 * 1 * 10 = 18000 which has 3 trailing zeros.
It can be shown that this is the maximum trailing zeros in the product of a cornered path.

The grid in the middle is not a cornered path as it has more than one turn.
The grid on the right is not a cornered path as it requires a return to a previously visited cell.
```

**Example 2:**

![img](https://assets.leetcode.com/uploads/2022/03/25/ex2.jpg)

```
Input: grid = [[4,3,2],[7,6,1],[8,8,8]]
Output: 0
Explanation: The grid is shown in the figure above.
There are no cornered paths in the grid that result in a product with a trailing zero.
```

 

**Constraints:**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 105`
- `1 <= m * n <= 105`
- `1 <= grid[i][j] <= 1000`

#### 思路（[代码](Solution.java)）

穷举+剪枝。逐行或逐列遍历每个单元格，依次以每一个单元格为起点，向其他方向出发，每前进一步，计算一下当前 `trailing zero`, 跟历史走过的子路径的 `trailing zero` 进行比较，取最大值；每遍历一个单元格，更新当前 `trailing zero` 的最大值，最终遍历完所有单元格，得到全局最大值。

假设我们逐行遍历单元格。由于对于一条 `cornered path` 是无方向的，即假设其起点是 A 且终点是 B，那么从 A 出发最终走到 B，和从 B 出发最终走到 A 是看作同一种 case 的，因而逐行从上向下遍历时，垂直方向上，我们只用考虑向下的前进方向，不用考虑向上的方向，因为从当前单元格出发向上到达某个单元格的路径，已经被从前面行中的某个单元格出发并向下到达当前单元格的路径涵盖了，此时对于水平方向上，左右都是要考虑的。

所以总结一下，思路就是：

> 1. 从上到下逐行遍历每个单元格，并以其为起始点，向左、下、右三个方向之一出发，中途最多可以拐弯儿一次，每到达一个单元格，计算一下 `trailing zero` ，跟历史比较，取最大值
> 2. 当每一个单元格都被遍历后，最终达到的最大值即是所需。