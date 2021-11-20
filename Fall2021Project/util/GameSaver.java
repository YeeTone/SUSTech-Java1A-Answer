package Fall2021Project.util;

import Fall2021Project.components.ChessGridComponent;
import Fall2021Project.model.Step;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameSaver extends Util{
    private static int gid = 0;
    private static BufferedWriter gameSaver;
    private static List<Step> stepList;

    private GameSaver(){
        super();
    }

    public static void initiate(){
        if(stepList!=null){
            stepList.clear();
        }
        stepList = new ArrayList<>();
        gid++;
    }

    public static void recordStep(Step s){
        stepList.add(s);
    }

    public static void saveGame(String fileName,ChessGridComponent[][]chessboard){
        try{
            gameSaver = new BufferedWriter(new FileWriter(fileName));
            gameSaver.write("Game-Id: "+gid+System.lineSeparator());
            saveStepsIntoFile(fileName,chessboard);
            saveChessboardIntoFile(fileName,chessboard);
            gameSaver.flush();
            gameSaver.close();
        }catch (IOException e){

            //todo: fail!
            e.printStackTrace();
        }

    }

    private static void saveStepsIntoFile(String fileName, ChessGridComponent[][] chessboard) throws IOException {
        gameSaver.write("Reversi-GameSteps:"+System.lineSeparator());
        for (Step s: stepList){
            gameSaver.write(s.toString()+System.lineSeparator());
        }
        gameSaver.write("=========="+System.lineSeparator());
    }

    private static void saveChessboardIntoFile(String fileName, ChessGridComponent[][] chessboard) throws IOException {
        for (int i = 0; i< SIZE; i++){
            for (int j = 0; j < SIZE; j++) {
                gameSaver.write(String.format("%3s",chessboard[i][j].toString()));
            }
            gameSaver.write(System.lineSeparator());
        }

    }

}
