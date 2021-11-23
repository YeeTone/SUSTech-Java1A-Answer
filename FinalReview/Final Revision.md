# CS102A Final Revision

## Multiple Choice Problems(4 points each, 4×25 = 100)

### Q1-Q3 Read the following code: 
```java
class A1 { public int x; private int y; protected int z;}

class A2 extends A1 { protected int a; private int b;}

class A3 extends A2 { private int q;}
```

Q1: Which of the following lists of instance data are accessible in class A2?       
A. x, y, z, a, b     
B. x, y, z, a      
C. x, z, a, b      
D. z, a, b     
E. a, b      

Q2: Which of the following lists of instance data are accessible in class A3?       
A. x, y, z, a, b, q      
B. a, b, q      
C. a, q        
D. x, z, a, q      
E. x, a,       

Q3: Which of the following is true with the use of instance data y of class A1?       
A. it is accessible in A1, A2 and A3     
B. it is accessible in A1 and A2      
C. it is accessible only in A1      
D. it is accessible only in A3        
E. it is not accessible to any of the three classes

### Q4 The instruction super(); does which of the following?       
A. calls the method super as defined in the current class       
B. calls the method super as defined in the current class’ parent class      
C. calls the method super as defined in java.lang      
D. calls the constructor as defined in the current class        
E. calls the constructor as defined in the current class’ parent class

### Q5 Aside from permitting inheritance, the visibility modifier protected is also used to?

A. permit access to the protected item by any class defined in the same package        
B. permit access to the protected item by any static class        
C. permit access to the protected item by any parent class       
D. ensure that the class can not throw a NullPointerException         
E. define abstract elements of an interface

### Q6 All classes in Java are directly or indirectly subclasses of the ( ) class.
A. Wrapper
B. String
C. Reference
D. this
E. Object

### Q7-Q9 Read the followig code:
```java
Person p = new Person(...);
// assignment 1 
int m1 = p.getMoney();
p = new Student(...);
// assignment 2 
int m2 = p.getMoney();
if (m2 < 100000)
p = new Employee(...);
else if (m1 > 50000)
p = new Retired(...);
// assignment 3
int m3 = p.getMoney();
```
Q7: The reference to getMoney() in assignment 1 is to the class:     
A. Person      
B. Student     
C. Employee     
D. Retired     
E. none of the above, this cannot be
determined by examining the code

Q8:  The reference to getMoney() in assignment 2 is to the class:     
A. Person      
B. Student     
C. Employee     
D. Retired     
E. none of the above, this cannot be
determined by examining the code

Q9:  The reference to getMoney() in assignment 3 is to the class:     
A. Person      
B. Student     
C. Employee     
D. Retired     
E. none of the above, this cannot be
determined by examining the code

### Q10 The relationship between a child (sub) class and a parent (super) class is referred to as a(n) ( ) relationship.

A. has­-a
B. is­-a
C. was­-a
D. instance­-of

### Q11-Q12 Read the followig code:
```java
public class Test { 
    private int x; 
    public test (int newValue) { x = newValue;}
}
```
Q11: Which of the following is true about the class Test? 
A. it has no parent class       
B. it’s parent class is Object       
C. it’s parent class is Java       
D. it can not be extended       
E. it has a default child called Object       

Q12: If q1 and q2 are objects of Test class, then q1.equals(q2)

A. is a syntax error since equals is not defined in the Test class          
B. is true if q1 and q2 both store the same value of x       
C.is true if q1 and q2 reference the same Test object     
D. is never true        
E. throws a NullPointerException         

### Q13 Read the following codes:
```java
for (int j = 0; j < list.length; j++)
    if (list[j] < temp)
        c++;
```
What does the code do? Assume list is an initialized array of int values,
temp is some previously initialized int value, and c is an int initialized to 0.

A. It finds the smallest value and stores it in temp       
B. It finds the largest value and stores it in temp       
C. It counts the number of elements equal to the smallest value in list       
D. It counts the number of elements in list that are less than temp       
E. It sorts the values in list to be in ascending order      

### Q14-Q16 Read the following codes:
```java
try { 
    BufferedReader f = new BufferedReader(new FileReader(filename)); //i1
    int x = Integer.parseInt(f.readLine()); //i2
    a[++i] = (double) (1 / x); //i3
} 
catch (FileNotFoundException ex) {...} // e1
catch (NumberFormatException ex) {...} // e2
catch (ArithmeticException ex) {...} // e3
catch (ArrayIndexOutOfBounds ex) {...} // e4
catch (IOException ex) {...} // e5
```

Q14: An exception raised by the instruction in i1 would be caught by the catch statement labeled      
A. e1;     
B. e2;        
C. e5;         
D. e1 or e5;      
E. e1, e4 or e5

Q15: An exception raised by the instruction in i2 would be caught by the catch statement labeled      
A. e1;       
B. e2;        
C. e3;         
D. e5;             
E. e2 or e5

Q16: An exception raised by the instruction in i3 would be caught by the catch statement labeled    
A. e2;        
B. e3;            
C. e4;        
D. e3 or e4;          
E. e2, e3 or e4

### Q17: Consider the array declaration and instantiation: int[] arr = new int[5];Which of the following is true about arr?
A. It stores 5 elements with legal indices between 1 and 5        
B. It stores 5 elements with legal indices between 0 and 4        
C. It stores 4 elements with legal indices between 1 and 4         
D. It stores 6 elements with legal indices between 0 and 5       
E. It stores 5 elements with legal indices between 0 and 5           


### Q18: Which of the following messages passed to the string str could throw a StringIndexOutOfBoundsException?
A. str.length()         
B. str.charAt(2);         
C. str.replace('a', 'A');         
D. str.equals(str);          
E. any of the above could throw aStringIndexOutOfBoundsException

### Q19: Assume x and y are String variables with x = "Hello" and y = null. If the operation y = "Hello"; is performed, then the result of (x == y) is:
A. true           
B. false           
C. x and y becoming aliases            
D. x being set to the value null          
E. a run­time error         

### Q20-Q23: Consider an array:
{9, 4, 12 ,2, 6, 8, 18}

Q20: What is returned by values[1]?

Q21: What is returned by values.length?

Q22: Which of the following loops would adequately add 1 to each element stored in values?        
A. for (int j=1; j < values.length ; j++) values[j]++;            
B. for (int j=0; j < values.length ; j++) values[j]++;             
C. for (int j=0; j <= values.length; j++) values[j]++;             
D. for (int j=0; j < values.length–1; j++) values[j]++;             
E. for (int j=1; j < values.length–1; j++) values[j]++;              

Q23: The statement System.out.println(values[7]); will          
A. output 7           
B. output 18           
C. output nothing          
D. cause an ArrayOutOfBoundsException to be thrown       
E. cause a syntax error          

### Q24-Q25: Read the following codes:
```java
public class Aclass { private int x; protected int y; 
    public Aclass (int a, int b) {x = a;y = b;}
    public int addEm () {return x + y;}
    public String toString () {return "" + x + " " + y;}
}
```
Consider that you want to extend AClass to BClass. BClass will have a third int instance data, z. 

Q24: Write the code that would best define BClass’ constructor.

Q25: Write the code that would best define BClass’ toString method.
