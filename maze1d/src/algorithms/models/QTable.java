package algorithms.models;

import java.time.temporal.ValueRange;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QTable {
    Map<State, Map<Action, Double>> qTable;

//    Map<State, Action> state2MaxAction;


    public QTable() {
        qTable = new HashMap<>();
//        state2MaxAction = new HashMap<>();
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
        Map<Action, Double> action2Value = qTable.get(state);
        Action maxAction = null;
        double maxValue = -1.0;
        for (Action action : action2Value.keySet()) {
            if (action2Value.get(action) > maxValue) {
                maxAction = action;
            }
        }
        return maxAction;
    }

    public double getQValue(State state, Action action) {
        return qTable.get(state).get(action);
    }

    public double getMaxReward(State state) {
        Map<Action, Double> action2Value = qTable.get(state);
        if (action2Value == null) {
            System.out.println("A2V NULL");
            System.out.println(state.getIdxInArray());
        }
        double max = -1.0;
        for (Double value : action2Value.values()) {
            max = Math.max(max, value);
        }
        return max;
    }

    public void update(State state, Action action, double updatedQ) {
        Map<Action, Double> action2Value = qTable.get(state);
        action2Value.put(action, updatedQ);
    }

    public void show() {
        System.out.println(String.format("%6s%6s%6s", "state", "left", "right"));
        for (State state : qTable.keySet()) {
            Map<Action, Double> action2Value = qTable.get(state);
            System.out.println(String.format("%6s%6.2f%6.2f",
                    state.getIdxInArray(), action2Value.get(new Action(-1)), action2Value.get(new Action(1))));
        }
    }
}
