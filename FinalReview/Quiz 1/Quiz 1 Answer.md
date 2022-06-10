# Quiz 1 Answer

I. True or False

1-5 TFTFT

II.

1. 136051865
2. boolean; byte, short, int, long; float, double; char
3. B
4. C
5. `3 9`

III.

1. code:

```java
public static void BMICalculator(double weight, double height) {
    double bmi = weight/(height*height);
    if(0 < bmi && bmi < 18.5){
        System.out.println("thin");
    }else if(18.5 <= bmi && bmi <= 22.9){
        System.out.println("normal");
    }else if(bmi > 22.9){
        System.out.println("overweight");
    }
}
```

2. two solutions:

```java
public static int factorial(int n) {
    if(n <= 1){
        return 1;
    }else {
        return n*factorial(n-1);
    } 
}
```


```java
public static int factorial(int n) {
    int result = 1;
    for(int i = 1; i <= n; i++){
        result *= i;
    }
    return result;
}
```
