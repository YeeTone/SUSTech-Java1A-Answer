# CS102A/CS107 Week 8
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## 1. 理论课内容框架
- 字符编码
- 字符串对象的构建与内存机制 **考点**
- 字符串对象的常用方法 **重要**
- 字符串不可变性，字符串循环拼接优化与StringBuilder，StringBuffer的使用 **重要**
- 基本数据类型及其包装类

### 1.1 字符编码

常用的字符编码有以下几种：（了解即可，并非考试重点）        
1. ASCII: 最原始的编码，1个byte，8个bit，共128个字符；
2. Unicode: 统一字符集，3个byte，24个bit，0x000000~0x10FFFF，属于编码扩充，共1100K+种字符，是Java默认的编码方式
3. 万国码: UTF32(定长) UTF16(变长) UTF8(变长) 本质是对Unicode的编码方式
4. GBK: 汉字内码扩展规范，确定对中文的编码

For more details, please refer to EE411: 信息论与编码

指定编码打印字符：```s.getBytes("UTF-8");```

在线学习网站：http://tool.chinaz.com/tools/unicode.aspx

### 1.2 字符串对象的构建与内存机制 考点

**考试考点** 判断，选择，填空都有可能考，而且很容易混，建议认真理解这部分内容！

在Java语言中，字符串有两种可能的存储位置：常量池、堆（其中常量池是堆当中单独划分出来的一片区域）

在Java语言中，获得字符串对象的方法主要有以下两种：

```java
String s1 = "Hello";
String s2 = new String("Hello");
```

这两种初始化字符串对象的方法都可以得到内容为```Hello```的字符串对象，但是这个过程中JVM做了什么事情，是需要各位同学理解并且记忆的！

#### 重点讲解1

先看第一句：
```java
String s1 = "Hello";
```
主要完成的工作是：
1. 检查字符串常量池中有无"Hello"字符串；
2. 发现没找到，于是JVM在字符串常量池中创建字符串对象"Hello"；
3. 返回该常量池中对该字符串对象的引用；

因此，做了这三步以后，才算完成了字符串对象的构建；

如果在运行了第一句后，再来两句：
```java
String s1_ = "Hello";
System.out.println(s1==s1_);
```
此时的结果一定为```true```，因为第二步中：
1. 检查字符串常量池中有无"Hello"字符串；
2. 发现找到了，直接返回该字符串在常量池中的引用给s1_；
因此```s1```和```s1_```指向同一对象，地址相同，所以为```true```

#### 重点讲解2

再看第二句：
```java
String s2 = new String("Hello");
```
主要完成的工作有：
1. 执行重点讲解1的过程以创建常量池中的"Hello"字符串；
2. 在堆中找片区域，申请出来，给s2对象使用，s2指向的内存空间在堆里；
3. 字符串本质上是char数组，s2指向的字符串对象和常量池中"Hello"字符串将共享同一个char数组；


如果在运行了第二句后，再来两句：
```java
String s2_ = new String("Hello");
System.out.println(s2==s2_);
```
此时的结果一定为```false```，因为在刚才的步骤中，s2和s2_在堆中的不同内存区域，所以必定结果为```false```！

（这部分难度会比较大，建议画图展示，并且安排自由提问环节）

#### 重点讲解3
我们知道：字符串是可以使用```+```来进行字符串连接的，很简洁也很优雅。

现在请问这部分代码的结果会是什么？
```java
String s1 = "Hi";
String s2 = s1 + "";
System.out.println(s1 == s2);
```
结果将会是```false```。

其实，这部分代码如果写成这样，你们可能就能理解为什么是```false```了。
```java
String s1 = "Hi";
StringBuilder b = new StringBuilder(s1);
b.append("");
String s2 = b.toString();
System.out.println(s1 == s2);
```
我将会在 1.4章节中详细介绍为什么是```false```，现在有个大概印象就行。

### 1.3 字符串对象的常用方法 重要
String对象本质上是一个```char```数组。         

和String相关的API一直在增加，我就简单选取一部分我认为比较常用的进行介绍，主要分4类：       
（参考资料来源：https://blog.csdn.net/xupt_rl/article/details/89474033 ）

#### 1.3.1 获取字符串的信息
- public int length(): 获取字符串的长度。
- public char charAt(int index): 获取指定索引位置的字符
- public int indexOf(int ch): 返回指定字符在此字符串中第一次出现处的索引。
- public int indexOf(String str): 返回指定字符串在此字符串中第一次出现处的索引。
- public int indexOf(int ch,int fromIndex):返回指定字符在此字符串中从指定位置后第一次出现处的索引。
- public int indexOf(String str,int fromIndex): 返回指定字符串在此字符串中从指定位置后第一次出现处的索引。
- public String substring(int start): 从指定位置开始截取字符串,默认到末尾。
- public String substring(int start,int end): 从指定位置开始到指定位置结束截取字符串。

