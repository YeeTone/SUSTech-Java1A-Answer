# Explanation to A5: 基于图数据结构的类与对象作业
以下内容将基于本人的标准实现进行提示的书写。

## Contribution
- Designer: 
  - ZHU Yueming(朱悦铭)
- JUnit: 
  - Local Judge: WANG Lishuang(王力爽)
  - Official Judge: WANG Yitong(王奕童)
- Document: 
  - ZHANG Wencheng(张闻城)
  - LI Kai(李开)
- Tester: 
  - ZHENG Bowen(郑博文)

## Overview Information
| 题目 | 题面           | 分数 | 难度 | 随机化 |
| ---- | ---------------- | ---- | ------ | ------ |
| Q1   | 时间           | 10   | Noob   | 是    |
| Q2   | 航班           | 20   | Easy   | 是    |
| Q3   | 航空公司     | 30   | Medium | 否    |
| Q4   | 直接邻居搜索 | 15   | Easy   | 是    |
| Q5   | 单次中转搜索 | 25   | Hard   | 是    |
| Q6   | 所有合法路径搜索 | 30   | Demon  | 是    |
## Q1: SustechTime（时间）
难度：Noob       

分数：10       

测评点信息：

- 测评点1-3：测试构造方法及内部属性的修改情况
- 测评点4-9：测试timeDifference方法
- 测评点7-9：测试toString方法

9个测试点的测试数据全随机生成，基本上一个方法写错的话就三个点全错

### 做法提示
这都这么明显了，还需要提示吗……        
- 构造方法：```split```以后再```Integer.parseInt```
- toString方法：调用```String.format```
- timeDifference：注意```Math.abs```

## Q2: Flight（航班）
难度：Easy

分数：20

测评点信息：

- 测评点1-3：测试构造方法及内部属性的修改情况
- 测评点4-6：测试toString方法

6个测试点的测试数据全随机生成，基本上一个方法写错的话就三个点全错

### 做法提示
本人的标准实现中，增加了Airport类表示图数据结构的顶点，将字符串对象向上做了一层抽象。         
由于有可能有多次对同一个飞机场添加飞往其他位置的航班，要处理数据之间的共享问题，因此在此处使用了设计模式中的享元模式。          

