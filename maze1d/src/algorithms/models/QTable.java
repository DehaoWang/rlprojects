package algorithms.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QTable {
    Map<State, Map<Action, Double>> qTable;

    Map<State, Action> state2MaxAction;

    public QTable() {
        qTable = new HashMap<>();
        state2MaxAction = new HashMap<>();
    }

    public void initialize(Map<State, List<Action>> state2Actions) {
        for (State state : state2Actions.keySet()) {
            Map<Action, Double> action2Value = new HashMap<>();
            qTable.put(state, action2Value);

            List<Action> actions = state2Actions.get(state);
            for (Action action : actions) {
                action2Value.put(action, 0.0);
            }
        }
//        System.out.println(qTable);
    }

    public Action getMaxAction(State state) {
        return state2MaxAction.get(state);
    }

    public double getQValue(State state, Action action) {
        return qTable.get(state).get(action);
    }
}
