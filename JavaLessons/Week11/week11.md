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

绑定：一个方法的调用与方法所在的类(方法主体)关联起来。

本质：动态绑定（也就是说后面还有静态绑定）

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
}
class SubTest extends Test{
    @Override
    public void display(){
        System.out.println("SubTest::display() is invoked!");
    }
}
```

### 1.3 方法重写，final方法与静态绑定

#### 1.3.1 方法重写

是什么：重写是子类对父类的允许访问的方法的实现过程进行重新编写, 返回值和形参都不能改变。即外壳不变，核心重写！

为什么：重写存在的意义在于：子类可以根据需要，重新定义特定于自己的行为。

怎么办：如何重写？遵循以下示例：
```java
public abstract class Test {
    public abstract void display();
}
class SubTest extends Test{
    @Override
    public void display(){
        System.out.println("SubTest::display() is invoked!");
    }
}
```
#### 1.3.2 final方法

是什么：final方法是不允许继承/重写/隐藏/覆盖的方法

为什么：final方法有两个存在的意义：
- 运行加速（选讲：内嵌机制）
- 禁止重写（选讲：中断继承链）

怎么办：使用final关键字修饰方法即可（实例方法/静态方法均可）

```java
public final int sum(int a, int b){
    return a + b;
}
```

```java
public static final int sum(int a, int b){
    return a + b;
}
```
#### 1.3.3 静态绑定
静态绑定：在程序执行前方法已经被绑定（也就是说在编译过程中就已经知道这个方法到底是哪个类中的方法），这是由编译器或其它连接程序实现。

静态绑定主要有4种可能性：
- final方法
- static方法
- private方法
- 构造方法

我们可以归纳出这4种方法的特性：如果一个方法不可被继承或者继承后不可被覆盖，那么这个方法就采用的静态绑定。

### 1.4 接口
对接口的学习，必须从三个维度出发：       
1. 是什么？
2. 为什么？
3. 怎么办？

#### 1.4.1 接口是什么？

接口（英文：Interface），在JAVA编程语言中是一个抽象类型，是抽象方法的集合，接口通常以```interface```来声明。

接口具有的特性：
- 接口不能被实例化。
- 接口中所有方法不能有主体。（好像现在增加了default，允许写一些默认的实现）
- 一个类可以实现多个接口。
- 接口可以有变量，但一定是public static final 修饰，就算你不写，JVM也会默认是这个。
- 接口中的方法只能是public修饰。
- 一个接口不能继承其他类，但是可以实现别的接口。

接口和抽象类的区别：
- 抽象类中的方法可以有方法体，就是能实现方法的具体功能，但是接口中的方法不行。（现在接口可以有default方法体）
- 抽象类中的成员变量可以是各种类型的，而接口中的成员变量只能是 public static final 类型的。
- 接口中不能含有静态代码块以及静态方法(用 static 修饰的方法)，而抽象类是可以有静态代码块和静态方法。
- 一个类只能继承一个抽象类，而一个类却可以实现多个接口。

#### 1.4.2 为什么要有接口？

先前提到，Java语言只支持单继承，这对描述一些共同特征很多的类对象带来了困难。

接口的目的是指明相关或者不相关类的多个对象的共同行为，跟抽象类很相似，可以说接口是更加抽象的抽象类。

接口体现了程序设计的多态和高内聚低耦合的设计思想。

#### 1.4.3 怎么使用接口？
主要通过```implements```关键字实现接口

```java
interface Swimmable{
    void swim();
}

abstract class Duck implements Swimmable{
    @Override
    public abstract void swim();
}

class YellowDuck extends Duck implements Swimmable{
    @Override
    public void swim() {
        System.out.println("Yellow Duck is swimming");
    }
}
```

注意：一定要先```extends```再```implements```，考试的时候考过！
