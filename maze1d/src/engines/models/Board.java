package engines.models;

public class Board {
    private int boardSize = 7;
    public final char DEF_BOARD_SYMBOL = '.';
    private char[] board;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        board = new char[boardSize];
        for (int i = 0; i < boardSize; i++) {
            board[i] = DEF_BOARD_SYMBOL;
        }
    }

    public void setSymbolToPosition(char symbol, int pos) {
        board[pos] = symbol;
    }

    public void updateByMove(char symbol, int currPos, int dir) {
        if (currPos + dir < 0) {
            return;
        } else {
            board[currPos] = DEF_BOARD_SYMBOL;
            board[currPos + dir] = symbol;
        }
    }

    public void show() {
        for (int i = 0; i < board.length; i++) {
            System.out.print(board[i] + " ");
        }
        System.out.println();
    }
}
