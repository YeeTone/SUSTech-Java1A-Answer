# CS102A/CS107 Week 2
（Based on the slides from Prof.Fengwei ZHANG）
## 1. 理论课内容框架
- java程序简览
- 基本数据类型
- java算术运算
- java算数表达式求值顺序
- 决策/条件语句

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

### Java程序的语法注意事项
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
