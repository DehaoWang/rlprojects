package engines.models;

import java.util.Random;

public class Player {
    private char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public int getDirOnBoard(Board board, int currPos) {
        if (currPos == 0) {
            return 1;
        }
        Random random = new Random();
        if (random.nextDouble() > 0.5) {
            return -1;
        } else {
            return 1;
        }
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
