// package PPJ.TicTacToe;

import java.util.Scanner;

public abstract class Player {
    protected Board board;
    protected int playerIndex;
    Scanner scanner;

    public Player(Board board, int index) {
        this.board = board;
        this.playerIndex = index;
    }

    public abstract void makeMove();
}
