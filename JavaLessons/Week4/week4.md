# CS102A/CS107 Week 4 
（Based on the slides from Prof.Fengwei ZHANG(张锋巍)）

## 1. 理论课内容框架
- for/while/do-while循环 **考点**
- switch语句 **考点**
- continue和break语句 **考点**
- 逻辑运算符
- 结构化编程

### 1.1 for/while/do-while循环
for 循环：
```java
for (int i = 0; i <= 10; i++){
  System.out.println(i);
}
```

while 循环：
```java
int i = 0;
while (i <= 10){
  System.out.println(i);
  i++;
}
```

do-while 循环：
```java
int i = 0;
do {
  System.out.println(i);
  i++;
}while (i <= 10);
```

以上三段代码等价，都是输出1-10，每行一个数。      

for循环结构：     
```java
for(A; B; C)
```
A：循环体内临时变量定义      
B：循环体内的循环条件，需要是```boolean```类型       
C: 每次循环后进行的操作       

拓展：     
- A B C均可为空
- for/while 循环如果没有花括号划定范围，那么只能作用于1条语句

