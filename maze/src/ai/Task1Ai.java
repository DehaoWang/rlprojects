package ai;


import model.Config;
import model.Direction;
import model.Location;

import java.util.Random;

/**
 * Created by wangdehao on 18/1/25.
 */
public class Task1Ai implements AiTask1{
    private static final int NUMBER_OF_DIRECTIONS = 4;

    public Location submitLocationOnExplorations(int numOfExp, int maxStepsForEachExp) {
        return null;
    }

    public Direction submitPotentialMove(Location currLocation) throws InterruptedException {
        Random random = new Random();
        int direction = Math.abs(random.nextInt()) % NUMBER_OF_DIRECTIONS;
        Thread.sleep(Config.SLEEP_TIME);
        return new Direction(direction);
    }
}
