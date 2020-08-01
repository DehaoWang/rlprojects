/**
 * Created by wangdehao on 18/5/14.
 */


import ai.Task1Ai;
import ai.Task1AiRL;
import model.*;
import rule.ExitMazeRule;
import rule.RemainingStepRule;

public class Mmain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to MAZE");
        int testId = 1;
        if(testId == 1){
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

            Maze maze = new Maze(8, 8);
            maze.setBlocks(blocks);
            Location startLoc = new Location(5, 1);
            Location endLoc = new Location(6, 7);
            maze.setStartLoc(startLoc);
            maze.setEndLoc(endLoc);

            Task1AiRL task1AiRL = new Task1AiRL(maze);
            Player player = new Player("Random");
            player.setTask1AiRL(task1AiRL);
            player.initLocation(startLoc);


            task1AiRL.init();
            task1AiRL.printR();
            task1AiRL.calculateQ();
            task1AiRL.printQ();
            task1AiRL.printPolicy();

            int fromState = task1AiRL.getStateFromLoc(startLoc);
            int toState = task1AiRL.getStateFromLoc(endLoc);
            task1AiRL.printPath(fromState, toState, 0);

            ExitMazeRule exitMazeRule = new ExitMazeRule(endLoc);
            GameRule gameRule = new GameRule(exitMazeRule);
            Game game = new Game(maze, player, gameRule);
            game.printState();
            Engine engine2 = new Engine(game);
            engine2.gameRun();
        }
        if(testId == 2){


            int[][] blocks2 = {
                    {0, 0, 0},
                    {1, 0, 1},
                    {0, 0, 0}
            };

            Maze maze2 = new Maze(3, 3);
            maze2.setBlocks(blocks2);
            Location startLoc2 = new Location(0, 0);
            Location endLoc2 = new Location(2, 2);
            maze2.setStartLoc(startLoc2);
            maze2.setEndLoc(endLoc2);

            Task1Ai task1Ai2 = new Task1Ai();
            Player player2 = new Player("Random");
            player2.setTask1Ai(task1Ai2);
            player2.initLocation(startLoc2);

            Task1AiRL task1AiRL2 = new Task1AiRL(maze2);

            task1AiRL2.init();
            task1AiRL2.printR();
            task1AiRL2.calculateQ();
            task1AiRL2.printQ();

            int fromState2 = task1AiRL2.getStateFromLoc(startLoc2);
            int toState2 = task1AiRL2.getStateFromLoc(endLoc2);
            task1AiRL2.printPath(fromState2, toState2, 0);
        }



//        Player playerRL = new Player("RL");
//        playerRL.setTask1AiRL(task1AiRL);
//        playerRL.initLocation(startLoc);
//
//
//
//        int ruleNum = 2;
//
//        if(ruleNum == 2){
//            ExitMazeRule exitMazeRule = new ExitMazeRule(endLoc);
//            GameRule gameRule2 = new GameRule(exitMazeRule);
//            Game game2 = new Game(maze, playerRL, gameRule2);
//            game2.printState();
////            Engine engine2 = new Engine(game2);
////            engine2.gameRun();
//        }
//
//
//
//        if(ruleNum == 1){
//            RemainingStepRule remainingStepRule = new RemainingStepRule(100);
//            GameRule gameRule0 = new GameRule(remainingStepRule);
//            Game game1 = new Game(maze, player, gameRule0);
//            game1.getMaze().setBlock(5, 5);
//            game1.printState();
//            Engine engine1 = new Engine(game1);
//            engine1.gameRun();
//        }


    }

}
