package engines;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Environment {
    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 1; i++) {
//            Player p1 = new Player("Alice", 'X');
            Player p1 = new PlayerHuman("Alice", 'X');
            Player p2 = new Player("Bob", 'O');
            Referee r = new Referee("Charlie");
            Board b = new Board();
            int cT = 0;
            Game game = new Game(p1, p2, r, b, cT);

            game.playInTurns();
        }
    }
}
