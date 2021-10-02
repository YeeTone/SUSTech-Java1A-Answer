# CS102A/CS107 Week 9
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## 1. 理论课内容框架

- 类的设计
- 构造方法重载
- setter/getter方法讲解
- 类对象之间的```is-a```和```has-a```关系

### 1.1 类的设计

类与对象的设计中，有5个环节：     
- 类：所有类对象的共同特征
- 方法：允许外部对类对象进行操作的入口
- 对象：类的实例载体
- 方法调用：对方法入口的调用操作
- 实例变量：类对象内部封装的一些属性 etc

#### 重点1 RECALLS
RECALL1: 类的设计中，如果没有构造方法，那么Java编译器会帮你写一个无参的，什么都不做的构造方法。       

RECALL2: 类的实例变量初始化时，可以显式指定其默认值。        

RECALL3: 方法体的使用，有利于保证数据安全性和数据隐私（可以过滤非法数据） -> consistent vs correct。         

RECALL4: ```this```关键字可以用于区分同名成员变量和临时变量。

#### 重点2 consistent VS correct
consistent: 合理的，就像分钟只能在0-59之间；          
correct: 正确的，就如同分钟必须反映真实世界的分钟数；      

correct是consistent的一个子集

#### 重点3 访问权限控制

在数据处理中，我们并不一定希望外部可以随意更改数据，希望对这个更改的过程做一些限制，或者是检查，这时候访问权限控制就很有必要了。

在Java语言中，一共支持4种权限修饰符：      
```private```: 仅限类内访问操作，控制最为严格，将外部的访问直接禁了。         

```protected```：introduced later        

```(default)```：introduced later        

```public```：外部可以直接调用与修改，控制最松，直接放开了外部访问。    

### 1.2 构造方法重载

之前我们学习了普通方法的重载，构造器方法是否也支持重载？支持！       

示例：
```java
public class Test {
    public Test(int i){
        System.out.println("int constructor");
    }

    public Test(String i){
        System.out.println("String constructor");
    }
}
```

方法要求是一样的：参数列表不同就构成重载！

另外，构造方法之间还支持相互调用，通过this关键字，课件上的示例就非常好：        

```java
public class Time2 {
    public Time2(int h, int m, int s) {
        setTime(h, m, s);
    }
    public Time2(int h, int m) {
        this(h, m, 0);
    }
    public Time2(int h) {
        this(h, 0, 0);
    }
    public Time2() {
        this(0, 0, 0);
    }
    public Time2(Time2 time) {
        this(time.getHour(), time.getMinute(), time.getSecond());
    }
}
```

不过有点要求，就是this关键字必须是第一句话，且不能循环调用this！

讲了这么多，this究竟是个什么东西呢？要我说，那就是指向自己的一个指针，而且不允许变更指针指向的对象位置！

### 1.3 setter/getter方法讲解
### 1.4 类对象之间的```is-a```和```has-a```关系
