package engines;

import engines.models.Board;
import engines.models.Game;
import engines.models.Player;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    public static void main(String[] args) {
        Map<Integer, Integer> counter = new HashMap<>();
        int episodes = 1000000;
        int stepSum = 0;
        for (int i = 0; i < episodes; i++) {
            Player player = new Player('P');
            Board board = new Board(7);
            Game game = new Game(player, board, 0, 6);
            int steps = game.playInTurns(false);
            counter.put(steps, counter.getOrDefault(steps, 0) + 1);
            stepSum += steps;
        }
        int avgSteps = stepSum / episodes;
        System.out.println("Average steps = " + avgSteps);
//        System.out.println(counter);
    }
}
