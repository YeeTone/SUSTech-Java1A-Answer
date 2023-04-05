import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GobangTest {
    public static void main(String[] args) {
        GobangChess gobangChess = new GobangChess();
        List<String> lines = gobangChess.readFileByFileReader("chessboard.txt");
//        List<String> lines = gobangChess.readFileByFileReader("chessboard.txt");
        gobangChess.convertToChessboard(lines);
        for (int[] l : gobangChess.getChessboard()) {
            System.out.println(Arrays.toString(l));
        }

        gobangChess.readFileByByte("bytes.txt");

        gobangChess.playing(4,3,1);
        gobangChess.playing(4,3,2);

        gobangChess.convertToList().forEach(System.out::println);
        gobangChess.writeFileByFileWriter("new_chessboard.txt");
//        gobangChess.writeFileByLib("new_chessboard.txt");
    }
}
