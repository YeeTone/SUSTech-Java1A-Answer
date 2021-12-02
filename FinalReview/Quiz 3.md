# CS102A Quiz 3
30 minutes, 20 questions, 5 points each.

##### Q1: Which of the following is/are NOT legal identifier(s) (variable/method/class names) in Java?

A. WhatALongClassNameYouHave
B. \_what\_a\_variable            
C. 2variable              
D. \_2__             
E. \$another\$var              

##### Q2: Write the value of the variable ```intResult```, given:

```java
int a = 3, b = 2, c = 10, intResult;
intResult = c % a*b;
```

##### Q3: Write what will be printed out when the following code executes.

```java
int i= 1;
switch(i){
    case 0: System.out.printIn("zero");break;
    case 1: System.out.printIn("one");
    case 2: System.out.println("two");
    default: System.out.println("default");
}
```

##### Q4: Which of the following lines is/are the java codes can be compiled without warnings or errors?

A. ```float f = 1.3;```          
B. ```char c = "a";```             
C. ```byte b = 257;```            
D. ```boolean b = null;```           
E. ```int i = -114514;```            
F. ```double d = (int) 1919810;```

##### Q5: Given:
```java
int a = 3, b = 5, c = 4, d = 6; 
```
Write the value of the following Java expression.
```java
!(a < b) || c > d
```

##### Q6: Write the output from this code fragment.
```java
int x = 1, y = 8, z = 5; 
if(x < 7){ 
if(y < 5){ System.out.println("message one"); }
else { System.out.println("message two"); } 
}else if(z > 5){ System.out.println("message three"); }
else{ System.out.println("message four"); }
```

##### Q7: Which one best describes overloaded methods?
A. Methods of subclasses inherited from superClass, but the implementation is redefined            
B. Methods that takes a long time to run           
C. Methods with the same name, but different parameter lists          
D. Methods with different return values         

##### Q8: Which of the following is a reason to enforce access to instance variables via getters and setters?
A. Encrustation             
B. Encapsulation            
C. Limitation          
D. Aggregation            

##### Q9: Write the initial values of the variables tracks and time.

```java
public class Test{ 
    private int tracks; 
    public void mark(int whichTrack){ double time; System.out.println(whichTrack);} 
}
```

##### Q10: Given the classes:
```java
class AA{
    double doSomething(int c, int d){
        //some codes ...
    }
    //Location A
}

class BB extends AA{
//Location B
}
```
Which is an example of method overriding?

A. ```double doSomething(int c, int d)``` at Location A            
B. ```double doSomething(int c, int d)``` at Location B           
C. ```double doSomething(double a)``` at Location A            
D. ```double doSomething(float a)``` at Location B           

##### Q11: What would be a valid constructor code for the following class?
```java
public class Test{
}
```

A. ```Test Test(){}```          
B. ```Test(){}```          
C. ```super(){}```          
D. ```void Test(){}```           

##### Q12: Which statement(s) is/are true about overriding and overloading?

A. Overriding is another term for overloading        
B. Overloading only can be done within an inheritance structure         
C. Overriding only can be done within an inheritance structure          
D. You can override a constructor       

##### Q13: Which statement(s) is/are false?

A. You can invoke a nonstatic method from within a static method          
B. You can invoke a nonstatic method from within a non-static method          
C. You can invoke a static method from within a static method         
D. You can invoke a static method from within a non-static method             
E. None of the above

##### Q14: Write the value of ```x[3]``` after execution of this code fragment.
```java
int x[] = {3,6,4,10};
x[3] -= x[1] % x[2];
```

##### Q15: Write the output of the following code.
```java
double sum = 0.0; 
double b[3] = {2.0, 1.0, 3.0}; 
for(int k = 0; k < b.length - 1; k++){ sum += b[k];} 
System.out.println(sum);
```

##### Q16: Write the value of the variable x after execution of the code fragment below.
```java
int b[] = {4,6,7};
int x = ++b[1]+b[2]++;
```

##### Q17: Write the output of running the following code.
```java
public class Test{ 
    public static void main(String[] args){ 
        int b = 5, ar[] = {5, 4, 3, 2}; 
        double val = myMethod(b, ar); 
        System.out.println(val + "," + ar[2] + "," + b); 
    } 
    public static double myMethod(double b, int arr[]){ 
        b -= 1.0; 
        arr[2] = (int)(arr[1] * b);
        arr = {2, 0, 2, 1}; 
        return b; 
    } 
}
```

##### Q18: Which of following(s) is/are false about constructors?

A. For some classes in Java, they do not have constructors          
B. Constructors are there by default, even if you don't see any constructors in the class          
C. Constructors can be overloaded         
D. All Constructors are public            

##### Q19: What statement(s) is/are to describe ```class B``` being a subclass (child) of ```class A```?

A. class B extend A          
B. class B implement A           
C. class A extend B         
D. class A implement B         
E. None of above           

##### Q20: What is/are the difference between a while loop and a do-while loop?

A. While loop tests condition after execution. Do-While loop tests condition before execution          
B. While loop tests condition before execution. Do-While loop tests condition after execution       
C. While loop is guaranteed to run at least once. Do-While is not guaranteed to run at least once       
D. While loop is not guaranteed to run at least once. Do-While is guaranteed to run at least once        