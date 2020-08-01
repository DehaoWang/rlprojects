package ai;


import model.Config;
import model.Direction;
import model.Location;

import java.util.Random;

/**
 * Created by wangdehao on 18/1/25.
 */
public class Task1Ai implements AiTask1{

    public Location submitLocationOnExplorations(int numOfExp, int maxStepsForEachExp) {
        return null;
    }

    public Direction submitPotentialMove(Location currLocation) throws InterruptedException {
        Random random = new Random();
        int direction = Math.abs(random.nextInt()) % Direction.DIRECTIONS.size();
        Thread.sleep(Config.SLEEP_TIME);
        return new Direction(direction);
    }

    @Override
    public Location submitNextLocation(Location currLocation) throws InterruptedException {
        return null;
    }
}
