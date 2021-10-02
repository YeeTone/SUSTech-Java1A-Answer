# CS102A/CS107 Week 10
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## 1. 理论课内容框架
- 静态类成员变量与方法 **考点**
- final关键字
- 垃圾回收 **考点**
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

对于方法而言，方法是属于类的，而不属于任何一个类的实例变量（换句话说，也就是没法用```this```关键字，也不能访问非static的变量！）

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
这个例子中的```static```变量记录了Test对象创建的个数。

static方法使用示例：
```java
public class Test {
    public static boolean testEqual(int a,int b){
        return a == b;
    }
}
```
这个例子中的testEqual方法可以直接通过类名调用。

#### 1.1.2 为什么要有static关键字？

类对象本身的实例变量（没有用static修饰的），是为了彰显类对象个体之间的差异性（个性）；           
而隶属于类的静态变量（使用了static修饰的），是为了彰显类对象个体之间的共性！         

有人可能会问了：那我建一个相同的类对象属性不就好了？          
对于这个问题，这种解决问题的方法是对内存空间极大的浪费！既然各个对象中都存在同样的数据，那么何不将这些相同的数据单独拿出来并且单独存放呢？

因此static关键字诞生了，允许用户无需创建对象即可访问一个类的某些修饰成分，大大方便了程序的开发！

#### 1.1.3 static关键字怎么使用？

static本身是一个非权限控制修饰符，因此可以放在类对象属性/方法的修饰符位置！

RECALL: 对象属性/对象方法的写法？

### 1.2 final关键字
final的中文翻译为“最后的，最终的”，其在Java语言中的含义与之也是相对应的。       

主要有3个地方能使用```final```关键字：        
- 类修饰
- 方法修饰
- 变量修饰

#### 1.2.1 final修饰类

主要功能：将一个类定义为“终类” -> 也就是说继承链到这里就结束了 -> 禁止其他的类继承该类

举例：```java.lang.String```       
```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    //...
}
```

#### 1.2.2 final修饰方法

主要功能：将一个方法定义为“终方法” -> 也就是说方法重写链到这里就结束了 -> 禁止其他的类继承该类时重写该方法

final关键字对方法的重写的禁止，无论是否是静态，都会生效！（对于静态方法，final关键字将不允许子类定义同名同参方法，不允许子类隐藏该方法！）

两种权限修饰的方法自动会成为final的，不会让子类重写：       
- ```private```
- ```static```

举例1：        
以下的代码将无法通过编译：       
```java
public class Test {
    public final void printTest(){
        System.out.println("Test Final");
    }
}
class SubTest extends Test{
    public void printTest(){
        System.out.println("Sub Test Final");
    }
}
```
无法通过编译的原因是因为SubTest子类尝试重写其能够访问到的父类方法，受到了关键字```final```的阻止。          

举例2：      
以下的代码可以通过编译：         
```java
public class Test {
    private final void printTest(){
        System.out.println("Test Final");
    }
}
class SubTest extends Test{
    public void printTest(){
        System.out.println("Sub Test Final");
    }
}
```
可以通过编译的原因是因为Test的方法使用了```private```关键字进行了访问控制，父类中有无该方法对子类来说是不可见的，因此相当于子类重新定义了一个同名方法，并不存在继承关系。这其实也暗含了一个信息：```private```修饰的成员方法会隐式地增加```final```关键字！           

举例3：       
以下的代码不能通过编译：       
```java
public class Test {
    public static final void printTest(){
        System.out.println("Test Final");
    }
}
class SubTest extends Test{
    public static void printTest(){
        System.out.println("Sub Test Final");
    }
}
```
不能通过编译的原因是父类静态方法使用关键字```final```做修饰，禁止子类隐藏该方法！

#### 1.2.3 final修饰变量

主要功能：将一个变量定义为“终变量” -> 也就是说该变量的状态就是最终的 -> 禁止一切对该变量的修改操作！

在实际应用中，我们除了可以用final修饰成员变量、成员方法、类，还可以修饰参数、若某个参数被final修饰了，则代表了该参数是不可改变的。

