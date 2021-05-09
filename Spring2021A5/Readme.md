# Explanation to A5
### **[Notice]**  
1.请基于Structure框架进行本次作业的完成。本次作业5题相互关联，错综复杂，使用Structure框架可以避免OJ和LJ上的编译错误！  
2.OJ上已经部署了Structure框架下的CourseOperator.java和CourseTime.java，因此提交时对这两个类做出的任何修改都将视为无效！  

## Problems
1: 设计Building类，以及一些基本的枚举类和抽象类(10 points);  
2: 设计Classroom类(20 points);  
3: 设计Course类(20 points);  
4: 设计Teacher类，要求继承于抽象类Person，并实现CourseOperator接口;(25points)  
5: 设计Student类，要求继承于抽象类Person，并实现CourseOperator接口;(25points)  

## Problem1: Building
类对象内部成员：  
```java
private List<Classroom> rooms;  
private Location location;  
private int id;  
```
1.构造方法  
```java
public Building();  
public Building(Location location , int id);  
```
**【要求解读】**  
1.任一构造方法都需要初始化rooms的list对象；
2.参数列表需要用于初始化内部成员对象；  
**【题解提示】**  
List接口的实现类有ArrayList, LinkedList等等。  
