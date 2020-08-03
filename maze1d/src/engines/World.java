package engines;

import algorithms.models.Action;
import algorithms.models.Agent;
import algorithms.models.Environment;
import algorithms.models.State;

import java.util.List;
import java.util.Map;

public class World {
    public static void main(String[] args) {
        // basic Game Impl

//        int episodes = 1000000;
//        int stepSum = 0;
//        for (int i = 0; i < episodes; i++) {
//            Player player = new Player('P');
//            Board board = new Board(6);
//            Game game = new Game(player, board, 0, 5);
//            int steps = game.playInTurns(false);
//            stepSum += steps;
//        }
//        int avgSteps = stepSum / episodes;
//        System.out.println("Average steps = " + avgSteps);


        // RL Impl
        int maze1dSize = 6;
        Environment env = new Environment(maze1dSize);
        Map<State, List<Action>> state2Actions = env.generateState2Actions();
//        System.out.println(state2Actions);
        Agent agent = new Agent(0.9, state2Actions);
        agent.trainInEnv(env, 13);
    }
}
