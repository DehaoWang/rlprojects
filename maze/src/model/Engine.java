package model;


import ai.Task1Ai;
import ai.Task1AiRL;

import java.util.Scanner;

/**
 * Created by wangdehao on 18/1/16.
 */
public class Engine {
    Game mazeGame;
    GameRule gameRule;
    private int remainingSteps = 0;
    private int timeStepsTaken = 0;
    private Location endLoc;

    public Engine(Game mazeGame) {
        this.mazeGame = mazeGame;
        this.gameRule = mazeGame.getGameRule();
        if(gameRule.isUseRemainingSteps()){
            this.remainingSteps = gameRule.getRemainingStepRule().getRemainingSteps();
        }
        else if(gameRule.isUseExitMaze()){
            this.endLoc = gameRule.getExitMazeRule().getEndLoc();
        }
    }

    public void gameRun() throws InterruptedException {
        if(gameRule.isUseRemainingSteps()){
            while (remainingSteps > 0){
                singleStep();
            }
        }
        else if(gameRule.isUseExitMaze()){
            Player player = mazeGame.getPlayer();
            while (player.getCurrLocation().getX() != endLoc.getX()
                    || player.getCurrLocation().getY() != endLoc.getY()){
                singleStep();
            }
        }
    }

    public void singleStep() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Direction direction;
        Player player = mazeGame.getPlayer();
        Task1Ai task1Ai = player.getTask1Ai();
        Task1AiRL task1AiRL = player.getTask1AiRL();
        if (task1Ai != null) {
            direction = task1Ai.submitPotentialMove(player.getCurrLocation());
        }
        else if(task1AiRL != null){
            direction = task1AiRL.submitPotentialMove(player.getCurrLocation());
        }
        else {
            System.out.println("please input direction for player: " + player.getName());
            direction = new Direction(scanner.nextInt());
        }
        Location potentialLocation = getPotLoc(player.getCurrLocation(), direction);

        FeedBack feedBack = getFeedBackByMLD(mazeGame.getMaze(), potentialLocation);
        if(feedBack.getType() == FeedBack.VALID){
            System.out.println("valid move");
            player.setCurrLocation(potentialLocation);
        }
        else {
            System.out.println("invalid move");
        }
        player.recordFeedBack(direction, feedBack);
        this.remainingSteps--;
        this.timeStepsTaken++;
        printInfo();
    }

    public void printInfo(){
        mazeGame.printState();
        if(gameRule.isUseRemainingSteps()){
            System.out.println("remaining moves: "+remainingSteps);
        }
        else if(gameRule.isUseExitMaze()){
            System.out.println("timesteps taken: "+timeStepsTaken);
        }
    }

    private Location getPotLoc(Location currLocation, Direction direction) {
        Location potentialLocation = new Location(0,0);
        switch (direction.getDirection()){
            case Direction.UP:
                potentialLocation = new Location(currLocation.getX()-1, currLocation.getY());
                break;
            case Direction.DOWN:
                potentialLocation = new Location(currLocation.getX()+1, currLocation.getY());
                break;
            case Direction.LEFT:
                potentialLocation = new Location(currLocation.getX(), currLocation.getY()-1);
                break;
            case Direction.RIGHT:
                potentialLocation = new Location(currLocation.getX(), currLocation.getY()+1);
                break;
        }
        return potentialLocation;
    }


    private FeedBack getFeedBackByMLD(Maze maze, Location potentialLocation) {
        FeedBack feedBack = new FeedBack();

        int potMovValid = validatePotMov(maze, potentialLocation);
        feedBack.setType(potMovValid);
        return feedBack;
    }
    private int validatePotMov(Maze maze, Location potentialLocation){
        int pX = potentialLocation.getX();
        int pY = potentialLocation.getY();
        if(pX < 0){
            return FeedBack.BOUNDARY;
        }if(pX >= maze.getMazeSizeM()){
            return FeedBack.BOUNDARY;
        }if(pY < 0){
            return FeedBack.BOUNDARY;
        }if(pY >= maze.getMazeSizeN()){
            return FeedBack.BOUNDARY;
        }if(maze.getSpace(pX, pY).isBlocked()){
            return FeedBack.BLOCK;
        }
        return FeedBack.VALID;
    }
}
