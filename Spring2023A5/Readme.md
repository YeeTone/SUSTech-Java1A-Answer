# Explanation to A5

这个第五次作业，是这几个学期以来第五次作业中，出题最简单的一次。

## A5Q1 GraphicObject

提示1：`GraphicObject`中的`id`需要保持唯一，可以使用一个`static`变量的方法来标识当前的`id`。

比如说：

```java
private int id;
private static int idcnt;

public GraphicObject(...){
    ...
   	this.id = idcnt;
    idcnt++;
}
```

提示2：`GraphicObject`的子类，需要有对应参数的构造方法，以避免编译错误。

```java
public Cone(ObjectColor oc, double radius, double length) {
    ...
}

public Cuboid(ObjectColor oc, double x, double y, double z) {
   	...
}

public Sphere(ObjectColor oc, double radius) {
   	...
}
```

提示3：表面积和体积的计算方法：

| subClass | surfaceMeanSize      | volume                                                  |
| -------- | -------------------- | ------------------------------------------------------- |
| Cone     | $\pi r^{2}+ \pi r l$ | $\frac{\pi r^{2}\sqrt{l^{2}-r^{2}}}{3}$（勾股定理求高） |
| Cuboid   | $2(xy+yz+xz)$        | $xyz$                                                   |
| Sphere   | $4 \pi r^{2}$        | $\frac{4\pi r^{3}}{3}$                                  |



## A5Q2 ObjectCollection

题目里如果需要按照**addition order**排序，那么可以直接用之前的自增id作为排序的基准。

解释：能够这么做的原因是对象是在`addObject`的时候创建的，那么后加入的对象一定`id`比较大。