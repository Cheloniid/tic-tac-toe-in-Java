package PPJ.TicTacToe;

public class Board {

    String[][] boardString;

    public Board() {
        boardString = new String[3][3];
        for (int i = 0; i < boardString.length; i++) {
            for (int j = 0; j < boardString.length; j++) {
                boardString[i][j] = " ";
            }
        }
    }

    public String getField(int x, int y) {
        return boardString[y][x];
    }

    public boolean isEmptyField(int x, int y) {
        return this.boardString[y][x] == " ";
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

    public void markOnBoard(String symbol, int x, int y) {
        this.boardString[y][x] = symbol;
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
        System.out.println();
    }
}
