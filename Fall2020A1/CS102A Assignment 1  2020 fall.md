# CS102A Assignment 1  2020 fall

<center>Translator: Isaac Chen</center>
<center>Email: ccccym666@gmail.com</center>

## 1.  Credit Calculator

Renjie Liu is a fresh man in SUSTech, which is the eighth university in Chinese mainland. In the very beginning of his university life, he is asked to choose some courses for the next semester where each course has a credit $c(0 <= c <= 10)$. There are $n(0 < n < 10^5)$ courses he wants to join in but the total credit is limited by TAO. After he chooses some courses, Renjie would like to check whether the total credit is satisfied. Given a list of course credit, please tell Renjie how many credits he has chosen.

**Input**: The first line is the course number $n$ Renjie has chosen, followed by $n$ integer $c_1, c_2...c_n$, which represents the credit for each course.

**Output**: An integer representing the total credit.

**Sample Input**:

```
5
5 4 3 2 1
```

**Sample Output**

```
15
```



## 2. I AM TIRED!!!!!!!

Lishuang Wang is very very very very busy this semester. He always feels that he is so tired, but he do not know why. He want to find out the reason that cause tiredness. If there is one day that the course number $c(0 <= c < 6)$ of Lishuang is not less than 4, Lishuang is tired. Given the course number for each day of the week of Lishuang, please tell Lishuang how many days that Lishuang would feel tired.

**Input**: Five integer $c_1, c_2... c_5$ representing the course number from Monday to Friday.

**Output**: The days number that Lishuang would feel tires.

**Sample Input**:

```
5 4 3 2 1
```

**Sample Output**

```
2
```



## 3. Ready to pick!

The result of Yitong's course schedule was released but he found that he missed several courses. So that unfortunately, he has to compete for the missing courses. The course could be picked after 13:00 each day and Yitong is ready to pick the course up. Given current hour $h(0 <= h <= 23)$ and minute $m(0 <=m <= 59)$, please tell Yitong the time duration between the current time and the next competition time.

**Notes**: It is guaranteed that the time duration between the current time and the next competition time is less than 24 hours.

If the time duration is less than 1 hour, the hours could be ignored. 

**Input**: Two integer representing $h$ and $m$

**Output**: One or two integer representing the time duration.

**Sample Input1**:

```
12 59
```

**Sample Output1**

```
1
```

**Sample Input2**:

```
11 59
```

**Sample Output2**

```
1 1
```



## 4. I AM POOR

Jiaxi is going to buy textbooks when the course is determined. There are a large number of textbook, with price of each textbook being $p(0 <= p < =10^5)$. Jiaxi only has $n(0 <=n <= 10^7)$ yuan but he is asked to buy $m (0 <=m <= 10^5)$ books. Given the price of each book, please tell Jiaxi whether he could buy so many books.

**Input**: The first line contains two integer $n$ and $m$.

The second line contains $m$ numbers, representing the price of each book

**Output**: "Yes" if Jiaxi has enough money to but so many books. "No" else.

**Sample Input1**:

```
300 6
10.1 20.2 30.3 40.4 50.5 60.6
```

**Sample Output1**

```
Yes
```



## 5. Currency exchange

It is only allowed to use SUSTech currency (SC) in the bookshop so that Yuezhen has to exchange his money to SC. There are eight kinds of SC could be exchanged, named by $SC_1, SC_2....SC_8 $. SC is special and the exchange rules are as follows: 

1. 1 $SC_1$ equals 1 yuan. 
2. 1 $SC_2$ equals 7 yuan. 
3. 1 $SC_3$ equals 49 yuan. 
4. 1 $SC_4$ equals 343 yuan. 
5. 1 $SC_5$ equals 2401 yuan. 
6. 1 $SC_6$ equals 16807 yuan. 
7. 1 $SC_7$ equals 117649 yuan. 
8. 1 $SC_8$ equals 823543 yuan. 

Yuezhen has $x(0<=0<=10^6)$ yuan. Because SC are all coins, they are very heavy. Yuezhen hopes that as few coins as possible. Given the money that Yuezhen has, please output the number of each SC coins in such format: $SC_8 SC_7 SC_6 SC_5 SC_4SC_3SC_2SC_1$. 

**Input**: An integer $x$ representing Yuezhen's money

