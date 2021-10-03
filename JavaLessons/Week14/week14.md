# CS102A/CS107 Week 14
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## 1. 理论课内容框架
- 泛型方法原理分析
- 泛型类原理分析
- 常用泛型集合框架的使用 **重要**

### 1.1&&1.2 泛型方法原理分析 && 泛型类原理分析

上周说过，泛型主要有好几个步骤：
- 泛型编译检查
- 泛型擦除
- 擦除后强制类型转换

比如说泛型方法：
```java
public static <T extends Comparable<T>> T max(T t1, T t2) {
    return t1.compareTo(t2) > 0 ? t1 : t2;
}

public static void main(String[] args) {
    String max = max("a","b");
    System.out.println(max);
}
```

对于泛型方法来说，也同样符合这几个流程，看如下的反编译结果：
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

  public static <T extends java.lang.Comparable<T>> T max(T, T);
    Code:
       0: aload_0
       1: aload_1
       2: invokeinterface #2,  2            // InterfaceMethod java/lang/Comparable.compareTo:(Ljava/lang/Object;)I
       7: ifle          14
      10: aload_0
      11: goto          15
      14: aload_1
      15: areturn

  public static void main(java.lang.String[]);
    Code:
       0: ldc           #3                  // String a
       2: ldc           #4                  // String b
       4: invokestatic  #5                  // Method max:(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;
       7: checkcast     #6                  // class java/lang/String
      10: astore_1
      11: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
      14: aload_1
      15: invokevirtual #8                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      18: return
}
```

在main方法的中，我们可以看到max方法已经被类型擦除掉了，变成了Comparable。

同样，在泛型类中也是一样的，比如说下面所示的泛型类：
```java
public class Test <T> {
    private final T element;

    public Test(T t){
        this.element = t;
    }

    public T getElement() {
        return element;
    }
}

class Main{
    public static void main(String[] args) {
        Test<String> test = new Test<>("string");
        System.out.println(test.getElement());
    }
}
```

遇事不决就反编译！结果如下所示：
```
D:\IdeaProjects\CS102A2021Fall\src>javac Test.java

D:\IdeaProjects\CS102A2021Fall\src>javap -c *.class
Compiled from "Test.java"
class Main {
  Main();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class Test
       3: dup
       4: ldc           #3                  // String string
       6: invokespecial #4                  // Method Test."<init>":(Ljava/lang/Object;)V
       9: astore_1
      10: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
      13: aload_1
      14: invokevirtual #6                  // Method Test.getElement:()Ljava/lang/Object;
      17: checkcast     #7                  // class java/lang/String
      20: invokevirtual #8                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      23: return
}
Compiled from "Test.java"
public class Test<T> {
  public Test(T);
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: aload_1
       6: putfield      #2                  // Field element:Ljava/lang/Object;
       9: return

  public T getElement();
    Code:
       0: aload_0
       1: getfield      #2                  // Field element:Ljava/lang/Object;
       4: areturn
}
```
在Main类的main方法中，我们可以看到同样有强制类型转换和擦除成Object类型的泛型。

和大家讲了这么多反编译的东西，就是希望大家记住泛型本身并不神秘，是编译器用来骗我们的小伎俩而已，知道了编译器做了什么就没什么困难的！

再强调一次，泛型主要有好几个步骤：
- 泛型编译检查！
- 泛型擦除！
- 擦除后强制类型转换！

### 1.3 常用泛型集合框架的使用 重要

java的集合框架极其强大，大家很有必要了解其中的架构！

主要集中在```java.util```包下，类的个数很多，但是要学起来是非常简单的！

主要分四类，其中泛型的数据结构大部分属于前三种当中的一种！
- [X] List：列表，主要提供有序且允许重复的存储；
- [X] Set：集合，主要提供无序且不允许重复的存储；
- [X] Map：映射，主要提供无序的键值对的存储，其中键不允许重复，值允许重复；
- [X] Util：工具，主要提供一些对集合操作的静态方法；

#### 1.3.1 List列表

代表性实现类：
- ArrayList: 基于动态数组实现，容量随着元素个数增多而自动增长；优点是获取元素很方便，效率很高；缺陷是删除元素时需要将后面的元素全部往前移动，可能代价会比较高；
- LinkedList: 基于双向链表实现，通过在头部和尾部增加元素的方法来实现扩充元素；优点是增加，删除元素都很方便；缺陷是获取元素不太容易，需要从头或者尾部开始寻找；
- Vector: 同样基于动态数组，实现原理和ArrayList相似，但是通过加锁以牺牲效率的代价换取了线程安全的保障；
- Stack: 基于Vector实现， 是数据结构栈的标准实现形式，只允许获取最后加入的元素（先入后出，后入先出，FILO，LIFO）

其中的功能虽然很多，但无非再次分类，分成四种：
- 增：add方法（对单个元素），addAll（对元素集合）
- 删：remove（对单个元素或者指定下标），removeAll（对元素集合），retainAll（对元素集合），clear（直接清空）
- 改：set（对指定下标），replaceAll（对元素集合），sort（对自身排序）
- 查：get（对指定下标），size（获取容量），isEmpty（是否为空），contains（对单个元素），containsAll（对元素集合），indexOf（对单个元素），lastIndexOf（对单个元素）

#### 1.3.2 Set集合

代表性实现类：
- HashSet: 基于哈希表实现，内置特殊哈希算法减少碰撞，是数组+链表/红黑树的结合体；优点是增加/删除/定位元素的效率都很高；缺陷是元素无序，迭代访问时顺序不确定；         
- TreeSet: 基于红黑树实现，内置红黑树数据结构以平衡树状结构，减少查询时间；优点是元素自动排序；缺陷是效率不如HashSet，且不允许null键；

同List，也分为四种：        
和List不一样的地方就在于和index相关的方法都失效了，因为集合是无序的：）     
- 增：add方法（对单个元素），addAll（对元素集合）
- 删：remove（对单个元素），removeAll（对元素集合），retainAll（对元素集合），clear（直接清空）
- 改：没有相关的方法
- 查：size（获取容量），isEmpty（是否为空），contains（对单个元素），containsAll（对元素集合）

#### 1.3.3 Map映射

代表性实现类：
- HashMap: 基于哈希表实现，内置特殊哈希算法减少碰撞，是数组+链表/红黑树的结合体（起始是链表，当元素碰撞太多就转变成红黑树）；优点是增加/删除/定位元素的效率都很高；缺陷是元素无序，迭代访问时顺序不确定；         
- TreeMap: 基于红黑树实现，内置红黑树数据结构以平衡树状结构，减少查询时间；优点是元素自动排序；缺陷是效率不如HashSet，且不允许null键；
- HashTable: 实现原理同HashMap，但是通过加锁以牺牲效率的代价换取了线程安全的保障；优点是线程安全，缺点是效率有损失

同List，也分为四种：
- 增：put（对单个键值对），putIfAbsent（对单个键值对），putAll（对键值对集合）
- 删：remove（对键或者键值对），clear（直接清空）
- 改：replace（对键或者键值对），replaceAll（对键值对集合）
- 查：size（获取容量），isEmpty（是否为空），get（对值），containsKey（对键），containsValue（对值），keySet（获取键集合），values（获取值集合），entrySet（获取键值对集合）

这和Set很像，因为Set就是基于Map实现的（HashSet基于HashMap，TreeSet基于TreeMap）

#### 1.3.4 Util工具

Util不是一个类或者接口，只是一个统称：）        
主要有以下几个代表类：
- Collections: 提供很多对集合的操作
- Arrays: 提供很多对数组的操作
