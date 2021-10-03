# CS102A/CS107 Week 14
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## 1. 理论课内容框架
- 泛型方法原理分析
- 泛型类原理分析
- 常用泛型集合框架的使用

### 1.1 泛型方法原理分析

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

### 1.2 泛型类原理分析
