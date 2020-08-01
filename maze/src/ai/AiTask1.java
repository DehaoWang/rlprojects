package ai;


import model.Direction;
import model.Location;

/**
 * Created by wangdehao on 18/1/25.
 */
public interface AiTask1 {
    public Location submitLocationOnExplorations(int numOfExp, int maxStepsForEachExp);

    Direction submitPotentialMove(Location currLocation) throws InterruptedException;

    Location submitNextLocation(Location currLocation) throws InterruptedException;
}
