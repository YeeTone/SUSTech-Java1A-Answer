# CS102A/CS107 Week 2
（Based on the slides from Prof.Fengwei ZHANG）
## 1. 理论课内容框架
- java程序简览
- 基本数据类型
- java算术运算 **考点**
- java算数表达式求值顺序 **考点**
- 决策/条件语句 **考点**

### 1.1 java程序简览

学习要领：不明白的地方先不用管，先记着就行！      
```java
public class Hello{
  public static void main(String[] args){
    System.out.println("Hello World!");
    //This can output "Hello World!"
    /*
    World: Hello, Java Programmer!
    */
  }
}
```

对该标准程序的重要信息解读：     
1. 一切皆对象    
```java 
public class Hello{

}
```
2. main函数的写法
```java
public static void main(String[] args){

}
```
3. 内容输出
```java
System.out.println("Hello World!");
```
4. 内容注释     
单行注释：     
```java
//This can output "Hello world!"
```
多行注释：     
```java
/*     
World: Hello, Java Programmer!
*/    
```

#### Java程序的语法注意事项
- 注释：单行```//```，多行```/* */```      
- 命名规则：首位非数字，其余位可以由以下几个构成：a-z A-Z 0-9 $ _       
建议：驼峰命名法     
- 括号务必对应匹配！
- 编译java程序：```javac Hello.java```，结果生成.class文件；    
运行java程序：```java Hello```
- 输出：三种方法
```java
System.out.println("Hello World!");
```
↑输出内容后直接换行↑
```java
System.out.print("Hello World!");
```
↑输出内容不换行↑
```java
System.out.printf("Hello World!");
```
↑格式化输出↑

- 换行符：\n    
- 转义字符：\，eg.\n \t \r \\ \"
- 格式化输出：%s(字符串)，%d(整数)，%f(浮点数)，%.4f(保留4位有效小数) 更多用法，详见网上教程！

### 1.2 基本数据类型
总体来说，一共八种，可以分为四类：      
- 真假 ```boolean```
- 整数 ```byte short int long```
- 字符 ```char```
- 浮点数 ```float double```

数据范围：
- boolean: true false
- byte: -128 ~ 127 (-2^7 ~ 2^7-1)
- short: -32768 ~ 32767 (-2^15 ~ 2^15-1)
- char: 0 ~ 65535 (0 ~ 2^16-1) (\u0000 ~ \uffff)
- int: -2147483648 ~ 2147483647 (-2^31 ~ 2^31-1)
- long: -9223372036854775808 ~ 9223372036854775807 (-2^63 ~ 2^63-1)
- float: +-(3.4* 10^-38 ~ 3.4* 10^38)
- double: +-(1.7* 10^-308 ~ 1.7* 10^308)
(for the detailed reason, please refer to CS207: Digit Design)

补充：      
无限大整数 BigInteger
无限精度/大小浮点数 BigDecimal

### 1.3 算术运算 ！！！重要考点！！！
学习要领：Scanner的用法先记着，以后会有机会慢慢解释            

五种运算： + - * / %
**【重要考点】两整数相除的结果是自动将小数位数截断的！**

### 1.4 运算符优先次序 ！！！重要考点！！！
以下这张表格供参考（来源：https://www.cnblogs.com/gavin-yao/p/10595835.html ）：
![image](https://user-images.githubusercontent.com/64548919/135467035-18a0243f-3025-4f98-b1bd-2180359d05dc.png)
以考试出题的形式来说，前5条是必须记住的，因为是高频考点！

给大家一个小诀窍来记忆b=a++和b=++a的区别：      
a++将++放在了后面，因此++运算是后做的，运算时b用的是a之前的值，因此b就比a小；     
++a将++放在了前面，因此++运算是先做的，运算时a要先++，因此a和b的值就相同；    

### 1.5 决策/条件语句 ！！！重要考点！！！
三种逻辑判断选择结构：if else-if else
```java
if(...){
  //type1
} else if(...){
  //type2
} else {
  //type3
}
```

其中注意一个细节，如果if-else中没有使用花括号进行划定范围，那么只能作用于1句statement，例如：     
```java
int a = 0;
if (a==0)
  System.out.println("a = 0");
else 
  System.out.println("a /= 0");
  System.out.println("a's value = "+a);
```

这个代码块的运行结果是两行结果：
```
a = 0
a's value = 0
```
