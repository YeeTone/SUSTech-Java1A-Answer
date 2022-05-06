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
