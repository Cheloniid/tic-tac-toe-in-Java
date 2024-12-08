package PPJ.TicTacToe;

import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class TicTacEngine extends Player {

    final int DELAY = 1000;
    static final int DEFENSE = 1;
    static final int OFFENSE = 2;
    static final int GO_FOR_TIE = 3;

    int engineTactics;
    Random randGen;

    public TicTacEngine(Board board, int index) {
        super(board, index);
        scanner = new Scanner(System.in);
        randGen = new Random();

        do {
            ClearConsole.clearConsole();
            System.out.println("< Choose engine tactics (difficulty level) >\n");
            System.out.println("0: for very easy (random moves)");
            System.out.println("1: minimizing losing chance");
            System.out.println("2: maximizing winning chance");
            System.out.println("3: go for tie");
            ClearConsole.putNewLines(2);
            System.out.println("Enter your choice: ");
            engineTactics = scanner.nextInt();
        } while (!("0123".contains(String.valueOf(engineTactics))));
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
        if (engineTactics == 0) {
            makeRandomMove();
        } else {
            makePredictiveMove(engineTactics);
        }
    }

    public void makeRandomMove() {
        board.updateAvailableMoves();
        Move randomMove = board.availableMoves.get(
                randGen.nextInt(board.availableMoves.size()));
        board.markOnBoard(playerIndex == 1 ? "X" : "O",
                randomMove.x, randomMove.y);
    }

    public void makePredictiveMove(int engineTactics) {
        board.updateAvailableMoves();
        board.availableMoves.forEach(move -> analyzeMove(move, move, this.board.getCopy()));
        board.availableMoves.forEach(move -> move.predictResults());
        
        Move selectedMove = board.availableMoves.get(0);
        
        if (engineTactics == TicTacEngine.DEFENSE) {
            for (Move move : board.availableMoves) {
                if (move.loseChance < selectedMove.loseChance) {
                    selectedMove = move;
                }
            }
        } else if (engineTactics == TicTacEngine.OFFENSE){
            for (Move move : board.availableMoves) {
                if (move.winChance > selectedMove.winChance) {
                    selectedMove = move;
                }
            }
        } else if (engineTactics == TicTacEngine.GO_FOR_TIE) {
            for (Move move : board.availableMoves) {
                if (move.tieChance > selectedMove.tieChance) {
                    selectedMove = move;
                }
            }
        }

        board.markOnBoard(playerIndex == 1 ? "X" : "O",
                selectedMove.x, selectedMove.y);
    }

    public void analyzeMove(Move assessedMove, Move nextMove, Board board) {
        board.markOnBoard(board.getCurrentSymbol(), nextMove);

        if (board.isGameFinished()) {
            if (board.isTie()) {
                assessedMove.ties++;
            } else if (playerIndex == 1) {
                if (board.winsPlayer1()) {
                    assessedMove.wins++;
                } else if (board.winsPlayer2()) {
                    assessedMove.loses++;
                }
            } else if (playerIndex == 2) {
                if (board.winsPlayer2()) {
                    assessedMove.wins++;
                } else if (board.winsPlayer1()) {
                    assessedMove.loses++;
                }
            }
        } else {
            board.updateAvailableMoves();
            for (Move move : board.availableMoves) {
                analyzeMove(assessedMove, move, board.getCopy());
            }
        }

    }
    // Tutaj dodaj swoje metody

}

class Move {
    int x;
    int y;
    int wins;
    int loses;
    int ties;
    double winChance;
    double loseChance;
    double tieChance;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
        this.wins = 0;
        this.loses = 0;
        this.ties = 0;
    }

    public void predictResults() {
        this.winChance = (wins + loses + ties) == 0 ? 0 : (double) wins / (double) (wins + loses + ties);
        this.loseChance = (wins + loses + ties) == 0 ? 0 : (double) loses / (double) (wins + loses + ties);
        this.tieChance = (wins + loses + ties) == 0 ? 0 : (double) ties / (double) (wins + loses + ties);
        System.out.println(wins + loses + ties);
    }

}
