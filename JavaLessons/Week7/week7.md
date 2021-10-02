# CS102A/CS107 Week 7
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## 1. 理论课内容框架
- 面向对象编程
- 声明类与创建对象
- 实例方法
- 实例变量
- 类对象构造方法

知识点多，难度较大，建议多花时间理解相关概念，有问题及时与我联系或者课上请教！         

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

你说这么写有什么好处？我只能说，对于这种写法，你要先记住，然后再思考有没有用！          

### 1.4 实例变量
实例变量和静态变量如果不做显式初始化的话，编译器也会使用默认值帮助初始化以供调用（八大基本数据类型默认值，其他都是null）

类实例的变量与静态变量的区别
```java
class ChinesePerson{
    private String name;
    private static String countryName;
}
```
这个代码中，```name```是每个中国人都各自独有的，因此就是实例变量，没有使用```static```修饰；          
而```countryName```是所有中国人都共享的，都一样的，因此就是静态变量，需要```static```修饰。         

数据保护：
```private```关键字：仅限类与对象内部访问，访问权限控制最高        
```setter/getter```方法       

有时候，我们并不希望外部直接访问修改数据，会导致类数据的安全性有累卵之危，倒悬之急！      
如下：
```java
public class Test {
    public static void main(String[] args) {
        ChinesePerson wyt = new ChinesePerson();
        wyt.name = "ytw";
    }
}

class ChinesePerson{
    public String name;
    private static String countryName;
}
```

这样wyt的名字就被随意修改了，影响很不好，因此为了数据安全性，访问权限控制是必须要做的！


## 1.5 类对象构造器
```java
class GradeBook{
    private String name;

    public GradeBook(String name) {
        this.name = name;
    }
}
```



重要信息解读：      
i. 对象实例化要使用的方法，关键字是```new```；        
ii. 如果有构造方法了，那么编译器不会再帮你生成一个无参的空构造方法；             
iii. 传入参数名与自身属性名重名时使用this关键字做指代；          

## 1.6 课件代码解读(Optional)

## 1.7 基本数据类型VS引用数据类型

基本：八大基本数据类型，分四类 -> 变量存储的是值        
引用：类与对象，数组 -> 变量存储对象的内存空间地址，本质上是指针（但被称为引用）          

基本数据类型默认值：不会为```null```，有值          
引用数据类型默认值：```null``` -> 对```null```调用方法或属性会导致```NullPointerException```

一方面揭示了Java引用本身是指针，另一方面说明```null```是一片不允许访问的内存区域，如果强制访问就类似于一脚踏入了虚空之中！