#### 1.3.2 判断字符串的性质
- public boolean equals(Object obj): 比较字符串的内容是否相同,区分大小写
- public boolean equalsIgnoreCase(String str): 比较字符串的内容是否相同,忽略大小写
- public boolean contains(String str): 判断字符串中是否包含传递进来的字符串
- public boolean startsWith(String str): 判断字符串是否以传递进来的字符串开头
- public boolean endsWith(String str): 判断字符串是否以传递进来的字符串结尾
- public boolean isEmpty(): 判断字符串的内容是否为空串""。

#### 1.3.3 字符串相关的转换
- public byte[] getBytes(): 把字符串转换为字节数组。
- public char[] toCharArray(): 把字符串转换为字符数组。
- public static String valueOf(char[] chs): 把字符数组转成字符串。
- public static String valueOf(int i): 把int类型的数据转成字符串。（String类的valueOf方法可以把任意类型的数据转成字符串。）
- public String toLowerCase(): 把字符串转成小写。
- public String toUpperCase(): 把字符串转成大写。
- public String concat(String str): 把字符串拼接。
- Integer.parseInt(String s): 将字符串转换为整数类型

#### 1.3.4 字符串相关的其他功能
- public String replace(char old,char new) 将指定字符进行互换
- public String replace(String old,String new) 将指定字符串进行互换
- public String trim() 去除两端空格
- public int compareTo(String str) 会对照ASCII 码表 从第一个字母进行减法运算 返回的就是这个减法的结果，如果前面几个字母一样会根据两个字符串的长度进行减法运算返回的就是这个减法的结果，如果连个字符串一摸一样 返回的就是0
- public int compareToIgnoreCase(String str) 跟上面一样 只是忽略大小写的比较

### 1.4 字符串不可变性，字符串循环拼接优化与StringBuilder，StringBuffer的使用 重要
先上一下不可变对象的定义：      
```
不可变对象：对象一旦被创建它的状态（对象的数据，也即对象属性值）就不能改变。
```
我们可以看到：String是符合这种类型的。

因此，上文中提到的字符串"+"实现的字符串拼接，其实是又创建了一个对象，因此==的结果就是false。

之前介绍对象创建的时候，有好几个步骤，因此对象创建的代价是极其高昂的。      

有时候，如果我们需要大量的字符串拼接，那么不断通过"+"进行拼接的代价就很高，实在不是明智之举！       
如下所示的代码就很不好（大家可以在自己的电脑上run一下这个程序，我自己的电脑是5分钟都没法结束）：

```java
String longString = "";
for (int i = 0; i < 2000000; i++) {
    longString+=(i);
}
```

Java语言的设计者考虑到了这一点，就设计了```StringBuilder/StringBuffer```类，用于实现字符串拼接。     

JDK编译选项：
```java
javac Test.java
```
JDK反编译选项：
```java
javap -c Test.class
```

比如说，将上面的代码改写成这种等价形式，运行时间大大缩短（我的机器只用了75ms即完成了200W的拼接操作）：
```java
StringBuilder longString = new StringBuilder();
for (int i = 0; i < 2000000; i++) {
    longString.append(i);
}
```
运行效率提升这么多，还有谁说StringBuilder不香！

本质：char数组，但是允许变更（不被```final```修饰），并且会灵活地进行动态扩容。          

区别：线程安全问题，这个不需要详细掌握，只需大概有个印象即可。         

功能主要可以分为四类：      

#### 1.4.1 增
- append：在末尾拼接字符串
- insert：在指定位置插入字符串

#### 1.4.2 删
- delete: 删除一片区域的所有字符，并将后续的字符串向前移动
- deleteCharAt: 删除指定位置上的字符

#### 1.4.3 改
- replace: 取代某一个位置上的字符
- reverse: 字符串内部排布整体进行反转
- setLength: 设置当前可用长度

#### 1.4.4 查
- length: 当前字符串对象的长度
- indexOf: 查看某个字符的第一个出现的位置
- lastIndexOf: 查看某个字符的最后一次出现的位置
- toString: 获取当前字符串对象的内容


### 1.5 基本数据类型及其包装类
```java
Boolean -> boolean
Byte -> byte
Short -> short
Character -> char
Integer -> int
Long -> long
Float -> float
Double -> double
```

有一些比较有用的API：
- Character.isDigit: 判断是不是数字         
- Character.isLetter: 判断是不是字母
- Character.isLowerCase: 判断是不是小写字母
- Integer.parseInt: 特定进制的字符串表示转整数
- Integer.toString: 整数转特定进制的字符串表示
- Double.parseDouble: 字符串转double浮点数

只要会用就行，不是考试重要的考点。
