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
                Thread.sleep(500);
            }
        }
    }

    public void singleStep() throws InterruptedException {
        Player player = mazeGame.getPlayer();
        Task1Ai task1Ai = player.getTask1Ai();
        Task1AiRL task1AiRL = player.getTask1AiRL();
        Location currLocation = player.getCurrLocation();

//        Direction direction;
//        if (task1Ai != null) {
//            direction = task1Ai.submitPotentialMove(currLocation);
//        }
//        else if(task1AiRL != null){
//            direction = task1AiRL.submitPotentialMove(currLocation);
//        }
//        else {
//            System.out.println("please input direction for player: " + player.getName());
//            direction = new Direction(scanner.nextInt());
//        }
//        Location potentialLocation = getPotLoc(player.getCurrLocation(), direction);


        Location nextLocation = new Location(0, 0);
        if (task1Ai != null) {
            nextLocation = task1Ai.submitNextLocation(currLocation);
        }
        else if(task1AiRL != null){
            nextLocation = task1AiRL.submitNextLocation(currLocation);
        }
        else {
            System.out.println("please input direction for player: " + player.getName());
        }
        System.out.println("nextLocation:"+nextLocation);
        FeedBack feedBack = getFeedBackByMLD(mazeGame.getMaze(), nextLocation);
        if(feedBack.getType() == FeedBack.VALID || feedBack.getType() == FeedBack.FINAL){
            System.out.println("valid move");
//            player.setCurrLocation(potentialLocation);
            player.setCurrLocation(nextLocation);
        }
        else {
            System.out.println("invalid move");
        }
//        player.recordFeedBack(direction, feedBack);
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

    public static Location getPotLoc(Location currLocation, Direction direction) {
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


    public static FeedBack getFeedBackByMLD(Maze maze, Location potentialLocation) {
        FeedBack feedBack = new FeedBack();

        int potMovValid = validatePotMov(maze, potentialLocation);
        feedBack.setType(potMovValid);
        return feedBack;
    }
    private static int validatePotMov(Maze maze, Location potentialLocation){
        int pX = potentialLocation.getX();
        int pY = potentialLocation.getY();

        int eX = maze.getEndLoc().getX();
        int eY = maze.getEndLoc().getY();

        System.out.println("pX="+pX);
        System.out.println("pY="+pY);
        System.out.println("eX="+eX);
        System.out.println("eY="+eY);

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
        }if(maze.getEndLoc().getX() == pX
                && maze.getEndLoc().getY() == pY){
            return FeedBack.FINAL;
        }
        return FeedBack.VALID;
    }
}