final还有利于显式告知编译器，让编译器在编译期间就完成特定的代码优化与常量替换，如下所示：       
```java
String s1 = "Hi";
final String s2 = "Hi";

String s1_ = s1 + 2;
String s2_ = s2 + 2;

String constant = "Hi2";

System.out.println(s1_==constant);
System.out.println(s2_==constant);
```
这段代码的输出结果是：
```java
false
true
```
这是因为Java编译器在编译的时候就做了优化，发现自己能够提前算出s2_是"Hi2"，因此就直接做了常量替换。而根据先前的字符串的内存管理机制，我们可以知道结果分别是```false```和```true```

### 1.3 垃圾回收机制
老规矩，还是三个维度：      
1. 是什么？
2. 为什么？
3. 怎么办？

#### 1.3.1 什么是垃圾回收机制？
先有必要了解一下什么是垃圾：
```
垃圾：没有被外部引用的内存对象被称为垃圾
```
有一些语言赋予了程序员直接操作内存的权力，如强大的C/C++；但是正如一句话所言：       
```
权力越大，责任越大
```
C/C++语言需要用户手动操作内存的申请与释放，这就带来了极大的工作量，也带来了极大的内存泄漏（内存申请了没释放，然后就变成了内存中的垃圾，再也无法回收）的风险。

1960年，垃圾回收机制的提出将程序员从烦人的内存管理中解放出来，使得编程效率大大提高！    

#### 1.3.2 为什么要有垃圾回收机制？

垃圾回收机制可以自动管理内存，编程人员只需关心对象的申请即可，而不需要关心内存的释放问题！

#### 1.3.3 垃圾回收机制是怎么操作的？
程序员可以通过调用
```java
System.gc();
```
来通知JVM，**建议**JVM进行垃圾回收（也就是说实际上垃圾回收器会不会启动还得看JVM的心情）        

垃圾回收机制有几个很有意思的算法，有兴趣的同学可以自己去搜索：      
- 引用计数算法
- 可达性分析算法
- 标记清除算法
- 分代收集算法

对于课程的考核要求来说，只要知道什么时候有可能启动垃圾回收（答案是没有外部引用的时候就有可能被启动回收掉），垃圾回收的启动时机即可，以及finalize方法是不是会被调用也不确定就可以了。       
示例：     
```java
int[] a = new int[2]；
a = new int[3];
```
第一个申请的2长度的int数组就有可能被回收了，但我也不知道有没有回收。        

finalize方法的是否调用，什么时候调用对用户来说都是未知数，JVM都将这个底层实现屏蔽掉了。

### 1.4 Java语言的package机制

作用：      
- 方便代码管理
- 访问权限控制

访问权限控制：      
有一个访问权限：```no modifier```，作用是允许自身和同个package的其他类访问，工程中使用不多。

### 1.5 枚举类型
老规矩，三个维度：      
1. 是什么？
2. 为什么？
3. 怎么用？

#### 1.5.1 枚举是什么？
枚举是特殊的类，是一个被命名的对象的集合，用于声明一组带标识符的常值。        

枚举的类对象的实例个数是绝对确定的，在编译期就完成了确定和初始化。

#### 1.5.2 为什么要有枚举？

枚举的好处有很多：      
1. 枚举类的实例个数绝对确定，在Runtime的各种情况下都不会变更，很好地满足了单例模式和多例模式的设计需要！
2. 枚举禁止了反射攻击，禁止了线程不安全带来的隐患
3. 枚举写起来代码很少，也很优雅。

#### 1.5.3 枚举怎么使用？
示例：         
```java
enum Signal
{
    GREEN,YELLOW,RED;
}
```
**枚举类有哪些方法？**       
所有枚举自动继承```java.lang.Enum```类，主要有以下方法：       
- values(): 以数组形式返回枚举类型的所有成员
- valueOf(): 将普通字符串转换为枚举实例
- compareTo(): 比较两个枚举成员在定义时的顺序
- ordinal(): 获取枚举成员的索引位置

### 1.6 堆栈内存管理机制
课件已经介绍地足够详细，简而言之概括就是：
```
1. 方法调用在栈上；
2. 临时变量引用在方法内；
3. 真正对象在堆里；
4. 堆和栈相互独立，常量池是堆的一片小空间。
```
记住这个图应该就可以了：
![image](https://user-images.githubusercontent.com/64548919/135720768-ddd2a60d-b7c3-448e-9a15-253bf5b91e06.png)

### 1.7 继承
老规矩，三个维度：      
1. 是什么？
2. 为什么？
3. 怎么用？
