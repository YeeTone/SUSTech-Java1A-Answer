# CS102A/CS107 Week 13
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## 1. 理论课内容框架
- 泛型简要介绍
- 泛型类
- 泛型接口
- 泛型方法
- 泛型擦除

### 1.1 泛型简要介绍
学习泛型，三个维度：       
1. 是什么？
2. 为什么？
3. 怎么办？

#### 1.1.1 什么是泛型？

泛型（generics）是 JDK 5 中引入的一个新特性, 泛型提供了编译时类型安全检测机制，该机制允许程序员在编译时检测到非法的类型。          
泛型的本质是参数化类型，也就是说所操作的数据类型都将提前被指定！         

#### 1.1.2 为什么要有泛型？
Java语言引入泛型的好处是安全简单。泛型的好处是在编译的时候检查类型安全，并且所有的强制转换都是自动和隐式的，提高代码的重用率。

详细的说：

类型安全。 泛型的主要目标是提高 Java 程序的类型安全。通过知道使用泛型定义的变量的类型限制，编译器可以在一个高得多的程度上验证类型假设。没有泛型，这些假设就只存在于程序员的头脑中（或者如果幸运的话，还存在于代码注释中）。

消除强制类型转换。泛型的一个附带好处是，消除源代码中的许多强制类型转换。这使得代码更加可读，并且减少了出错机会。

潜在的性能收益。 泛型为较大的优化带来可能。在泛型的初始实现中，编译器将强制类型转换（没有泛型的话，程序员会指定这些强制类型转换）插入生成的字节码中。但是更多类型信息可用于编译器这一事实，为未来版本的 JVM 的优化带来可能。由于泛型的实现方式，支持泛型（几乎）不需要 JVM 或类文件更改。所有工作都在编译器中完成，编译器生成类似于没有泛型（和强制类型转换）时所写的代码，只是更能确保类型安全而已。

(以上内容来自于：https://www.cnblogs.com/zhengbin/p/5622621.html )

简而言之就是：减少代码量；代码更优美；不必写重复代码；增强安全性

#### 1.1.3 怎么写泛型？

下文中将会详细介绍！

### 1.2 泛型类

泛型广泛应用于类的定义中，特别是各种容器，如先前提过的```java.util.ArrayList```。
```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable{
    //...
    public boolean add(E e) {
        //...
    }
    public E get(int index) {
        //...
    }
}
```

这个示例中的E就是泛型，其中类的返回值和参数值都可以使用这个E，表示一种规定的数据类型。

这个E可以指代所有的引用数据类型。

RECALL: 什么是引用数据类型？

### 1.3 泛型接口
泛型接口与泛型类的定义及使用基本相同。泛型接口常被用在各种类的生产器中，如Java对集合框架的for-each循环的底层实现结构迭代器```java.util.Iterator```：
```java
public interface Iterator<E> {
        //...
        E next();
        //...
}
```
这部分属于扩展内容！

### 1.4 泛型方法

泛型方法：是在调用方法的时候指明泛型的具体类型。

泛型方法的规则：      
- 所有泛型方法声明都有一个类型参数声明部分（由尖括号分隔），该类型参数声明部分在方法返回类型之前（在下面例子中的<E>）。
- 每一个类型参数声明部分包含一个或多个类型参数，参数间用逗号隔开。一个泛型参数，也被称为一个类型变量，是用于指定一个泛型类型名称的标识符。
- 类型参数能被用来声明返回值类型，并且能作为泛型方法得到的实际参数类型的占位符。
- 泛型方法体的声明和其他方法一样。注意类型参数只能代表引用型类型，不能是原始类型（像int,double,char的等）。
        
比如说下面的代码就是泛型方法的典范，泛型也可以受到限制！
```java
public static <T extends Comparable<T>> T max(T t1, T t2) {
        return t1.compareTo(t2) > 0 ? t1 : t2;
}
```

### 1.5 泛型擦除

之前我们讲了```ArrayList```容器中有泛型机制，那么不同类型的ArrayList是否是同一个类呢？以及运行时的ArrayList的数据类型还会是ArrayList<E>吗？

```java
import java.util.ArrayList;
public class Test {
    public static void main(String[] args) {
        ArrayList<Integer>integers = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();

        Class<?> c1 = integers.getClass();
        Class<?> c2 = strings.getClass();

        System.out.println(c1==c2);
    }
}
```
这段代码的结果是：
```java
true
```
说明运行时integers和strings是同一个类！
        
尝试反编译，结果是：
```
D:\IdeaProjects\CS102A2021Fall\src>javac Test.java

D:\IdeaProjects\CS102A2021Fall\src>javap -c Test.class
Compiled from "Test.java"
public class Test {
  public Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class java/util/ArrayList
       3: dup
       4: invokespecial #3                  // Method java/util/ArrayList."<init>":()V
       7: astore_1
       8: new           #2                  // class java/util/ArrayList
      11: dup
      12: invokespecial #3                  // Method java/util/ArrayList."<init>":()V
      15: astore_2
      16: aload_1
      17: invokevirtual #4                  // Method java/lang/Object.getClass:()Ljava/lang/Class;
      20: astore_3
      21: aload_2
      22: invokevirtual #4                  // Method java/lang/Object.getClass:()Ljava/lang/Class;
      25: astore        4
      27: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
      30: aload_3
      31: aload         4
      33: if_acmpne     40
      36: iconst_1
      37: goto          41
      40: iconst_0
      41: invokevirtual #6                  // Method java/io/PrintStream.println:(Z)V
      44: return
}
```

细看，我们就可以发现String和Integer的泛型信息被擦除了，变成了Object！      
        
也就是说，泛型只是用来给人和编译器看的，运行时已经消失了！
