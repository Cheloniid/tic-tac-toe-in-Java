package PPJ.TicTacToe;

public class ClearConsole {
    public static void clearConsole() {
        putNewLines(100);
    }

    public static void putNewLines(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
    }
}
