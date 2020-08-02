package engines;

public class Referee {
    private int id;
    private String name;

    public Referee(String name) {
        this.name = name;
    }

//    public boolean dropPieceOnBoard(Player player, Position position, Board board) {
//        // check valid
//        if (board.validPosition(position)) {
//            board.dropPiece(player.getSymbol(), position);
//            return true;
//        } else {
//            return false;
//        }
//    }
}
