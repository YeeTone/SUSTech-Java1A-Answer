# Explanation to A3

> Chinese version: Yitong WANG
>
> English version: ChatGPT plus

## A3Q1 Mine Sweeper

Requirement: Given the size $n$ of the Minesweeper board and the distribution of the mines inside, you need to process one query to inquire about the actual situation after clicking on a specific position $(x, y)$. If it is a mine, output -1, otherwise output the number of mines around it.

Difficulty: Easy

Approach: This is a classic 2-D array problem and a simplified version of the JavaA project in 2021. First, check if $(x,y)$ is a mine. If $(x,y)$ is a mine, output -1 and end the program. Otherwise, explore the eight surrounding cells and count the number of mines around them.

Note: When exploring the eight cells, it is necessary to do boundary checking to avoid `ArrayIndexOutOfBoundException`.

## A3Q2 Safe Areas in RA2 Battlefield

This is a problem I designed because I am a loyal fan of WM=HBK08 and =HY=Moon3. It is generally believed that Iraq and France are the strongest countries in this game, so I designed a question using Iraq and France.

Requirement: Given the battlefield size $n$, the number of desolators $m$, and the number of grand cannons $k$. The Iraqi desolators  will irritate the surrounding area when they are deploying on the ground; the grand cannons of France can launch long-range, high-damage attacks but cannot attack units close to them. The attack range is shown in the figures (the figures are not shown here). The positions of the Iraqi desolators and the French grand cannons are also not safe. Inquire about how many safe areas there are in the $n\times n$ battlefield area, which are not contaminated by the desolators of Iraq or attacked by the cannons of France.

Difficulty: Medium

Approach: Use a boolean two-dimensional array of $n\times n$ to record whether a cell is unsafe. When entering the positions of desolators and grand cannons, enumerate their attack ranges one by one and perform boundary checks to mark all legal attack positions as unsafe. After processing, traverse the two-dimensional array and count the number of remaining safe areas.

Note:

1. There is a constraint in the question, $-50 \le x_i,y_i,o_i,p_i \le 150$. This information means that the desolators and grand cannons may locate outside the battlefield, but they may still attack some blocks in the $n\times n$ battlefield area, so boundary checking is particularly important.
2. The methods for handling desolators and grand cannons in this question are the same, and the direction array can be used to simplify the code, such as:

```java
private static final int[][] DESOLATOR = {
    {-1, -1},
    {-1, 0},
    {-1, 1},
    {0, -1},
    {0, 1},
    {1, -1},
    {1, 0},
    {1, 1}
};
```

3. The question **does NOT mention that desolators and grand cannons cannot be in the same cell**, so you should not add unnecessary constraints based on the background.

## A3Q3 Hatsune Miku's Puzzle

This is also a question I designed. In short, it's just a bit tricky but not difficult.

Requirement: Given the size of the target matrix $m\times n$ and the string $s$ to be decompressed. First, decompress the string $s$ according to the requirements of the question, and then arrange the decompressed string in a counterclockwise spiral matrix of size $m\times n$, starting from the bottom left corner.

Difficulty: Hard

Approach: I don't know how to explain the approach to this problem, because the approach is already written in the question. I think it can be divided into three steps: First, implement a method to decompress the string, then implement a method to arrange the spiral matrix, and finally output the arranged spiral matrix, for example:

```java
public static String decompress(String s){
    //...
}


public static char[][] organizeSpiralMatrix(int m, int n, String decompressed){
    //...
}

public static void printMatrix(char[][] matrix){
    //...
}
```

Note:

1. When decompressing the string, after getting a letter, how can we know know how many times it should be repeated? How to identify the left and right boundaries of this digit string?

2. There are two conditions for rotating the spiral matrix: 

   2.1 Accessing Out of Array Boundaries 

   2.2 The content of the target position has already been filled

3. When arranging the spiral matrix, can we also use the direction array to simplify the code?

## A3Q4 Trivial Tetris Game

Requirement: Given the size of the game board of Tetris, `w` and `h`. Seven types of blocks fall freely in the order of input format `type l`, where `l` is the position of the lowest left element. During the game, complete rows should be eliminated first. If the Tetris block still goes beyond the upper boundary after line elimination, the game should terminate immediately. When all blocks are used or the game ends prematurely, the final game board should be outputted.

Difficulty: Medium

Solution: The solution of this problem consists of the following steps:

- Construct a game board of size $(h+3)×w$, where the top three rows are used as a `buffer area` to check whether the blocks will go beyond the boundary.
- Determine the specific vertical coordinate of the falling block. The method is to check whether all four cells are 0 based on the **boundary check**. If not, the falling position is on the line right above it; if all four cells are 0 until the last position, the falling position is on the last line.
- After determining the vertical coordinate, fill the falling position with 1. Then check the entire board to see if any complete rows can be eliminated. If successful, move all elements above them down one row **(not free falling according to gravity!)**. Keep eliminating complete rows until there is no more.
- After elimination, check for out-of-boundary conditions. If the `buffer area` goes beyond the boundary, end the input loop immediately.
- Output the game board information after the game ends. Do **NOT** output the elements in the `buffer area`.

Note: There are many details to pay attention to in this problem.

- Detail 1: The `buffer area` must be used. It is very difficult to solve the problem without it.
- Detail 2: The vertical coordinate of the falling block cannot be determined by checking whether only four positions are all 0. For example, placing `L 0` in the following situation will not work.

```
000000
000000
011000
011101
001110
111100
```

- Detail 3: After elimination, the top element should be 0. This seems simple, but it is also a cause of WA that I have experienced.

```
010010
111111
010111
110111
011101
101110
```

After eliminating the second top row, it should be:

```
000000
010010
010111
110111
011101
101110
```

- Detail 4: The coordinate axis of this problem is different from the traditional view. (0,0) is at the upper left corner.
- Detail 5: Is enumerating the seven types of blocks too cumbersome? Using the offset direction array relative to the lower left corner can greatly simplify the code, such as:

```java
private static final int[][] L = {
    {0, 0},
    {0, 1},
    {-1, 0},
    {-2, 0}
};
private static final int[][] S = {
    {0, 0},
    {0, 1},
    {-1, 1},
    {-1, 2}
};
```