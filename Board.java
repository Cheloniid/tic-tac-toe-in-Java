package PPJ.TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private String[][] boardString;
    public List<Move> availableMoves;
    private int moveCount;
    public static int boardCounter = 0;


    public Board() {
        boardString = new String[3][3];
        for (int i = 0; i < boardString.length; i++) {
            for (int j = 0; j < boardString.length; j++) {
                boardString[i][j] = " ";
            }
        }
        boardCounter++;
        availableMoves = new ArrayList<>();
        moveCount = 0;
    }

    public Board getCopy(){
        Board newBoard = new Board();
        for (int i = 0; i < boardString.length; i++) {
            for (int j = 0; j < boardString.length; j++) {
                if (boardString[i][j] == "X"){
                    newBoard.markOnBoard("X", j, i);
                } else if (boardString[i][j] == "O"){
                    newBoard.markOnBoard("O", j, i);
                }
            }
        }
        return newBoard;
    }

    public String getField(int x, int y) {
        return boardString[y][x];
    }

    public boolean isEmptyField(int x, int y) {
        return boardString[y][x] == " ";
    }

    public boolean isFullBoard() {
        boolean isFull = true;
        for (int i = 0; i < boardString.length; i++) {
            for (int j = 0; j < boardString[i].length; j++) {
                if (boardString[i][j] == " ") {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    public void updateAvailableMoves() {
        availableMoves.clear();
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (this.isEmptyField(i, j)) {
                    this.availableMoves.add(new Move(i, j));
                }
            }
        }
    }

    public void displayAvailableMoves(){
        for (Move move : this.availableMoves){
            System.out.println("%d, %d wins: %d loses %d ties %d"
                            .formatted(move.x, move.y, move.wins, move.loses, move.ties));
        }
    }

    public void markOnBoard(String symbol, int x, int y) {
        this.boardString[y][x] = symbol;
        this.moveCount++;
    }

    public void markOnBoard(String symbol, Move move){
        markOnBoard(symbol, move.x, move.y);
    }

    public int getMoveCount(){
        return this.moveCount;
    }

    public String getCurrentSymbol(){
        return this.moveCount % 2 == 0 ? "X" : "O";
    }

    public void printBoard() {
        System.out.println(" |0|1|2|");
        System.out.println(String.format("0|%s|%s|%s|",
                this.boardString[0][0],
                this.boardString[0][1],
                this.boardString[0][2]));
        System.out.println(String.format("1|%s|%s|%s|",
                this.boardString[1][0],
                this.boardString[1][1],
                this.boardString[1][2]));
        System.out.println(String.format("2|%s|%s|%s|",
                this.boardString[2][0],
                this.boardString[2][1],
                this.boardString[2][2]));
        System.out.println(boardCounter);
    }

    public boolean winsPlayerWithSymbol(String symbol) {
        return getField(0, 0) == symbol && getField(0, 1) == symbol && getField(0, 2) == symbol
                || getField(1, 0) == symbol && getField(1, 1) == symbol && getField(1, 2) == symbol
                || getField(2, 0) == symbol && getField(2, 1) == symbol && getField(2, 2) == symbol
    
                || getField(0, 0) == symbol && getField(1, 0) == symbol && getField(2, 0) == symbol
                || getField(0, 1) == symbol && getField(1, 1) == symbol && getField(2, 1) == symbol
                || getField(0, 2) == symbol && getField(1, 2) == symbol && getField(2, 2) == symbol
    
                || getField(0, 0) == symbol && getField(1, 1) == symbol && getField(2, 2) == symbol
                || getField(0, 2) == symbol && getField(1, 1) == symbol && getField(2, 0) == symbol;
    }
    
    public boolean winsPlayer1() {
        return winsPlayerWithSymbol("X");
    }
    
    public boolean winsPlayer2() {
        return winsPlayerWithSymbol("O");
    }
    
    public boolean isTie() {
        return !winsPlayer1() && !winsPlayer2() && isFullBoard();
    }
    
    public boolean isGameFinished() {
        return winsPlayer1() || winsPlayer2() || isTie();
    }
}
