// package PPJ.TicTacToe;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LocalPlayer extends Player {

    public LocalPlayer(Board board, int index) {
        super(board, index);
        this.scanner = new Scanner(System.in);
    }

    public void makeMove() {
        String coordinates;
        Pattern pattern;
        Matcher matcher;

        do {
            ClearConsole.clearConsole();
            System.out.println("< Player's %d move >\n".formatted(playerIndex));
            this.board.printBoard();
            ClearConsole.putNewLines(1);
            System.out.println("Enter coordinates in format [x,y]: ");
            coordinates = scanner.next();
            pattern = Pattern.compile("^[0-2],[0-2]$");
            matcher = pattern.matcher(coordinates);
        } while (!matcher.find() || !this.board.isEmptyField(
                coordinates.charAt(0) - '0',
                coordinates.charAt(2) - '0'));

        board.markOnBoard(playerIndex == 1 ? "X" : "O",
                coordinates.charAt(0) - '0',
                coordinates.charAt(2) - '0');
    }

    public void closeScanner() {
        this.scanner.close();
    }
}