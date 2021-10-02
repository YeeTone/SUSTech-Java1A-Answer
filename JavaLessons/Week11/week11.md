# CS102A/CS107 Week 11
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## 1. 理论课内容框架
- 多态
- 抽象类
- 方法重写，final方法与静态绑定
- 接口
- 继承

### 1.1 多态
对多态的学习，必须从三个维度出发：       
1. 是什么？
2. 为什么？
3. 怎么办？

#### 1.1.1 什么是多态？
多态是同一个行为具有多个不同表现形式或形态的能力。

多态就是同一个接口，使用不同的实例而执行不同操作。

举个实际生活的例子：中国人吃饭的习惯会因为南北地域差异而有所不同，因此对于"吃"这个接口，南方人和北方人就会产生不同的行为，这就是多态！

#### 1.1.2 为什么要有多态？
- 消除或者减弱类之间的耦合关系
- 增强可替换性
- 增强可扩充性
- 体现接口性
- 增强灵活性
- 增强简化性

#### 1.1.3 怎么实现多态？
三个必要条件：
- [X] 存在父类和子类的继承关系
- [X] 子类重写父类方法
- [X] 父类引用指向子类对象

示例：
```java
public class Test {
    public void display(){
        System.out.println("Test::display() is invoked!");
    }

    public static void main(String[] args) {
        //requirement 3
        Test t = new SubTest();
        t.display();
    }
}
class SubTest extends Test{//requirement 1
    @Override
    //requirement 2
    public void display(){
        System.out.println("SubTest::display() is invoked!");
    }
}
```
该代码的输出结果是：
```
SubTest::display() is invoked!
```

### 1.2 抽象类
