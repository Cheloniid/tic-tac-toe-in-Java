package PPJ.TicTacToe;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class TicTacToe {

    private Board board;
    private Player player1;
    private Player player2;
    private List<Player> players;

    Scanner scanner;

    public TicTacToe() {
        this.board = new Board();
        this.players = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        ClearConsole.clearConsole();
        int choice1;
        do {
            System.out.print("< Choose player 1 (X) >");
            ClearConsole.putNewLines(2);
            System.out.println("1: Human");
            System.out.println("2: Computer");
            ClearConsole.putNewLines(3);
            System.out.println("Enter your choice: ");
            choice1 = scanner.nextInt();
        } while (!(choice1 == 1 || choice1 == 2));
        player1 = choice1 == 1 ? new LocalPlayer(board, 1) : new TicTacEngine(board, 1);
        players.add(player1);

        ClearConsole.clearConsole();
        int choice2;
        do {
            System.out.print("< Choose player 2 (O) >");
            ClearConsole.putNewLines(2);
            System.out.println("1: Human");
            System.out.println("2: Computer");
            ClearConsole.putNewLines(3);
            System.out.println("Enter your choice: ");
            choice2 = scanner.nextInt();
        } while (!(choice2 == 1 || choice2 == 2));
        player2 = choice2 == 1 ? new LocalPlayer(board, 2) : new TicTacEngine(board, 2);
        players.add(player2);
    }

    public void play() {

        while (!board.isGameFinished()) {
            ClearConsole.clearConsole();
            players.get(board.getMoveCount() % 2).makeMove();
        }

        ClearConsole.clearConsole();
        if (board.winsPlayer1()) {
            System.out.println("< Player 1 wins! >\n");
        } else if (board.winsPlayer2()) {
            System.out.println("< Player 2 wins! >\n");
        } else {
            System.out.println("< It is a tie! >\n");
        }

        this.board.printBoard();
        ClearConsole.putNewLines(2);

        scanner.close();
        players.forEach((player) -> {
            if (player instanceof LocalPlayer) {
                player.scanner.close();
            }
        });
    }
}
