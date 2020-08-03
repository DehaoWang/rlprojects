package engines;

import engines.models.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class World {
    public static void main(String[] args) throws IOException {

        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            Player p1 = new Player("Alice", 'X');
//            Player p1 = new PlayerHuman("Alice", 'X');
            Player p2 = new Player("Bob", 'O');
            Referee r = new Referee("Charlie");
            Board b = new Board();
            int cT = 0;
            Game game = new Game(p1, p2, r, b, cT);

            char result = game.playInTurns(false);
            counter.put(result, counter.getOrDefault(result, 0) + 1);
        }

        System.out.println(counter);
    }
}
