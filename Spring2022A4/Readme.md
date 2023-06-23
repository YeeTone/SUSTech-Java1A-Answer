# Explanation to A4[100 + 20 Points]

- 帮助文档：https://docs.qq.com/doc/DTHpIRUJYV2t6ZnRz?u=c34e7d60b7bf4c3384a73627fee73747
- 标答中的package仅仅是为了区分包，请各位同学自行使用时去除package信息！

## A4Q1 BigBinary[40 + 15 Points]

（出这道题发挥了我**九成功力**，是Java1作业题目中深度和广度兼具的集成者）

- 难度：中等
- 要求：手动实现一个二进制大整数类，要求支持加法，减法和乘法（bonus），其中禁用所有java相关的高精度运算类库。
- 注意：
    - 考虑到数据规模比较大（15000长度，50-150组），各位同学一定要避免频繁使用+连接字符串！
    - 对于基本的加法和减法，使用手动模拟进位和借位的方法即可通过！
    - 对于bonus乘法，使用朴素的二进制实现将不能通过！原因如下：

```
朴素实现需要的操作数是n^2，因此极限数据情况下，单组数据的操作数是15000^2=2.25*10^8
本题中有多组测试数据，个数在50-150之间。如果考虑50组数据，那么就需要1.125*10^10个操作数
如果OJ的测评效率可以到达10^9/s，那么所需要的时间就是11.25s，远远超过了3s的时间限制！
```

乘法的bonus考察的是高精度乘法的优化策略，主要有以下几个方向可以做：
- 方法一：乘法高精度压位。用一位int表示多位bits（比如说8个bits），相当于升级进制（比如说从二进制升级为256进制）。此处给大家提供一个十进制下的高精度压位乘法的实现思路：https://www.cnblogs.com/zengpeichen/p/10943083.html
- 方法二：分治。将原先的乘法问题分解为多个子问题，最终再进行合并。此处给大家提供一个十进制下的高精度分治乘法的实现思路的讲解视频：https://www.bilibili.com/video/BV1m7411w75J?from=search&seid=14971151476235823330&spm_id_from=333.337.0.0
- 方法三：FFT（ Fast-Fourier Transform）。使用算法中的快速傅里叶变换进行乘法加速。不过考虑原理比较复杂，各位同学可以不使用这个方法。

## A4Q2 Online Shopping[60 + 5 Points]
- 难度：中等
- 要求：实现一些类，实现商品购买卖出等等流程的操作。
- 注意：

Product：
- cnt与id的动态更新与赋值
- setRating的范围检测
- getAvgRating的平均值计算要使用float
- toString方法中保留两位小数

Store：
- cnt与id的动态更新与赋值
- addProduct的存在性检查
- removeProduct的不存在性检查
- transact中，productList和income的动态更新

Customer：
- cnt与id的动态更新与赋值
- rateProduct的合法性检查
- purchaseProduct中对于Store和Customer的合法性检查
- viewShoppingCart排序中，按照购买时间的排序就是不排！（别问我怎么知道的）
- refundProduct可能要用hashmap，或者自己设计一个field来做