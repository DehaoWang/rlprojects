package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdehao on 18/1/25.
 */
public class Maze {
    private Space[][] maze;
    private int mazeSizeM;
    private int mazeSizeN;

    private Location startLoc;
    private Location endLoc;


    public Maze(int mazeSizeM, int mazeSizeN) {
        this.mazeSizeM = mazeSizeM;
        this.mazeSizeN = mazeSizeN;
        this.init();
    }

    public void init() {
        this.maze = new Space[mazeSizeM][mazeSizeN];
        for(int i = 0; i < mazeSizeM; i++){
            for(int j = 0; j < mazeSizeN; j++){
                maze[i][j] = new Space();
            }
        }
    }

    public void setBlocks(int[][] blocks){
        for(int i = 0; i < blocks.length; i++){
            for(int j = 0; j < blocks[0].length; j++){
                if(blocks[i][j] == 1){
                    maze[i][j].setBlocked(true);
                }
            }
        }
    }

    public void setBlock(int x, int y){
        maze[x][y].setBlocked(true);
    }

    public int getMazeSizeM() {
        return mazeSizeM;
    }

    public void setMazeSizeM(int mazeSizeM) {
        this.mazeSizeM = mazeSizeM;
    }

    public int getMazeSizeN() {
        return mazeSizeN;
    }

    public void setMazeSizeN(int mazeSizeN) {
        this.mazeSizeN = mazeSizeN;
    }

    public Space getSpace(int i, int j){
        return maze[i][j];
    }

    public boolean isBlock(int x, int y) {
        return maze[x][y].isBlocked();
    }

    public List<Location> getValidNextSpaces(int im, int in) {
        List<Location> nextLocations = new ArrayList<>();
        Location curr = new Location(im, in);
        for(int dir: Direction.DIRECTIONS){
            Location next = Engine.getPotLoc(curr, new Direction(dir));
            nextLocations.add(next);
        }
        return nextLocations;
    }


    public void setStartLoc(Location startLoc) {
        this.startLoc = startLoc;
    }

    public Location getStartLoc() {
        return startLoc;
    }

    public void setEndLoc(Location endLoc) {
        this.endLoc = endLoc;
    }

    public Location getEndLoc() {
        return endLoc;
    }
}
