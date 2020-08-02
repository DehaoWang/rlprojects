package engines;

import engines.constants.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayerHuman extends Player {
    public PlayerHuman(String name, char symbol) {
        super(name, symbol);
    }

    public Position getPosOnBoard(Board board) throws IOException {
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            String[] tokens = input.split(" ");
            Position position = new Position(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
            if (board.validPosition(position)) {
                return position;
            } else {
                System.out.println("Invalid Position! Please Reselect");
            }
        }
    }
}
