# Explanantion to A4
本次作业主要是类与对象设计的第一次作业，因此题解以题目意思解释为主
## A3Q4 数学向量
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
## A4Q2 图书馆房间
## A4Q3 图书馆房间管理员
