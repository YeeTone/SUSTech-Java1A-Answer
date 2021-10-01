# CS102A/CS107 Week 4 
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## 1. 理论课内容框架
- for/while/do-while循环 **考点**
- switch语句 **考点**
- continue和break语句 **考点**
- 逻辑运算符
- 结构化编程

### 1.1 for/while/do-while循环！！！考点！！！
for 循环：
```java
for (int i = 0; i <= 10; i++){
    System.out.println(i);
}
```

while 循环：
```java
int i = 0;
while (i <= 10){
    System.out.println(i);
    i++;
}
```

do-while 循环：
```java
int i = 0;
do {
    System.out.println(i);
    i++;
}while (i <= 10);
```

以上三段代码等价，都是输出1-10，每行一个数。      

for循环结构：     
```java
for(A; B; C)
```
A：循环体内临时变量定义      
B：循环体内的循环条件，需要是```boolean```类型       
C: 每次循环后进行的操作       

拓展：     
- A B C均可为空
- for/while 循环如果没有花括号划定范围，那么只能作用于1条语句

### 考点
- 循环次数计算与结果计算
- 手写代码

### 1.2 switch语句！！！考点！！！

特点：     
1. 底层实现是随机访问，效率高于多层if-else嵌套；      
2. 一旦一条分支符合，那么就会不断执行后续语句直到遇到break **（考点）**；

My own habit:      
增加```{}```划定范围，避免代码层次混乱。        

### 1.3 continue 和 break 考点

```continue```: 后续语句都不执行了，直接进行下一次循环迭代；      
```break```: 后续语句都不执行了，循环体结束

拓展：continue break可以选择跳出的循环体是哪一个，通过标签实现。       

### 1.4 逻辑运算符

一共四种，分两类：

- [X] && ||: 条件与或，如果某个结果出来可以确定最终表达式了，那么后续的步骤都不再会向下运行（布尔短路）      
- [X] & |: 按位与或，全部都会运行一次，效率不如条件与或，但是会避免布尔短路的情况出现。      

### 1.5 结构化编程

结构化编程有利于代码维护和代码重用，是面向对象分析与设计和设计模式的基石。       

非考点

## 2. Assignment讲解(if any)
