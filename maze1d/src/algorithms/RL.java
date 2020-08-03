package algorithms;

import algorithms.models.Action;
import algorithms.models.Environment;
import algorithms.models.QTable;
import algorithms.models.State;

public interface RL {
    public Action getActionByEGreedy(QTable qTable, State state);

    public void trainInEnv(Environment environment, int episodes);
}
