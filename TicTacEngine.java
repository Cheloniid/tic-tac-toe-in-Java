package PPJ.TicTacToe;

import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class TicTacEngine extends Player {

    final int DELAY = 1000;

    int difficulty;
    Random randGen;

    public TicTacEngine(Board board, int index) {
        super(board, index);
        scanner = new Scanner(System.in);
        randGen = new Random();

        do {
            ClearConsole.clearConsole();
            System.out.println("< Choose engine (difficulty level) >\n");
            System.out.println("0: for very easy (random moves)");
            System.out.println("1: minimizing lose chance");
            ClearConsole.putNewLines(4);
            System.out.println("Enter your choice: ");
            difficulty = scanner.nextInt();
        } while (difficulty != 0 && difficulty != 1);
    }

    

    public void makeMove() {
        // ClearConsole.clearConsole();
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
        } else if (difficulty == 1) {
            makePredictiveMove();
        }
    }

    public void makeRandomMove() {
        board.updateAvailableMoves();
        Move randomMove = board.availableMoves.get(
                randGen.nextInt(board.availableMoves.size()));
        board.markOnBoard(playerIndex == 1 ? "X" : "O",
                randomMove.x, randomMove.y);
    }

    public void makePredictiveMove() {
        board.updateAvailableMoves();
        board.availableMoves.forEach(move -> analyzeMove(move, move, this.board.getCopy()));
        board.availableMoves.forEach(move -> move.predictResults());

        Move lowestLoseChanceMove = board.availableMoves.get(0);
        for (Move move : board.availableMoves){
            if (move.loseChance < lowestLoseChanceMove.loseChance){
                lowestLoseChanceMove = move;
            }
        }
        board.displayAvailableMoves();
        board.markOnBoard(playerIndex == 1 ? "X" : "O",
                lowestLoseChanceMove.x, lowestLoseChanceMove.y);
    }

    public void analyzeMove(Move assessedMove, Move nextMove, Board board){
        board.markOnBoard(board.getCurrentSymbol(), nextMove);
        System.out.println("Analyzing move: " + nextMove.x + nextMove.y);

        if (board.isGameFinished())
            System.out.println("Isgamefinished");
            if (board.isTie()){
                assessedMove.ties++;
            } else if (playerIndex == 1){
                if (board.winsPlayer1()){
                    assessedMove.wins++;
                } else if (board.winsPlayer2()){
                    assessedMove.loses++;
                }
            } else if (playerIndex == 2) {
                if (board.winsPlayer2()){
                    assessedMove.wins++;
                } else if (board.winsPlayer1()){
                    assessedMove.loses++;
                }
            }
        else {
            board.updateAvailableMoves();
            System.out.println("INSIDE ELSE");
            for (Move move : board.availableMoves){
                analyzeMove(assessedMove, move, board.getCopy());
            }
        } 
        
    }
    public static void main(String[] args) {
        Board b = new Board();
        b.printBoard();
        b.updateAvailableMoves();
        TicTacEngine t = new TicTacEngine(b, 1);
        for (Move m: b.availableMoves){
            t.analyzeMove(m, m, b);
        }
        b.displayAvailableMoves();
    }

    // Tutaj dodaj swoje metody

}

class Move {
    int x;
    int y;
    int wins;
    int loses;
    int ties;
    int winChance;
    int loseChance;
    int tieChance;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
        this.wins = 0;
        this.loses = 0;
        this.ties = 0;
    }

    public void predictResults(){
        this.winChance = (wins + loses + ties) == 0 ? 0 : wins / (wins + loses + ties);
        this.loseChance = (wins + loses + ties) == 0 ? 0 : loses / (wins + loses + ties);
        this.tieChance = (wins + loses + ties) == 0 ? 0 : ties / (wins + loses + ties);
        System.out.println(wins + loses + ties);
    }

    
}
