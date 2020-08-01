package model;

import rule.ExitMazeRule;
import rule.RemainingStepRule;

/**
 * Created by wangdehao on 18/5/14.
 */
public class GameRule {
    private boolean useRemainingSteps = false;
    private boolean useExitMaze = false;

    private RemainingStepRule remainingStepRule;
    private ExitMazeRule exitMazeRule;


    public GameRule(RemainingStepRule remainingStepRule) {
        this.remainingStepRule = remainingStepRule;
        useRemainingSteps = true;
    }

    public GameRule(ExitMazeRule exitMazeRule) {
        this.exitMazeRule = exitMazeRule;
        useExitMaze = true;
    }

    public boolean isUseRemainingSteps() {
        return useRemainingSteps;
    }

    public void setUseRemainingSteps(boolean useRemainingSteps) {
        this.useRemainingSteps = useRemainingSteps;
    }

    public boolean isUseExitMaze() {
        return useExitMaze;
    }

    public void setUseExitMaze(boolean useExitMaze) {
        this.useExitMaze = useExitMaze;
    }

    public RemainingStepRule getRemainingStepRule() {
        return remainingStepRule;
    }

    public void setRemainingStepRule(RemainingStepRule remainingStepRule) {
        this.remainingStepRule = remainingStepRule;
    }

    public ExitMazeRule getExitMazeRule() {
        return exitMazeRule;
    }

    public void setExitMazeRule(ExitMazeRule exitMazeRule) {
        this.exitMazeRule = exitMazeRule;
    }
}