Airport设计细节介绍：          
- 内部封装```static```修饰的Map结构，并将构造器使用```private```修饰，是享元模式的标准实现方法。       
- 内部至少有3个成员变量：一个```String```类型的name，表示名字；两个```List<Flight>```类型的列表，表示出入边。
- 获取Airport对象的方法也是参考享元模式的标准实现，通过静态方法获取对象。
- 为了触发HashMap当中的哈希冲突，有必要需要重写hashcode和equals方法。
- 当一个合法Flight对象成功创建的时候，需要在两个Airport之间都修改出入边的相关信息。
![image](https://user-images.githubusercontent.com/64548919/145711627-4911d103-445f-406e-b6e9-c0a9eb2d06a7.png)


因此我的设计中，Flight就需要6个成员：           
![image](https://user-images.githubusercontent.com/64548919/145711362-eca26e99-6cf4-4fec-8f2d-d089b89d8388.png) 

其他的没有什么难度：

- 构造方法：```split```了以后再分别赋值
- toString方法：调用```String.format```

## Q3: Airline和SustechAirline（航空公司）
难度：Medium

分数：30

测评点信息：          
10个测试点，都是出题SA亲自手工设计，涉及的坑比较多         

### 做法提示
注意以下几个坑，其他的具体实现并无显著难度：
- 每次重新构造SustechAirline对象的时候，要将SustechAirline对象和Airport对象中所有内容重置初始化。
![image](https://user-images.githubusercontent.com/64548919/145711575-94b84dc4-f19e-43d1-840c-3f8d31673efd.png)

- addFlight的时候，不能添加同号航班
- getFlightInfo的时候，找不到时返回null
- getFlightRoute的时候，输出时要做排序
- getFlightRoute的时候，找不到则不打印对应的Airport
- removeFlight的时候，找不到要返回false，找到了就要移除然后返回true
- getAllFlightsAboutDepartPort，返回结果要做排序
- getAllFlightsAboutDepartPort找不到不能返回null
- isRoundTrip有好几种可能：无边，单向边，双向边
- searchAllRoutes和searchBestRoute，直接调用search中对应的方法

## Q4: DirectSearch（直接邻居搜索）
难度：Easy

分数：15

测评点信息：           

12个测评点，全为现场生成的随机化测试用例，要求测评者的程序与测评程序内嵌的标程做多次对拍。其中会一次性加入所有合法航班，然后开始做密度比较大的两两检查。

| 测评点     | 顶点数 | 边数 | 是否随机化 | 时限 | 检查策略 |
| ------------- | ------ | ---- | ---------- | ------ | -------- |
| combination01 | 15     | 188  | 是        | 3000ms | 两两检查 |
| combination02 | 16     | 201  | 是        | 3000ms | 两两检查 |
| combination03 | 17     | 221  | 是        | 3000ms | 两两检查 |
| combination04 | 18     | 244  | 是        | 3000ms | 两两检查 |
| combination05 | 19     | 275  | 是        | 3000ms | 两两检查 |
| combination06 | 20     | 288  | 是        | 3000ms | 两两检查 |
| combination07 | 21     | 307  | 是        | 3000ms | 两两检查 |
| combination08 | 22     | 342  | 是        | 3000ms | 两两检查 |
| combination09 | 23     | 386  | 是        | 3000ms | 两两检查 |
| combination10 | 24     | 432  | 是        | 3000ms | 两两检查 |
| combination11 | 25     | 480  | 是        | 3000ms | 两两检查 |
| combination12 | 26     | 530  | 是        | 3000ms | 两两检查 |

### 做法提示
这个题目难度不算很大，只需要通过Airport静态方法获取Airport实例，然后再遍历Airport周围的邻居即可。

没有挖什么坑，很简单。

## Q5: TransitOnceSearch（单次中转搜索）
难度：Hard

分数：25
 
测评点信息：           
同DirectSearch，不过内嵌标程的算法有变化。

### 做法提示
这个题稍微有一些复杂，主要算法流程如下：
```
TransitOnceSearch(departPort, arrivePort):
    result <- Empty List
    d <- departPort对应的Airport实例
    for 枚举每一个d的离开航班df:
        neigh <- df的到达顶点
        for 枚举每一个neigh的离开航班df2:
            neigh_neigh <- df2的到达顶点
            if neigh_neigh = arrivePort and df和df2能完成中转:
                result.add(Route(df, df2))
    return result
```
对于searchBest来说，可以先通过上述算法选出所有答案，然后再依次循环选出其中最优的那一个。

## Q6: UnlimitedSearch（所有合法路径搜索，Bonus）
**重要：无算法基础的同学强烈不建议花时间继续研究！建议花更多时间在黑白棋的project制作上！**

难度：Demon

分数：30

测评点信息：

同DirectSearch，不过内嵌标程的算法有变化。

### 问题描述
- searchRoutes：搜索限制条件下的所有合法可达路径
- searchBestRoutes：搜索限制条件下的最佳路径

### 做法提示

注意到这题的测试时限仍为3000ms，仍然需要进行两两检查，对时间的要求比较高。因此暴力做法肯定行不通。

有几个算法可以解决此类问题：
- DFS（深度优先搜索）
- Dijkstra（迪杰克斯拉算法）
- SPFA（最短路快速算法）

内嵌的标准实现做法是DFS以寻求所有可达路径，这里提供一个基于Airport的伪代码实现：
```
dfs(departPort, arrivePort):
    if arrivePort is found:
        dfs顶点栈中压入this
        用dfs路径栈开始反向建立航路路径Route，并保存结果
        dfs栈弹出
        return
    dfs顶点栈中压入this
    for 枚举每一个this的出发航路df:
        arrive <- df的到达Airport
        if df与栈顶的中转合法性检查失败了:
            continue
        dfs路径栈压入df
        arrive.dfs(depart, arrivePort)
        dfs路径栈弹出
    dfs顶点栈弹出
```

对于searchBest来说，可以先通过上述算法选出所有答案，然后再依次循环选出其中最优的那一个。
