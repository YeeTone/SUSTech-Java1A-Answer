# CS102 Quiz 1

Produced by Tloops(Qiushi NIE, thanks a lot!)						

Total Score: 30

## Part 1 - True or False(2 points each)

1. Java programs are executed by something called **JVM**
2. The loop body of **for** loop is executed at least once as it tests the loop-continuation condition after the the loop every time
3. Java allows several methods of the same name to be declared in a class, as long as the methods have different sets of parameters
4. An array can store many **different** types of values
5. **Instance methods** in a class can also be overloaded

## Part 2 - Short Answer Questions(2 points each)

1. Write the **output** of the following program:

   ```java
   for(int i = 1, j = 0; i < 10; i++) {
       j += i;
       j %= 10;
       System.out.print(j);
   }
   ```
   
2. What are the 8 primitive types in Java? Please write them down.

   

3. What will be the value of **num** after executing the following statements?

   ```java
   int num = 5;
   num += 12.8;
   num = num / 9;
   ```

   A. 9			B. 1			C. 2			D. 1.97777778			E. Runtime Error

4. Class M is defined as follows: 

   ```java
   class M {  
   	public M () { System.out.print( ":-) " ); }
   	public M (int n)  { this();  System.out.println( "Hi " + n ); }
   }  
   ```

   What will the following instruction result in?

   ```java
   M m = new M( 2019 );
   ```

   A. Compile error	B. Runtime Error	C. print: **:-) Hi 2019**	D. print: **Hi 2019**

5. Write the **output** of the following program:

   ```java
   public class Main {
       public static void main(String[] args){
           int[] a = {1, 2, 3}; int[] b = {9, 8, 7};
           swap(a, b);
           System.out.print(a[0] + " " + b[0]);
       }
       public static void swap(int[] a, int[] b){
           a[0]++;
           int[] temp = a; a = b; b = temp;
           b[0]++;
       }
   }
   ```
   

## Part 3 - Programming (5 points each)

1. Snow is always worried about her weight as many girls do. She wants to calculate her BMI to check if she is overweight and she asks you for help. Please implement a method which gets two parameters: **weight**(double) and **height**(double), and prints **thin**, **normal** or **overweight** according to the BMI calculated by the two parameters. The way to calculate **BMI** is given below: 
   $$
   BMI = \frac{weight}{height×height}
   $$
   **Range --> State**: (0, 18.5) --> **thin**	[18.5, 22.9] --> **normal**	(22.9, infinity) --> **overweight**
   
   ```java
   public static void BMICalculator(double weight, double height) {
       
       
       
       
   }
   ```
   
2. Implement a method which returns the factorial(阶乘) of a input integer `n`:

   ```java
   public static int factorial(int n) {
       
       
       
       
   }
   ```

## Part 4 - Additional Question (0 point)

Please write something you want to say to me! 

You can tell me what you have learned in my course, or something that I can improve when teaching.