**Output**: The SC coins with the format satisfied.

**Sample Input1**:

```
100
```

**Sample Output1**:

```
00000202
```

**Sample 1 Explanation**:

```
100 = 2 * 49 + 2 * 1 => 4 coins
100 = 14 * 7 + 2 * 1 => 16 coins
...
For all of the possible proposal, it is easy to find that 4 coins are the least coins. Thus the proposal is chosen.
In such plan, SC8 is 0, SC7 is 0 .... SC4 is 0, SC3 is 2, SC2 is 0, SC1 is 2. Thus the output is 00000202
```



## 6. School bus is too crowded!!!

Qiuqi found that she needed to attend the courses in both the Lychee Hills and the Teaching Building One. Supposed that the course in Teaching Building One is 1 and that in Lychee Hills is 2, we can regard Qiuqi's schedule in one day as an string like "12", which means that Qiuqi has one two course in such day with the first one in Teaching Building One and the second one in Lychee Hills. Qiuqi is too lazy to walk between Lychee Hills and Teaching Building One so that if she has to move between these two places, she would like to take the school bus. Supposed Qiuqi lives in Lychee Hills, please output how many times Qiuqi should take bus.

**Input**: The first line contains an integer $n(1<= n <= 5)$, representing how many days Qiuqi should attend the course. 

The second line contains $n$ string $s_1,...s_n(1 <= |s_i| <= 5)$, representing the schedule.

**Output**: An integer representing taking bus times.

**Sample Input1**:

```
3
1 222 12
```

**Sample Output1**:

```
4
```

**Sample 1 Explanation**:

```
In the first day, Qiuqi should start from Hychee Hills, taking bus to Teaching Building One. After class, she should go from Teaching Building One, taking bus back to domitory.
In the second day, the courses of Qiuqi are all in Hychee Hills, so that she is necessary to take bus.
In the third day, Qiuqi should start from Hychee Hills, taking bus to Teaching Building One. After the first couse, she has to take bus to attend the next course, which is in Hychee Hills.
Totally, she should take 4 buses.
```



## 7. Time traveler

The school calendar always uses the number of teaching week and the day in a week to represent the day. It is known that the days in one teaching week is not always 7 days but $x(1<= x <= 100)$ days, so that Yao Zhao is always confused what day is today. Supposed that 0001-01-01 is Monday. Given a confirmed date with year $y(1 <= y <= 99999)$, month $m(1 <=m <= 12)$ and day $d(1 <= d <= 31)$, please output an integer $k(1 <= k <= x)$  representing what day is for the given date.

**Note**: 

1. The calculation of leap year:
   1. **if** (*year* is not divisible by 4) **then** (it is a common year)
   2. **else if** (*year* is not divisible by 100) **then** (it is a leap year)
   3. **else if** (*year* is not divisible by 400) **then** (it is a common year)
   4. **else** (it is a leap year)
2. For convenience, do not consider the 10 days that were erased from October 5, 1582 to October 14, 1582.
3. It is guaranteed that the given date is valid.

**Input**: The first line contains an integer $x$, representing how many days in a week.

The second line contains three integer $y$, $m$, $d$, representing a confirmed date.

**Output**: An integer representing what day is for the given date.

**Sample Input1**:

```
14
0001 1 12
```

**Sample Output1**:

```
12
```

**Sample 1 Explanation**:

```
It is said that 0001-1-1 is the first day of a week.
The first integer 14 represents that there are 14 days in such a week.
So that 0001-01-12 represents the 12th day in the first week.
```



## 8. Digits Sort

Isaac Chen is often absent-minded in class. One day when he was in class, he found it interesting to sort his student ID digits descending. But he did not want to sort the digits number only in a descending way. Isaac has a lucky number. He wanted that his lucky number $l(0 <= l <= 9)$ is the very beginning digit in the sorted number if it is exactly in the student id. He was so excited but he was too lazy to sort other's student ID. Given a student ID $s(0 <= s <= 10^{9})$, please help Isaac to sort the Student ID.

**Note**:

1. It is guaranteed  that the given student ID does not have any leading zero.

**Input**: The first line contains an integer $l$, representing the lucky number.

The second line contains an integer $s$ representing the student ID

**Output**: Sorted student ID

**Sample Input1**:

```
5
11712225
```

**Sample Output1**:

```
57222111
```