# Explanation to A5
### **[Notice]**  
1.请基于Structure框架进行本次作业的完成。本次作业5题相互关联，错综复杂，使用Structure框架可以避免OJ和LJ上的编译错误！  
2.OJ上已经部署了Structure框架下的CourseOperator.java和CourseTime.java，因此提交时对这两个类做出的任何修改都将视为无效！  

## Problems
1: 设计Building类，以及一些基本的枚举类和抽象类(10 points);  
2: 设计Classroom类(20 points);  
3: 设计Course类(20 points);  
4: 设计Teacher类，要求继承于抽象类Person，并实现CourseOperator接口(25 points);  
5: 设计Student类，要求继承于抽象类Person，并实现CourseOperator接口(25 points);  

## Problem1: Building
类对象内部成员：  
```java
private List<Classroom> rooms;  
private Location location;  
private int id;  
```
#### 1. 构造方法   
```java
public Building();  
public Building(Location location , int id);  
```
**【要求解读】**  
1.任一构造方法都需要初始化rooms的list对象；
2.参数列表需要用于初始化内部成员对象；  
**【题解提示】**  
List接口的实现类有ArrayList, LinkedList等等。  

#### 2. getter和setter方法
```java
public Location getLocation();  
public void setLocation(Location location);  
public int getId();  
public void setId(int number);  
public List<Classroom> getRooms();  
public void setRooms(List<Classroom> rooms);  
```
**【要求解读】**  
无  
**【题解提示】**
无

#### 3. addRoom和deleteRoom方法  
```java
public boolean addRoom(Classroom room);  
public boolean deleteRoom(Classroom room);
```
**【要求解读】**  
操作增加和删除之前，需要做传入的Classroom对象是否在当前building中。如果是同一Building，那么就执行相应的增加和删除。   
哦对了，删除还要检查当前building对象里面是否存在传入的room。  
**【题解提示】**  
1.比较是否是同一building可以用==，也可以用equals方法；  
2.ArrayList中add方法和remove方法返回的结果都是boolean类型。其中add方法返回一定是true, remove方法如果找到了就移除并返回true，否则返回false；

#### 4.重写toString方法
```java
public String toString();
```
**【要求解读】**  
无  
**【题解提示】**  
按照题目意思来即可。  

#### 5.基本枚举类和抽象类
枚举类型：Location, CourseType, Day
抽象类型：Person
**【要求解读】**  
无
**【题解提示】**  
按照Structure的模板来即可。  

## Problem2: Classroom
类对象内部成员：  
```java
int id;//eg:101  
int seatNum;//eg:50    
CourseType type;// Lecture or Lab   
Building building;  
Map<CourseTime, Course> schedule;   
```
#### 1. 构造方法与getter,setter方法   
```java
public Classroom();
public Classroom(int id , int seatNum , Building building, CourseType type);
```
**【要求解读】**  
任一构造方法都需要初始化scedule。对于有参的构造方法，分别实例化每个所属的成员对象。  
**【题解提示】**   
1.Map接口的实现子类有HashMap等等；  
2.getter和setter方法最好都写上；  

#### 2. 重写toString方法
```java
public String toString();
```
**【要求解读】**  
无  
**【题解提示】**  
按照题目要求书写即可。    

#### 3. addCourse方法
```java
public String addCourse(Course course);
```
**【要求解读】**  
进行一系列检查，询问最终的返回结果字符串。  
**【题解提示】**  
1. 时间段有无安排课程：containsKey方法；  
2. Type是否相同：拿出Type做equals判断或者==判断；  
3. seat是否足够：seat与course的capacity做比较；  
4. 添加课程：put方法；  

#### 4. deleteCourse方法
```java
public boolean deleteCourse(Course course);
```
**【要求解读】**  
检查当前schedule内有没有指定课程，有则进行移除操作并返回true；否则返回false。  
**【题解提示】**  
Map类的remove(key, value)方法中可以判断(value, key)键值对是否存在并进行移除操作。存在则移除并返回true，否则返回false。  

#### 5. getCourse方法
```java
public Course getCourse(CourseTime courseTime);
```
**【要求解读】**
根据传入的CourseTime返回对应的course，如果不存在则返回null。
**【题解提示】**  
Map类的get方法可以返回key值对应的value值，如果不存在key值则返回null。  

#### 6. printSchedule方法
```java
public String printSchedule();
```
**【要求解读】**  
无  
**【题解提示】**  
按照题目要求书写即可。  
