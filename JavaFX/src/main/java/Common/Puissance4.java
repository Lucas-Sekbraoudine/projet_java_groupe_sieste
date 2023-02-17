package Common;

public class Puissance4 {
    private final int ROWS = 6;
    private final int COLUMNS = 7;
    private final char EMPTY_SLOT = ' ';
    private final char RED_TOKEN = 'R';
    private final char YELLOW_TOKEN = 'Y';
    private char[][] board;
    private char currentPlayer;

    public Puissance4() {
        board = new char[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY_SLOT;
            }
        }
        currentPlayer = RED_TOKEN;
    }

    public void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-----------------------------");
    }

    public boolean dropToken(int column) {
        if (column < 0 || column >= COLUMNS) {
            return false;
        }
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == EMPTY_SLOT) {
                board[i][column] = currentPlayer;
                return true;
            }
        }
        return false;
    }

    public void switchPlayer() {
        if (currentPlayer == RED_TOKEN) {
            currentPlayer = YELLOW_TOKEN;
        } else {
            currentPlayer = RED_TOKEN;
        }
    }

    public boolean isGameOver() {
        if (getWinner() != EMPTY_SLOT) {
            return true;
        }
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == EMPTY_SLOT) {
                    return false;
                }
            }
        }
        return true;
    }

    public char getWinner() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] != EMPTY_SLOT) {
                    if (checkWin(i, j)) {
                        return board[i][j];
                    }
                }
            }
        }
        return EMPTY_SLOT;
    }

    private boolean checkWin(int row, int col) {
        return checkVertical(row, col) || checkHorizontal(row, col) || checkDiagonal1(row, col) || checkDiagonal2(row, col);
    }

    private boolean checkVertical(int row, int col) {
        if (row > ROWS - 4) {
            return false;
        }
        for (int i = 1; i < 4; i++) {
            if (board[row + i][col] != board[row][col]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkHorizontal(int row, int col) {
        if (col > COLUMNS - 4) {
            return false;
        }
        for (int i = 1; i < 4; i++) {
            if (board[row][col + i] != board[row][col]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal1(int row, int col) {
        if (row > ROWS - 4 || col > COLUMNS - 4) {
            return false;
        }
        for (int i = 1; i < 4; i++) {
            if (board[row + i][col + i] != board[row][col]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal2(int row, int col) {
        if (row < 3 || col > COLUMNS - 4) {
            return false;
        }
        for (int i = 1; i < 4; i++) {
            if (board[row - i][col + i] != board[row][col]) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidMove(int column) {
        return column >= 0 && column < COLUMNS && board[0][column] == EMPTY_SLOT;
    }

    public boolean isFinished() {
        return isGameOver();
    }



}
