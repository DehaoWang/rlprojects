package engines.models;

public class Game {
    private Player player;
    private Board board;
    private int startPos;
    private int endPos;
    private int currPos;

    public Game(Player player, Board board, int startPos, int endPos) {
        this.player = player;
        this.board = board;
        this.startPos = startPos;
        this.endPos = endPos;

        currPos = startPos;

        board.setSymbolToPosition(player.getSymbol(), startPos);
        board.setSymbolToPosition('E', endPos);
    }

    public int playInTurns(boolean show) {
        int count = 0;
        while (true && currPos != endPos) {
            if (show) {
                board.show();
            }
            int dir = player.getDirOnBoard(board, currPos);
//            System.out.println("curr = " + currPos + ", dir = " + dir);
            board.updateByMove(player.getSymbol(), currPos, dir);
            currPos += dir;
            count++;
        }
        if (show) {
            System.out.println(String.format("End of the Game, %3d steps used.", count));
        }
        return count;
    }
}
