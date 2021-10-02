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
对抽象类的学习，必须从三个维度出发：       
1. 是什么？
2. 为什么？
3. 怎么办？

#### 1.2.1 什么是抽象类？

在面向对象的概念中，所有的对象都是通过类来描绘的，但是反过来，并不是所有的类都是用来描绘对象的，如果一个类中没有包含足够的信息来描绘一个具体的对象，这样的类就是抽象类。

抽象类不能实例化，有可能有抽象方法；实例方法，静态方法，实例变量，静态变量都可以定义，这与普通的类并无差别。

#### 1.2.2 为什么要有抽象类？

抽象方法和抽象类看上去是多余的，对于抽象方法，不知道如何实现，定义一个空方法体不就行了吗，而抽象类不让创建对象，看上去只是增加了一个不必要的限制。

引入抽象方法和抽象类，是Java提供的一种语法工具，对于一些类和方法，引导使用者正确使用它们，减少被误用。

使用抽象方法，而非空方法体，子类就知道他必须要实现该方法，而不可能忽略。

使用抽象类，类的使用者创建对象的时候，就知道他必须要使用某个具体子类，而不可能误用不完整的父类。

无论是写程序，还是平时做任何别的事情的时候，每个人都可能会犯错，减少错误不能只依赖人的优秀素质，还需要一些机制，使得一个普通人都容易把事情做对，而难以把事情做错。抽象类就是Java提供的这样一种机制。

有可能，父类并不希望草率定义方法行为，而延迟到子类实现，这时候抽象方法就有存在的必要了！

（节选自https://www.cnblogs.com/swiftma/p/5594961.html ）

#### 1.2.3 怎么写抽象类？

使用```abstract```修饰类即可完成抽象类的书写：

示例：
```java
public abstract class Test {
    public abstract void display();

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
