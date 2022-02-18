### 1.5 try-catch-finally异常处理(Optional)

这个是Java1A课程中最后一周的内容，考试也不考，但是我认为很有必要提前就给同学们讲讲，让同学们拥有一个面对程序报错时的大杀器！

异常的定义：
```
异常是程序中的一些错误，但并不是所有的错误都是异常，并且错误有时候是可以避免的。
```

运行时产生的错误统称为```Throwable```，其下分两类：       、

```Error```表示不是非法操作，而是程序本身出现了错误，是属于程序没法处理的，比如```StackOverFlowError```；        

```Exception```表示用户或者程序进行了某些非法操作，使得程序出现问题，可以得到处理，比如说```NullPointerException```；

```Exception```又下分两种，分别是运行时异常和非运行时异常：

- 运行时异常编译时没法检测，是运行的时候扔出来的，比如说```ArrayIndexOutOfBoundException```；
- 非运行时异常编译时可以检测，要求程序必须完成对异常的显式处理，比如说```IOException```；

![image](https://user-images.githubusercontent.com/64548919/136687085-45540a57-b80d-4a99-a580-dbe1c69302fa.png)


Java1A课程中常见的异常有以下几种，表示程序进行了不合法的操作：
- ArithmeticException
- IllegalArgumentException
- NumberFormatException
- InputMismatchException
- StringIndexOutOfBoundException
- ArrayIndexOutOfBoundException
- ClassCastException
- NullPointerException
- IndexOutOfBoundException
- FileNotFoundException
- ...

异常的机制的存在，让程序能够在出现非法错误的时候及时退出而不进行非法操作，也可以让程序员定位程序的位置；      

而try-catch-finally机制的存在，则让程序员面对异常处变不惊，能够在出现异常的时候及时挽救而不立即退出。

异常和try-catch-finally机制是程序员在面对非法操作或者请求时的必胜法宝，而不会面对满屏的报红不知所措，因此我认为很有必要提前给同学们讲解这方面的内容！

如何抛出一个异常？
```java
throw new Exception();
```

如何捕获一个异常并进行一些操作？
```java
try{
     int[] a3 = new int[3];
     System.out.println(a3[3]);
catch(ArrayIndexOutOfException e){
     System.out.println("ArrayIndex is invalid!");
}
```

如何检测多种异常？
```java
try{
   // 程序代码
}catch(异常类型1 异常的变量名1){
  // 程序代码
}catch(异常类型2 异常的变量名2){
  // 程序代码
}catch(异常类型3 异常的变量名3){
  // 程序代码
}
```

finally关键字的作用？

无论try块中有无异常，都会执行finally语句块，主要用于资源的释放与关闭：

```java
Scanner sc = new Scanner(System.in);
//如果输入a，那么程序将结束，然后资源会通过finally语句块释放
try{
   int x = sc.nextInt();
}catch (InputMismatchException e){
   System.out.println("Input is not valid!");
}
finally {
   sc.close();
}
```
