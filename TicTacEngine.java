package PPJ.TicTacToe;

import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class TicTacEngine extends Player {

    final int DELAY = 950;

    int difficulty;
    List<Move> availableMoves;
    Random randGen;

    public TicTacEngine(Board board, int index) {
        super(board, index);
        scanner = new Scanner(System.in);
        availableMoves = new ArrayList<>();
        randGen = new Random();

        do {
            ClearConsole.clearConsole();
            System.out.println("< Choose engine (difficulty level) >\n");
            System.out.println("0: for very easy (random moves)");
            ClearConsole.putNewLines(4);
            System.out.println("Enter your choice: ");
            difficulty = scanner.nextInt();
        } while (difficulty != 0);
    }

    public void updateAvailableMoves() {
        availableMoves.clear();
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (board.isEmptyField(i, j)) {
                    this.availableMoves.add(new Move(i, j));
                }
            }
        }
    }

    public void makeMove() {
        ClearConsole.clearConsole();
        System.out.println("< Player's %d move >\n".formatted(playerIndex));
        this.board.printBoard();
        ClearConsole.putNewLines(2);

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception");
        }

        // Tutaj wpisz swoje metody przy innych oszacowanych poziomach trudnosci
        if (difficulty == 0) {
            makeRandomMove();
        }
    }

    public void makeRandomMove() {
        updateAvailableMoves();
        Move randomMove = this.availableMoves.get(
                randGen.nextInt(this.availableMoves.size()));
        board.markOnBoard(playerIndex == 1 ? "X" : "O",
                randomMove.x, randomMove.y);
    }

    // Tutaj dodaj swoje metody

}

class Move {
    int x;
    int y;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
