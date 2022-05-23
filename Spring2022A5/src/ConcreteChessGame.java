import java.util.*;

public class ConcreteChessGame implements ChessGame {
    private static final int CHESSBOARD_SIZE = 8;

    private static final String BLANK = " ";
    private static final String SEPARATOR = "\n";

    private static final Map<Class<? extends ChessComponent>, Integer> fullValues = Map.of(
            KingChessComponent.class, KingChessComponent.FULL,
            QueenChessComponent.class, QueenChessComponent.FULL,
            RookChessComponent.class, RookChessComponent.FULL,
            KnightChessComponent.class, KnightChessComponent.FULL,
            BishopChessComponent.class, BishopChessComponent.FULL,
            PawnChessComponent.class, PawnChessComponent.FULL
    );

    private static final Map<Class<? extends ChessComponent>, Character> chessLowerCaseName = Map.of(
            KingChessComponent.class, KingChessComponent.NAME,
            QueenChessComponent.class, QueenChessComponent.NAME,
            RookChessComponent.class, RookChessComponent.NAME,
            KnightChessComponent.class, KnightChessComponent.NAME,
            BishopChessComponent.class, BishopChessComponent.NAME,
            PawnChessComponent.class, PawnChessComponent.NAME
    );

    private ChessComponent[][] chessComponents;

    private ChessColor currentPlayer;

    private ChessComponent generateChessComponent(int x, int y, char name) {
        ChessboardPoint point = new ChessboardPoint(x, y);

        ChessComponent chessComponent;

        char lower = Character.toLowerCase(name);
        switch (lower) {
            case KingChessComponent.NAME -> chessComponent = new KingChessComponent(point, ChessColor.NONE, name, this);
            case QueenChessComponent.NAME -> chessComponent = new QueenChessComponent(point, ChessColor.NONE, name, this);
            case RookChessComponent.NAME -> chessComponent = new RookChessComponent(point, ChessColor.NONE, name, this);
            case KnightChessComponent.NAME -> chessComponent = new KnightChessComponent(point, ChessColor.NONE, name, this);
            case BishopChessComponent.NAME -> chessComponent = new BishopChessComponent(point, ChessColor.NONE, name, this);
            case PawnChessComponent.NAME -> chessComponent = new PawnChessComponent(point, ChessColor.NONE, name, this);
            default -> chessComponent = new EmptySlotComponent(point, ChessColor.NONE, name, this);
        }

        if (Character.isLetter(name)) {
            chessComponent.setChessColor(Character.isUpperCase(name) ? ChessColor.BLACK : ChessColor.WHITE);
        }

        return chessComponent;
    }

    @Override
    public void loadChessGame(List<String> chessboard) {
        this.chessComponents = new ChessComponent[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
        for (int i = 0; i < CHESSBOARD_SIZE; i++) {
            String line = chessboard.get(i);
            for (int j = 0; j < CHESSBOARD_SIZE; j++) {
                chessComponents[i][j] = generateChessComponent(i, j, line.charAt(j));
            }
        }

        String isWB = chessboard.get(CHESSBOARD_SIZE);

        Arrays.stream(ChessColor.values()).forEachOrdered(e -> {
            if(isWB.equals(e.displayName())){
                currentPlayer = e;
            }
        });
    }

    @Override
    public ChessColor getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public ChessComponent getChess(int x, int y) {
        if (ChessComponent.outBoundary(x, y)) {
            return null;
        }

        return chessComponents[x][y];
    }

    @Override
    public String getChessboardGraph() {

        List<String> eachLines = new ArrayList<>();
        for (ChessComponent[] line : chessComponents) {
            StringBuilder b = new StringBuilder();
            for (ChessComponent chessPiece : line) {
                b.append(chessPiece.toString());
            }

            eachLines.add(b.toString());
        }

        return String.join(SEPARATOR, eachLines);
    }

    @Override
    public String getCapturedChess(ChessColor player) {
        Map<Class<? extends ChessComponent>, Integer> blackRestCount = new HashMap<>(), whiteRestCount = new HashMap<>();

        List<Class<? extends ChessComponent >> allChessTypes = Arrays.asList(KingChessComponent.class, QueenChessComponent.class, RookChessComponent.class,
                BishopChessComponent.class, KnightChessComponent.class, PawnChessComponent.class);

        for (Class<? extends ChessComponent> t: allChessTypes){
            blackRestCount.put(t, 0);
            whiteRestCount.put(t, 0);
        }

        for (ChessComponent[] line : chessComponents) {
            for (ChessComponent chessPiece : line) {
                if(chessPiece instanceof EmptySlotComponent || chessPiece == null){
                    continue;
                }

                Class<? extends ChessComponent> type = chessPiece.getClass();

                if(Character.isUpperCase(chessPiece.getName())){
                    blackRestCount.put(type, blackRestCount.get(type) + 1);
                }else if(Character.isLowerCase(chessPiece.getName())){
                    whiteRestCount.put(type, whiteRestCount.get(type) + 1);
                }

            }
        }

        StringBuilder blackLost = new StringBuilder(), whiteLost = new StringBuilder();

        for (Class<? extends ChessComponent> t: allChessTypes){
            if(Objects.equals(blackRestCount.get(t), fullValues.get(t))){
                continue;
            }

            blackLost.append(Character.toUpperCase(chessLowerCaseName.get(t)));
            blackLost.append(BLANK);
            blackLost.append(fullValues.get(t) - blackRestCount.get(t));
            blackLost.append(SEPARATOR);
        }

        for (Class<? extends ChessComponent> t: allChessTypes){
            if(Objects.equals(whiteRestCount.get(t), fullValues.get(t))){
                continue;
            }

            whiteLost.append(Character.toLowerCase(chessLowerCaseName.get(t)));
            whiteLost.append(BLANK);
            whiteLost.append(fullValues.get(t) - whiteRestCount.get(t));
            whiteLost.append(SEPARATOR);
        }

        if(player == null){
            return "";
        }else {
            switch (player){
                case WHITE ->{
                    return whiteLost.toString();
                }

                case BLACK -> {
                    return blackLost.toString();
                }

                default -> {
                    return "";
                }
            }

        }
    }

    public ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public List<ChessboardPoint> getCanMovePoints(ChessboardPoint source){
        return this.getChess(source.getX(), source.getY()).canMoveTo();
    }

    public boolean moveChess(int sourceX, int sourceY, int targetX, int
            targetY){
        ChessComponent source = this.getChess(sourceX, sourceY);
        if(source == null || source.getChessColor() != currentPlayer){
            return false;
        }

        boolean isMoveOK = false;
        List<ChessboardPoint> allMovable = source.canMoveTo();
        for (ChessboardPoint point: allMovable){
            if(point.getX() == targetX && point.getY() == targetY){
                isMoveOK = true;
                break;
            }
        }

        if(!isMoveOK){
            return false;
        }else {
            chessComponents[sourceX][sourceY] = generateChessComponent(sourceX, sourceY, EmptySlotComponent.NAME);
            chessComponents[targetX][targetY] = generateChessComponent(targetX, targetY, source.getName());
            currentPlayer = currentPlayer.reverseColor();

            return true;
        }
    }
}
