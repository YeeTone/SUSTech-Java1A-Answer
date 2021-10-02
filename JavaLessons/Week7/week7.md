# CS102A/CS107 Week 7
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## 1. 理论课内容框架
- 面向对象编程
- 声明类与创建对象
- 实例方法
- 实例变量
- 类对象构造方法

### 1.1 面向对象编程

学习面向对象之前，需要从三个维度思考面向对象的相关问题：     

1. 是什么？
2. 为什么？
3. 怎么办？

#### 1.1.1 什么是面向对象？

真实世界中，对象无处不在，如黑板，粉笔，纸张等等。这些对象都有自己的行为和状态。           

为了便于理解并与现实世界做抽象，软件工程中也常用软件对象的方法来建模，也有状态和行为。

状态 -> 对象内部的属性       
行为 -> 对象方法的调用        

对象：具有状态和行为的类的一个实例，可以类比于现实世界中的一个物品；            
类：对象的模板，用于描述类对象的行为和状态，可以类比于现实世界中对类对象的共性描述；        

面向对象编程有3个关键组成部分：class(类) object(对象) method(方法)     

类的组成部分：属性+方法 -> 集成数据，便于操作

#### 1.1.2 为什么要面向对象编程？

之前的面向过程编程中，如果某一个地方出现了bug，或者需求变更导致代码逻辑需要改动，就会牵一发而动全身，修改极其困难！

面向对象编程就充分利用了Divide-and-Conquer的设计思想，允许将问题中的数据抽象成对象从而方便进行操作，虽然使得编程的时候复杂度有所增加，但这使得程序的可扩展性大大增强，利大于弊！

#### 1.1.3 怎么面向对象编程？

将会在下个章节详细介绍！

### 1.2 声明类与创建对象

示例1：
```java
public class GradeBook{
    public void display(){
        System.out.println("This is a gradeBook!")
    }
}

class Main{
    public static void main(String[] args){
        GradeBook gb = new GradeBook();
        gb.display();
    }
}
```

关键信息解读：      

1. 如果无构造方法，那么Java编译器会自动生成一个无需参数，什么都不做的构造方法：
```java
public GradeBook(){

}
```
2. 对象的创建：
```java
GradeBook gb = new GradeBook();
```
```new```关键字做了什么事情？      

i. 实例化：向JVM申请内存空间，并且返回指向该片内存空间的引用（指针）；          
ii. 初始化：调用构造方法；           
iii. 内存布局：将第1步返回的引用（指针）传给实例变量gb，注意gb本身也是一个引用（指针）


3. 对象本质是指针，在第2部分中已讲述

### 1.3 实例方法

先前我们介绍了静态方法及其调用：
```java
class Main{
    public static void main(String[] args){
        System.out.println(sum(1,2));
    }

    public static int sum(int a, int b){
        return a + b;
    }
}

```

可以看出。这类方法主打还是面向过程编程的。

Java语言的设计者考虑到了这一问题，就将面向对象的方法调用也支持了，功能类似于现实世界中的个体行为。     

比如上述代码就可以修改为面向对象的风格设计：      

```java
class Main{
    public static void main(String[] args){
        Adder adder = new Adder();
        System.out.println(adder.sum(1,2));
    }
}

class Adder{
    public void sum(int a, int b){
        return a + b;
    }
}
```
