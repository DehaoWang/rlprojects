package ai;

import model.Direction;
import model.Game;
import model.Location;
import model.Maze;

/**
 * Created by wangdehao on 18/5/14.
 */
public class Task1AiRL implements AiTask1{
    private double[][] values;

    @Override
    public Location submitLocationOnExplorations(int numOfExp, int maxStepsForEachExp) {
        return null;
    }

    public Task1AiRL(Game game) {
        Maze maze = game.getMaze();
        int sizeM = maze.getMazeSizeM();
        int sizeN = maze.getMazeSizeN();
        this.values = new double[sizeM][sizeN];
    }

    @Override
    public Direction submitPotentialMove(Location currLocation) throws InterruptedException {
        // TODO: 18/5/14



        return null;
    }
}
