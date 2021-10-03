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


