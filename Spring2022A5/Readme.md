# Explanation to A5：国际象棋

## Q1: ChessboardPoint [10 Points]

1. ChessColor枚举按照题面中要求：

```java
public enum ChessColor {
    BLACK, WHITE, NONE; 
}
```

2. ChessboardPoint中，**x和y是从0开始计数的，左上角为(0, 0)！** （非常重要，我被这点坑了一个小时）
3. `offset`方法，注意如果出现了越界，那么要返回`null`！
4. 提交文件：
- ChessColor.java
- ChessboardPoint.java

## Q2: ChessComponent [10 Points]

1. 这个类是一个抽象类！

```java
public abstract class ChessComponent {
    // other codes are omitted.
}
```

2. 定义无参构造方法：

```java
public ChessComponent(){
    // other codes are omitted.
}
```

可以什么都不做，也可以写一些自定义的赋值语句。

3. **建议**自定义一个构造方法和相关的field：
```java
protected ChessGame currentChessGame;
public ChessComponent(ChessboardPoint source, ChessColor chessColor, char name, ChessGame currentChessGame){
    // other codes are omitted.
}
```
这里定义前三个参数是为了初始化里面的属性；定义一个ChessGame的属性是为了让棋子获取当前的棋盘状态！

4. 提交文件：
- ChessColor.java
- ChessboardPoint.java
- ChessComponent.java

## Q3: ChessGame1 [30 Points]

1. 要定义ChessComponent的具体实现子类，如下：
- KingChessComponent
- QueenChessComponent
- RookChessComponent
- BishopChessComponent
- KnightChessComponent
- PawnChessComponent
- EmptySlotComponent

2. 定义ChessGame的接口：
```java
public interface ChessGame {
    // other codes are omitted.
}
```

3. 定义ChessGame的具体实现ConcreteChessGame类：

```java
public class ConcreteChessGame implements ChessGame{
    // other codes are omitted.
}
```

4. loadChessGame和getChessboardGraph时注意符号的大小写！
5. getCapturedChess时先遍历整个棋盘统计，然后与满状态的棋子个数做减法就能得到答案。
6. getCapturedChess时结尾需要加一个`\n`
7. 提交文件：
- ChessColor.java
- ChessboardPoint.java
- ChessComponent.java
- ChessGame.java
- ConcreteChessGame.java
- EmptySlotComponent.java
- PawnChessComponent.java
- BishopChessComponent.java
- KnightChessComponent.java
- RookChessComponent.java
- QueenChessComponent.java
- KingChessComponent.java

## Q4: ChessGame2 [50 points]

1. 棋子移动规则需要在具体子类中实现。
- 无需考虑王是否会被将军的问题
- 无需考虑王车易位，吃过路兵和兵升变
- 兵在首次移动的时候，可以一次走1格或者2格
- 兵可以斜吃
- 象，车，后，王这些棋子都是直线型移动的，因此需要在一个方向上判断是否有棋子阻挡。如果有棋子阻挡就不能继续在该方向上延伸
- 马没有什么蹩脚规则
- 目的地不能有本方棋子，可以有对方棋子或者是空棋子
- 注意边界检查
- **建议** 使用方向数组来将象车后王的直线移动方法抽象化：
```java
protected void directionMove(int[][] directions, List<ChessboardPoint> result, int bounds){
    //directions: 方向数组，如车的方向数组是：{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    //result：存放结果的列表
    //bounds：可移动的最大步长，其中象，车，后都是7（因为最多移动7格），而王是1（王只能移动1格）
}
```

2. moveChess移动棋子方法

- **首先检查是否source的棋子颜色与当前行棋方相同**
- 相同后检查target位置是否在source的可移动列表里
- 如果合法了，那么就移动棋子，并且交换行棋方
- 移动棋子的时候，如果目的地有对手棋子，那么需要移除对手的棋子（吃子）

3. getCanMovePoints方法需要注意结果要按先x后y的次序做排序

4. 提交文件：
- ChessColor.java
- ChessboardPoint.java
- ChessComponent.java
- ChessGame.java
- ConcreteChessGame.java
- EmptySlotComponent.java
- PawnChessComponent.java
- BishopChessComponent.java
- KnightChessComponent.java
- RookChessComponent.java
- QueenChessComponent.java
- KingChessComponent.java
