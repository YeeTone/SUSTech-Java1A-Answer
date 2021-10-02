# CS102A/CS107 Week 10
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## 1. 理论课内容框架
- 静态类成员变量与方法 **考点**
- final关键字与垃圾回收 **考点**
- Java语言的package机制
- 枚举类型 **重要**
- 堆栈内存管理机制 **考点**
- 继承 **考点**

### 1.1 静态类成员变量与方法

```static```用于修饰静态类成员变量和方法，是一个新的语法规则。        
对于新的语法规则，我们还是要从三个维度思考问题：          
1. 是什么？
2. 为什么？
3. 怎么用？

#### 1.1.1 什么是static？
```static```是Java语言中的重要关键字，用于修饰类或者方法，表示被所有类实例对象共享。        
对于变量而言，该变量只有一个副本存在，为所有类实例变量共享，当且仅当类首次加载的时候才做初始化操作。          
对于方法而言，方法是属于类的，而不属于任何一个类的实例变量（换句话说，也就是没法用```this```关键字！）

static变量使用示例：
```java
public class Test {
    private static int cnt = 0;
    private int id;

    public Test(){
        cnt++;
        this.id = cnt;
    }

    public int getId() {
        return id;
    }
}
```

static方法使用示例：
```java
public class Test {
    public static boolean testEqual(int a,int b){
        return a == b;
    }
}
```


### 1.2 final关键字与垃圾回收
### 1.3 Java语言的package机制
### 1.4 枚举类型
### 1.5 堆栈内存管理机制
### 1.6 继承
