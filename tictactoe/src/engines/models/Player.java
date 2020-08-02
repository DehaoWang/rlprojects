package engines.models;

import engines.constants.Constant;

import java.io.IOException;
import java.util.Random;

public class Player {
    private int id;
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        setSymbol(symbol);
    }

    public Position getPosOnBoard(Board board, boolean show) throws IOException {
        // TODO: 2020-08-02
        Random random = new Random();
        while (true) {
            Position position = new Position(random.nextInt(Constant.DEF_BOARD_SIZE),
                    random.nextInt(Constant.DEF_BOARD_SIZE));
            if (board.validPosition(position)) {
                return position;
            } else {
                if (show) {
                    System.out.println("Invalid Position! Please Reselect");
                }
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        if (symbol == Constant.DEF_SYMBOL) {
            System.out.println("Invalid SYMBOL! Please RESET");
        } else {
            this.symbol = symbol;
        }
    }

    public Move getMoveOnBoard(Board board, boolean show) throws IOException {
        return new Move(this.symbol, getPosOnBoard(board, show));
    }
}
