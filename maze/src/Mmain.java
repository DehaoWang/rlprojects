/**
 * Created by wangdehao on 18/5/14.
 */


import ai.Task1Ai;
import ai.Task1AiRL;
import model.*;

import java.text.RuleBasedCollator;

public class Mmain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to MAZE");


        Maze maze = new Maze(8, 8);
        Task1Ai task1Ai = new Task1Ai();
        Player player = new Player("Exp1");
        player.setTask1Ai(task1Ai);
        Location startLoc = new Location(2, 0);
        player.initLocation(startLoc);

        Task1AiRL task1AiRL = new Task1AiRL();
        Player player1 = new Player(task1AiRL);
//        RemainingStepRule remainingStepRule = new RemainingStepRule(100);
//        GameRule gameRule0 = new GameRule(remainingStepRule);
//        Game game1 = new Game(maze, player, gameRule0);
//        game1.getMaze().setBlock(5, 5);
//        game1.printState();
//        Engine engine1 = new Engine(game1);
//        engine1.gameRun();
        int[][] blocks = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 0, 1, 0, 1},
                {1, 0, 0, 1, 1, 0, 0, 1},
                {1, 1, 0, 0, 1, 0, 1, 1},
                {1, 0, 1, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
        };
        Location endLoc = new Location(6, 7);
        ExitMazeRule exitMazeRule = new ExitMazeRule(endLoc);
        GameRule gameRule2 = new GameRule(exitMazeRule);
        Game game2 = new Game(maze, player, gameRule2);
        maze.setBlocks(blocks);
        game2.printState();
        Engine engine2 = new Engine(game2);
        engine2.gameRun();



    }

}
