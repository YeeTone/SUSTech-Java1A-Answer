# CS102A/CS107 Week 6
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## 1. 理论课内容框架
- 模块化编程
- 声明定义方法，并使用 **重要考点**
- 方法重载 **重要考点**

### 1.1 模块化编程

编程思想：Divide-and-Conquer（分而治之） -> for more details, please refer to CS203: 数据结构与算法分析、CS208: 算法设计与分析      

学习方法之前，需要先从三个维度思考方法的作用：      
1. 是什么？
2. 为什么？
3. 怎么办？

这是个哲学问题，充分体现了计算机科学与哲学的密切关联（怪不得有计算机科学哲学博士这个学位2333）。

以下将正经回答问题：      
#### 1.1.1 方法是什么？
方法，是一些语句构成的集合，可以一起执行来实现某些功能。

- 方法是解决问题的步骤的有序组合（这体现了减少代码重复的程序设计特点）
- 方法属于类或对象（体现了Java面向对象的特点，与C/C++函数体可以独立在外不太一样）
- 方法在程序中被创建，在程序中被引用（从程序中来，到程序中去）

#### 1.1.2 为什么要有方法？

- 使程序变得更优雅
- 有利于程序维护
- 可以提高程序开发的效率
- 提高了代码的重用性

#### 1.1.3 方法怎么使用？

在下一个部分详细介绍！

### 1.2 声明使用方法 重要考点

方法命名：     
1. 遵守Java语言的变量命名原则；
2. 推荐使用驼峰原则；

方法定义：
```java
[modifiers] [return type] [name] ([para type] [para name], ...){

}
```
```modifiers```: 可选选项，告诉编译器应该对这个方法有哪些修饰与控制，告诉编译器应该怎么做，分两种：访问修饰符和非访问修饰符（以后再介绍）         

```return type```: 必选选项，必须是数据类型（基本引用均可，如int, double, String等等）。如果不需要返回值，那也需要填写```void```来告诉编译器无需返回。（其实也是在告诉编译器怎么做事，就像打篮球的时候队友接不住球就很麻烦，写这个就是告诉编译器如何完成“接球”的工作）；      

```name```：方法名，必选选项，方法名遵守Java语言的变量命名原则；       

```para type```&&```para name```：可选选项，参数类型和参数名（其实就很像打篮球的时候自己有一个接球的姿势，这个过程就是在告诉外部调用的时候的“接球”姿势）      

在接触类与对象之前，我们都先暂时书写静态方法（```static```关键字的作用先不用管，先记着这么写就行），这与类对象无关，可以降低理解难度，例如：
```java
public static int sum(int a, int b){
    return a + b;
}
```

示例：
```java
public static void main(String[] args){

}
```
关键字：```return```返回结果，结束方法体的运行

底层内容讲解：方法调用栈 -> ```StackOverFlow``` (CS203 CS202 CS302)        

传值方法：         
1. 值传递 -> 传递参数的副本，本质上是一个copy的结果；        
2. 引用传递 -> 传递参数的引用，本质上是一片内存区域；         

**Java: Pass-by-Value** **重要考点**

对于基本数据类型，传递具体的值；       
对于引用数据类型，传递内存的引用（指针）；       

示例1：   
```java
public static void triple(int x){
    x *= 3;
}

public static void main(String[] args){
    int a = 3;
    triple(a);
    System.out.println(a);
}
```
运行后的结果a还是3

示例2：   
```java
public static void triple(int[] x){
    for(int i = 0;i < x.length; i++){
        x[i] *= 3;
    }
}

public static void main(String[] args){
    int[] a = {1,2,3};
    triple(a);
    System.out.println(Arrays.toString(a));
}
```
运行后的结果a数组变成了{3,6,9}

类型变更与方法签名 **考点**       

示例：
```java
int x = 3;
double y = Math.sqrt(y);
```
但是实际上```Math.sqrt```的方法签名中并无int类型，只有double类型：       
```java
public static double sqrt(double a) {
    return StrictMath.sqrt(a); // default impl. delegates to StrictMath
                               // Note that hardware sqrt instructions
                               // frequently can be directly used by JITs
                               // and should be much faster than doing
                               // Math.sqrt in software.
}
```
这个过程中，发生了两件事情：       
1. ```int``` 类型的 ```x``` 先自动升级成 ```double``` 类型；       
2. 转换成```double```类型的```x```再作为参数传入```Math.sqrt```方法中；

RECALL：       
基本数据类型的大小范围：
```java
byte < short < int < long < float < double
从左到右可以自动转换

char只能往上转换，不能由byte/short自动升级而来

boolean不能和任何其他基本数据类型转换
```

自动类型提升：小 -> 大

### 1.3 方法重载

1. 两个方法的名字和参数不能完全相同        
2. 可变长参数列表（自动转数组，但必须是最后一个参数）      

示例：
```java
public void printType(String s){
    System.out.println("This is a String!");
}

public void printType(int s){
    System.out.println("This is an int");
}
```
两个方法同名，只有形参不同，就构成了重载，编译器会自动选择重载的方法调用！

**重要考点，且考试会与后续的多态结合，考的话极易失分，建议写入期末考试的Cheating Paper中！（以下部分可以先不看，因为需要后续多态的知识）**

```java
class A {
    public String show(D obj){
        return ("A and D");
    }
    public String show(A obj){
        return ("A and A");
    }
}
class B extends A{
    public String show(A obj){
        return ("B and A");
    }
    public String show(B obj){
        return ("B and B");
    }
}
class C extends B{
    public String show(A obj){
        return ("C and A");
    }
    public String show(B obj){
        return ("C and B");
    }
    
}
class D extends B{
    public String show(A obj){
        return ("D and A");
    }
    public String show(B obj){
        return ("D and B");
    }
}

class MultiTest {
    public static void main(String[] args) {
        A ab = new B();
        B b = new B();
        C c = new C();
        System.out.println(ab.show(b));
        System.out.println(ab.show(c));
    }

}
```

运行结果是
```
B and A
B and A
```
