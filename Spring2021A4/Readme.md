# Explanantion to A4
本次作业主要是类与对象设计的第一次作业，因此题解以题目意思解释为主
## A3Q4 数学向量
难度：简单  
要求：定义一个数学意义上的三维向量类，然后实现一些关于向量的基本运算功能。  
### 类成员属性
```Java
private int x;
private int y;
private int z;
```
以上三个int类型变量表示该三维向量沿各自坐标轴的投影分量。  
### 构造方法
```Java
public Vector(int x,int y,int z)
```
该构造方法要对于那三个成员变量做初始化操作。  
【提示】构造方法中初始化成员变量示例：  
```Java
public Vector(int x,int y,int z){
    this.x=x;
    ...
}
```
### 象限方法
```Java
public int quadrant()
```
该方法要返回向量所在象限。如果xyz中任一值为0，则返回-1。  
【提示】使用多条if-else语句判断this中xyz值的情况即可。  

### 模长方法
```Java
public double modulus()
```
该方法要返回向量的模长，计算方法略。  
【提示】成员变量均为int类型，相乘的结果有可能超过int范围而导致溢出，发生结果错误。  
可以考虑升级int为double后进行计算，也可以使用Math.pow方法。     

### toString方法
```Java
public String toString()
```
该方法返回向量的规范表示形式：(x,y,z)
【提示】小心全角半角括号的区别  

### 向量加法
```Java
public Vector add(Vector vector)
```
该方法要将传入的向量的xyz值加到当前对象的对应值中，然后返回当前向量。  
【提示】更新当前对象对应值方法示例：
```Java
  this.x+=vector.x;
```
【提示】返回当前对象对应代码：
```Java
  return this;
```

### 向量面积
```Java
public double area(Vector vector)
```
该方法要计算一个三角形面积，并将结果返回。三角形的三个顶点分别是：  
(0,0,0)，(this.x,this.y,this.z)，(vector.x, vector.y, vector.z)   
计算方法参考海伦公式。   
【提示】同模长方法的提示。  

### 向量加法(静态)
```Java
public static Vector add(Vector a, Vecotr b)
```
该方法要将传入的两个向量a和b加到一个新的向量里面，然后返回新向量。  
【提示】静态方法中，传入的a，b对象仍然可以调用自身所存有的非静态add方法。  

### 向量面积(静态)
```Java
public static double area(Vector a,Vector b)
```
该方法要计算一个三角形面积，并将结果返回。三角形的三个顶点分别是：  
(0,0,0)，(a.x,a.y,a.z)，(b.x, b.y, b.z)   
【提示】静态方法中，传入的a，b对象仍然可以调用自身所存有的非静态area方法。  

## A4Q1 魔法字符串
难度：中等  
要求：定义一个具有优先级的字符串集合类，然后实现一些对于其中存储字符串对象集合的操作。  
### 类成员属性
```Java
private int[]priority;
```
priority数组中一共有26个元素，每个元素的大小在1~26之间。  
该数组表示字母到整数的优先级映射关系，如第0位元素就表示字母'a'的优先级，第1位元素就表示字母'b'的优先级，etc.  
元素越大，则表示该位字母（无论大小写）的优先级越高。  
```Java
private String[] ss;
```
ss数组表示所有内部存储的字符串集合。每一个ss数组中的元素，都必须只能由26个英文字母的大小写组成。  

### 构造方法
```Java
public MagicStrings(String s)
```
该构造方法使用setSs方法初始化内部成员变量ss数组，初始化优先级使用默认的优先级(1~26)即可。   
```Java
public MagicStrings(int[] priority, String s)
```
该构造方法使用setPriority(int[] priority)方法初始化，使用setSs方法初始化内部成员变量ss数组。  
```Java
public MagicStrings(String priority, String s)
```
该构造方法使用setPriority(String priority)方法初始化，使用setSs方法初始化内部成员变量ss数组。  

## A4Q2 图书馆房间
## A4Q3 图书馆房间管理员
