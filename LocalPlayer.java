package PPJ.TicTacToe;

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
        Pattern pattern1;
        Pattern pattern2;
        Matcher matcher1;
        Matcher matcher2;
        int x;
        int y;
        boolean goodInput = false;

        while (true) {
            ClearConsole.clearConsole();
            System.out.println("< Player's %d move >\n".formatted(playerIndex));
            this.board.printBoard();
            ClearConsole.putNewLines(1);
            System.out.println("Enter coordinates in format x,y or xy: ");

            coordinates = scanner.next();
            pattern1 = Pattern.compile("^[0-2],[0-2]$");
            pattern2 = Pattern.compile("^[0-2][0-2]$");
            matcher1 = pattern1.matcher(coordinates);
            matcher2 = pattern2.matcher(coordinates);

            if (matcher1.find()) {
                x = coordinates.charAt(0) - '0';
                y = coordinates.charAt(2) - '0';
            } else if (matcher2.find()) {
                x = coordinates.charAt(0) - '0';
                y = coordinates.charAt(1) - '0';
            } else {
                continue;
            }

            if (this.board.isEmptyField(x, y)) {
                this.board.markOnBoard(playerIndex == 1 ? "X" : "O", x, y);
                break;
            }
        }
    }

    public void closeScanner() {
        this.scanner.close();
    }
}