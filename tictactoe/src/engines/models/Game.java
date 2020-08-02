package engines.models;

import java.io.IOException;

public class Game {
    private Player player0;
    private Player player1;
    private Referee referee;
    private Board board;

    private int currTurn = 0;

    public Game(Player player0, Player player1, Referee referee, Board board, int currTurn) {
        this.player0 = player0;
        this.player1 = player1;
        this.referee = referee;
        this.board = board;
        this.currTurn = currTurn;
    }

    public char playInTurns(boolean show) throws IOException {
        while (true && board.hasValidMove()) {
            Move move;
            if (currTurn == 0) {
                move = player0.getMoveOnBoard(board, show);
            } else {
                move = player1.getMoveOnBoard(board, show);
            }
            board.updateByMove(move);
            currTurn = 1 - currTurn;
            if (show) {
                board.show();
            }
            if (board.isWinner(move)) {
                if (show) {
                    System.out.println("The winner is " + move.getSymbol());
                }
                return move.getSymbol();
            }
        }
        return 'T';
    }
}
