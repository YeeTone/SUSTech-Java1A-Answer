# CS102A/CS107 Week 5
(Based on the slides from Prof.Yepang LIU(刘烨庞))         

## 1. 理论课内容框架
- 数组存储与读取
- 数组声明与初始化，元素访问 **考点**
- foreach循环迭代数组 **考点**
- 声明使用多维数组 **考点(不常考)**
- Optional: try-catch-finally与异常处理(Week 15的内容，考试也不考，但这个是编程中常用的利器！)

关键：一定要对JVM的数组的内存管理机制有深刻的理解，才能将这个方面的理论掌握透彻！

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
boolean: false -> 源于：C语言中0表示false
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

图解图示：
![image](https://user-images.githubusercontent.com/64548919/135631418-3e3a5926-7276-4751-b0a5-92678ca06493.png)

### 1.4 多维数组
二维数组：数组的数组（套娃！有木有！）      

先前提到数组本质是指针，因此二维数组的内存存储格式并不是类似于国际象棋棋盘那样的方格格，而是有秩序的元素和元素指针的排列组合！

内存管理机制：
![image](https://user-images.githubusercontent.com/64548919/135633446-7e5284f0-02c3-4f5e-8f03-a0703d9d2406.png)


考试中可能会涉及手写代码的题目，但难度是极其简单的那种！

二维数组使用示例
```java
int[][] a2 = new int[3][2];
```

上面这种写法是最为常见的写法；      

二维数组的第一个维度的大小必须确定，其他的后续可以以后申请确定，不过对应的数组就是null。(后续再申请确定极其麻烦，不建议使用这种方法)           
```java
int[][] a2 = new int[3][];
a2[0] = new int[1];
a2[1] = new int[2];
a2[2] = new int[3];
```

类似一维数组，二维数组同样也支持通过显式定义元素的方法来自动申请确定空间，如下所示（也不太常用2333）：
```java
int[][] a2d = {{1,2,3,4},{5,6},{7,8,9},{10}};
```

**特别注意：二维数组的for-each循环对内存元素值的修改机制！（考试不常考但极易失分，容易混淆！）**

示例1：
```java
int[][] a2d = new int[3][3];
int cnt = 0;
for(int[] a1: a2d){
     a1[cnt] = 1;
     cnt++;
}
//运行后：a2d= {{1,0,0},{0,1,0},{0,0,1}}
```
示例2：
```java
int[][] a2d = new int[3][3];
for(int[] a1: a2d){
     for (int i: a1){
            i = 1;
     }
}
//运行后：a2d = {{0,0,0},{0,0,0},{0,0,0}}
```
讲解时注意对内存本质底层的运行过程做详细说明！

### 1.5 try-catch-finally异常处理(Optional)

这个是Java1A课程中最后一周的内容，考试也不考，但是我认为很有必要提前就给同学们讲讲，让同学们拥有一个面对程序报错时的大杀器！

异常的定义：
```
异常是程序中的一些错误，但并不是所有的错误都是异常，并且错误有时候是可以避免的。
```

运行时产生的错误统称为```Throwable```，其下分两类：       、

```Error```表示不是非法操作，而是程序本身出现了错误，是属于程序没法处理的，比如```StackOverFlowError```；        

```Exception```表示用户或者程序进行了某些非法操作，使得程序出现问题，可以得到处理，比如说```NullPointerException```；

```Exception```又下分两种，分别是运行时异常和非运行时异常：

- 运行时异常编译时没法检测，是运行的时候扔出来的，比如说```ArrayIndexOutOfBoundException```；
- 非运行时异常编译时可以检测，要求程序必须完成对异常的显式处理，比如说```IOException```；

![image](https://user-images.githubusercontent.com/64548919/136687085-45540a57-b80d-4a99-a580-dbe1c69302fa.png)


Java1A课程中常见的异常有以下几种，表示程序进行了不合法的操作：
- ArithmeticException
- IllegalArgumentException
- NumberFormatException
- InputMismatchException
- StringIndexOutOfBoundException
- ArrayIndexOutOfBoundException
- ClassCastException
- NullPointerException
- IndexOutOfBoundException
- FileNotFoundException
- ...

异常的机制的存在，让程序能够在出现非法错误的时候及时退出而不进行非法操作，也可以让程序员定位程序的位置；      

而try-catch-finally机制的存在，则让程序员面对异常处变不惊，能够在出现异常的时候及时挽救而不立即退出。

异常和try-catch-finally机制是程序员在面对非法操作或者请求时的必胜法宝，而不会面对满屏的报红不知所措，因此我认为很有必要提前给同学们讲解这方面的内容！

如何抛出一个异常？
```java
throw new Exception();
```

如何捕获一个异常并进行一些操作？
```java
try{
     int[] a3 = new int[3];
     System.out.println(a3[3]);
catch(ArrayIndexOutOfException e){
     System.out.println("ArrayIndex is invalid!");
}
```

如何检测多种异常？
```java
try{
   // 程序代码
}catch(异常类型1 异常的变量名1){
  // 程序代码
}catch(异常类型2 异常的变量名2){
  // 程序代码
}catch(异常类型3 异常的变量名3){
  // 程序代码
}
```

finally关键字的作用？

无论try块中有无异常，都会执行finally语句块，主要用于资源的释放与关闭：

```java
Scanner sc = new Scanner(System.in);
//如果输入a，那么程序将结束，然后资源会通过finally语句块释放
try{
   int x = sc.nextInt();
}catch (InputMismatchException e){
   System.out.println("Input is not valid!");
}
finally {
   sc.close();
}
```
