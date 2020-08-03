package engines.models;

import engines.constants.Constant;

public class Board {
    private final int N = Constant.DEF_BOARD_SIZE;
    private final char C = Constant.DEF_SYMBOL;
    private char[][] board;

    public Board() {
        this.board = new char[N][N];
        for (int k = 0; k < N * N; k++) {
            board[k / N][k % N] = C;
        }
    }

    public boolean validPosition(Position position) {
        int x = position.getX();
        int y = position.getY();
        return board[x][y] == C;
    }

    public void updateByMove(Move move) {
        char symbol = move.getSymbol();
        Position position = move.getPosition();
        int x = position.getX();
        int y = position.getY();
        board[x][y] = symbol;
    }

    public boolean isWinner(Move move) {
        char symbol = move.getSymbol();
        Position position = move.getPosition();
        int x = position.getX();
        int y = position.getY();
        boolean horWinner = horizontalCheck(symbol, x, y);
        boolean verWinner = verticalCheck(symbol, x, y);
        boolean diaWinner = diagonalCheck(symbol, x, y);
        return horWinner || verWinner || diaWinner;
    }

    private boolean diagonalCheck(char symbol, int x, int y) {
        int counter = 0;
        // BF - to be refined
        // diagonal
        if (x == 0) {
            if (y == 0) {
                return board[1][1] == symbol && board[2][2] == symbol;
            } else if (y == 2) {
                return board[1][1] == symbol && board[2][0] == symbol;
            } else {
                return false;
            }
        } else if (x == 2) {
            if (y == 0) {
                return board[1][1] == symbol && board[0][2] == symbol;
            } else if (y == 2) {
                return board[1][1] == symbol && board[0][0] == symbol;
            } else {
                return false;
            }
        } else {
            if (y != 1) {
                return false;
            } else {
                return (board[0][0] == symbol && board[2][2] == symbol)
                        || (board[0][2] == symbol && board[2][0] == symbol);
            }
        }
    }

    private boolean verticalCheck(char symbol, int x, int y) {
        int counter = 0;
        // vertical
        for (int i = y; i < N; i++) {
            if (board[x][i] == symbol && i != y) {
                counter++;
            }
        }
        for (int i = y; i >= 0; i--) {
            if (board[x][i] == symbol && i != y) {
                counter++;
            }
        }
        return counter == 2;
    }

    private boolean horizontalCheck(char symbol, int x, int y) {
        int counter = 0;
        // horizontal
        for (int i = x; i < N; i++) {
            if (board[i][y] == symbol && i != x) {
                counter++;
            }
        }
        for (int i = x; i >= 0; i--) {
            if (board[i][y] == symbol && i != x) {
                counter++;
            }
        }
        return counter == 2;
    }

    public void show() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------");
    }

    public boolean hasValidMove() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == C)
                    return true;
            }
        }
        return false;
    }
}
