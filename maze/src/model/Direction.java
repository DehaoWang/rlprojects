package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangdehao on 18/1/25.
 */
public class Direction {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final Set<Integer> DIRECTIONS = new HashSet<>();
    static {
        DIRECTIONS.add(UP);
        DIRECTIONS.add(DOWN);
        DIRECTIONS.add(LEFT);
        DIRECTIONS.add(RIGHT);
    }

    private int direction = -1;

    public Direction(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
