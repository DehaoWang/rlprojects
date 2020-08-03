package algorithms.models;

import java.util.*;

public class Environment {
    private State rewardState;
    private State startState;
    private int stateNum;
    private Map<Integer, State> stateMap;

    public Environment(int stateNum) {
        this.stateNum = stateNum;
        stateMap = new HashMap<>();
    }

    public Feedback feedback(State state, Action action) {
        int currIdx = state.getIdxInArray();
        if (action.getDir() == 1) {
            if (currIdx == stateNum - 1) {
                return new Feedback(new State(stateNum), 1.0);
            } else {
                return new Feedback(stateMap.get(currIdx + 1), 0.0);
            }
        } else {
            if (currIdx == 0) {
                return new Feedback(state, 0.0);
            } else {
                return new Feedback(stateMap.get(currIdx - 1), 0.0);
            }
        }
    }

//    public List<State> generateStates(int stateNum) {
//        List<State> states = new ArrayList<>();
//        for (int i = 0; i < stateNum; i++) {
//            states.add(new State(i));
//        }
//        return states;
//    }

    public Map<State, List<Action>> generateState2Actions() {
        Map<State, List<Action>> state2Actions = new HashMap<>();
        for (int i = 0; i < stateNum; i++) {
            List<Action> actions = Arrays.asList(new Action(1), new Action(-1));
            State state = new State(i);
            state2Actions.put(state, actions);
            stateMap.put(i, state);
        }
        return state2Actions;
    }

    public State getStartState() {
        return stateMap.get(0);
    }
}
