package model;

/**
 * Created by wangdehao on 18/1/25.
 */
public class Game {
    private Player player;
    private Maze maze;
    private GameRule gameRule;

    private Location startLoc;
    private Location endLoc;

    public Game(Maze maze, Player player, GameRule gameRule) {
        this.maze = maze;
        this.player = player;
        this.gameRule = gameRule;
    }

    public void printState(){
        for(int i = -1; i < maze.getMazeSizeM(); i++){
            if(i == -1){
                for(int j = 0; j < 4; j++){
                    System.out.print(" ");
                }
            }
            else {
                System.out.print(String.format("%-4d", i));
            }
        }
        for(int i = 0; i < 2; i++){
            System.out.println();
        }
        for(int i = 0; i < maze.getMazeSizeM(); i++){
            System.out.print(String.format("%-4d", i));
            for(int j = 0; j < maze.getMazeSizeN(); j++){
                if(i == player.getCurrLocation().getX() && j == player.getCurrLocation().getY()){
                    System.out.print(String.format("%-4s", player.getInitial()));
                }
                else {
                    System.out.print(String.format("%-4s", maze.getSpace(i, j).getFiller()));
                }
            }
            for(int k = 0; k < 2; k++){
                System.out.println();
            }
        }
    }

//    public boolean isOnGoing() {
//        if(movesRemaining > 0){
//            return true;
//        }
//        return false;
//    }

    public boolean exitMaze(){
        if(player.getCurrLocation().getX() == endLoc.getX()
                && player.getCurrLocation().getY() == endLoc.getY()){
            return true;
        }
        else {
            return false;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public GameRule getGameRule() {
        return gameRule;
    }

    public void setGameRule(GameRule gameRule) {
        this.gameRule = gameRule;
    }
}
