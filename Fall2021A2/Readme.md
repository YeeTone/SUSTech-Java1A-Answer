# Explanation to A2

## A2Q1 求导法则

难度：简单

要求：给定一个 N 阶多项式，要求求出其给定点 X 处的一阶导数值f'(x)，结果保留两位小数。

做法：按照题目要求执行即可，注意求导的公式计算，以及 X 的阶数计算。

注意：先在头脑中想清楚了再写，可以大大减少bug的出现几率！

## A2Q2 连连看

难度：中等

要求：给定一个二维平面的连连看游戏图大小及其分布，其中方块在横/纵方向只要有连续的3个及以上即可完成消除。询问对于该平面图，是否存在直接消除的可能？有则输出"Yes"，无则输出"No"。

做法： 该题可以简化模型，对于每一个方块，验证其的右侧区域和下方区域的两个方格是否数值与其相同即可，而无需探索到所有可能完成消除操作的方格区域。

注意：向右侧区域或者下方区域探索时，注意先要做边界检查以避免越界！

## A2Q3 双数和

难度：
- 简单(暴力枚举，复杂度O(n^2))
- 困难(二分搜索，复杂度O(n*logn))
- 困难(哈希散列表，复杂度O(n))

要求：给定T个测试用例，每个测试用例给出一个数组，不一定有序，要求询问这其中数对(i,j)的个数，其中需要满足以下两个条件：
- i < j
- a[i] + a[j] = 0

做法：

- 暴力枚举做法：枚举所有(i,j)可能的数对，并逐一进行验证，两重循环即可搞定；
- 二分搜索做法：先将数组完成排序，然后对于相同数字使用散列表去重，最后对于每一个元素都操作后续元素的二分搜索即可；
- 哈希散列表：需要先建立一个容器，存储下标和元素的对应关系，然后逐一计算即可；

注意：对于新手来说，暴力做法即可通过；对于大佬来说，可以尝试二分搜索算法和哈希散列表方法。


## A2Q4 车站

难度：
- 中等(模拟，输入复杂度O(n^2)，查询复杂度O(n))
- 魔鬼(线段树，输入复杂度O(n)，查询复杂度O(logn))

要求：

- 给定车站数目n和公交车容量m，然后依次给出已卖出车票数p和这p张车票的上车站台s和下车站台t。在一定的站台跨度（[s, t-1]）内，这些车票已经确定占用了一部分车上的位置。
- 然后要求处理一个询问，对于起点站为a，终点站为b的乘车需求来说，至多可以支持订购多少张这样的车票？

模拟做法：

- 首先注意，如果一个乘客从s车站上车，t车站下车，那么该乘客在 [s, t-1] 的站台区间内，都会占用乘车位。
- 那么做法是手动模拟每一个乘客的上车下车情况，并且记录公交车在每一个车站离开时的人数。
- 最终的结果将会是 [a, b-1] 区间内的剩余空位的最小值。

线段树做法：

- 该题涉及区间查询，是线段树的模板题。
- 使用线段树的成段更新方法，把每个人的上下车时间都看做一个线段，上车就批量更新区间即可。然后可以降低查询复杂度。

注意：对于新手来说，模拟做法即可通过；对于大佬 **（特级）** 来说，可以尝试线段树算法。

## A2Q5 计划安排表

难度：中等

要求：
- 给定T个测试用例，以下的内容说明都是针对单个测试用例的
- 先给出天数L，每天的时间单位数m，和每天需要的休息时间单位数n
- 在给出这三个数后，会给出3*L个数字，每3个为一组作为一天的安排，其中第一个数字a需要2个单位的时间，第二个数字b需要3个单位的时间，第三个数字c需要5个单位的时间。（也就是说，剩余的时间单位数是 m - n - 2×a - 3×b - 5×c）
- 对每一天，需要计算出其中剩余的时间单位数，并计算所有天数的平均值
- 结果要求先输出每一天剩余的时间单位数，然后在另一行输出这些天数中，大于平均值的天数

做法：手动模拟每一天的计算模式，注意求平均值要用double；
该题的难度主要在于读题，本身并无特别难的地方。