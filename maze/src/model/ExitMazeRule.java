package model;

/**
 * Created by wangdehao on 18/5/14.
 */
public class ExitMazeRule {
    private Location endLoc;

    public ExitMazeRule(Location endLoc) {
        this.endLoc = endLoc;
    }

    public Location getEndLoc() {
        return endLoc;
    }

    public void setEndLoc(Location endLoc) {
        this.endLoc = endLoc;
    }
}
