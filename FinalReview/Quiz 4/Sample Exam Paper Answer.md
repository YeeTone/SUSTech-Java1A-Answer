# Sample Exam Paper Answer

## Part I.
1-5 DABCD

6-10 ACACC

11-12 CD

## Part II.
1. BDE
2. ACDE
3. BCDE
4. BDE

## Part III.
1-5 FFTTT

6-10 FTFFT

11 T

## Part IV.
1-5 CHNIK

6-10 LBJED

## Part V.

1. output:

```
[Physics, Logic, Algbra, Programming]
```

2. output:

```
:-) Hi 2020
```

3. output:
```
south
```

4. output:
```
[1, 3]
[2, 1, 8]
[10]
[]
```

## Part VI.

1. code segment:

```java
public static double distance(Point p1, Point p2){
    double xDifference = p1.x - p2.x, yDifference = p1.y - p2.y, zDifference = p1.z - p2.z;
    return Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2) + Math.pow(zDifference, 2));
}
```

2. code segment 2.1:

```java
public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the number to be checked: ");
    int n = sc.nextInt();
    //...

}
```

code segment 2.2:

```java
int sum = 0;
for(int i = 1; i <n ; i++){
    if(n % i == 0){
        sum +=i;
    }
}

return sum == n;
```

3. code segment:

```java
public static double[][] multiply(double[][] a, double[][] b) {
    double[][] matrix = new double[a.length][b[0].length];
    for(int i = 0;i < a.length; i++){
        for(int j = 0; j < a[0].length; j++){
            for(int k =0; k < b[0].length; k++){
                matrix[i][k] += matrix[i][j]*matrix[j][k];
            }
        }
    }

    return matrix;

}
```