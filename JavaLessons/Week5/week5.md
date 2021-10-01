# CS102A/CS107 Week 3
(Based on the slides from Prof.Fengwei ZHANG(张锋巍))        

## 1. 理论课内容框架
- 数组存储与读取
- 数组声明与初始化，元素访问
- foreach循环迭代数组
- 声明使用多维数组

### 1.1 数组存储与读取
示例：
```java
int[] a = new int[3];
```
![image](https://user-images.githubusercontent.com/64548919/135578388-71bb2948-3df7-4eb2-a349-35ae1990066d.png)

- [ ] 数据结构：本质上是指针（但是被称为引用），指向JVM的申请好的一片连续的内存空间 -> for more details, please refer to CS205:C/C++程序设计
- [ ] 内部存储：同一类型的一组元素
- [ ] 数组本身：数组是对象，不是基本类型。是引用类型
- [ ] 内部元素：可以是基本数据类型，也可以是引用类型

RECALL: 8种基本数据类型有哪些？

数组访问：a[0] a[1] etc -> **重要**
**重要** 数组下标的计算是从0开始的，根本原因是指针和偏移量的关系。
**重要** 有效范围：[0, a.length-1] 如果超过就会导致元素越界
```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException
	at Test.main(Test.java:3)
```
**重要** 数组长度：a.length -> 申请后就自动确定了，不可以再变化！

拓展：可变长度的动态数组 ArrayList/LinkedList

### 1.2 数组声明与初始化
方法有三种
```java
int[] a = new int[12];
```
```java
int[] b = {1,2,3,4,5};
```
```java
int[] b = new int[]{6,7,8,9,10};
```

这三种方法可以归为两类：      

第一种是一类，可以定义长度但是不能定义内容（内容都初始化为默认值，后续还可以修改），是比较常用的写法；       

第二种和第三种是一类，可以定义内容并由JVM自动确认长度和空间申请，不太常用。    

对于第一类的默认值来说，8种基本数据类型有默认值，引用数据类型的默认值则是null，这种设计一般是出于对0初始化的考虑。       

默认值：
```java
boolean: false
```

```java
byte/short/char/int/long: 0
float/double: 0.0f/0.0
```

### 1.3 for-each循环迭代数组
对于一个数组
```java 
int[] arr = {1,2,3,4,5};
```
来说，根据之前循环的学习，我们很容易想到可以使用一个for循环来迭代访问数组。

```java
int sumn = 0;
for(int i = 0; i< arr.length; i++){
    sum += arr[i];
}
```

这种写法本质上没有什么问题，但是显得冗余且不优雅，因此Java的开发者就想了一种方法，来加速迭代访问：

```java
int sum = 0;
for(int i: arr){
    sum += i;
}
```

注意：
对于基本数据类型而言，i此时是原始对象的一个副本，无论怎么修改i，数组原先的值都不受影响！
