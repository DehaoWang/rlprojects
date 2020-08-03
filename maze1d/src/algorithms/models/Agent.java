package algorithms.models;

import algorithms.RL;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class Agent implements RL {
    private State currState;
    private QTable qTable;
    private Map<State, List<Action>> state2Actions;
    // e-greedy
    private double e;
    // gamma: discount rate
    private double g;
    // alpha: learning rate
    private double a;

    Random random = new Random();

    public Agent(double e, double g, double a, Map<State, List<Action>> state2Actions) {
        this.e = e;
        this.g = g;
        this.a = a;
        this.state2Actions = state2Actions;
        this.buildQTable(state2Actions);
    }

    public void buildQTable(Map<State, List<Action>> state2Actions) {
        qTable = new QTable();
        qTable.initialize(state2Actions);
    }

    @Override
    public Action getActionByEGreedy(QTable qTable, State state) {
        List<Action> actions = state2Actions.get(state);
        Action currMax = qTable.getMaxAction(state);
        if (random.nextDouble() < e) {
            return currMax;
        } else {
            return exploreNonMaxActions(actions, currMax);
        }
    }

    public Action exploreNonMaxActions(List<Action> actions, Action currMax) {
        int len = actions.size();
        while (true) {
            int r = random.nextInt(len);
            Action action = actions.get(r);
            if (action != currMax) {
                return action;
            }
        }
    }

    @Override
    public void trainInEnv(Environment environment, int episodes) {
        for (int i = 0; i < episodes; i++) {
            int steps = 0;
            State state = environment.getStartState();
            boolean terminated = false;
//            environment.show();
            while (!terminated) {
//                showQTable();
                Action action = getActionByEGreedy(qTable, state);
                Feedback feedback = environment.feedback(state, action);
                State nextState = feedback.getState();
                double reward = feedback.getReward();
                double predictQ = qTable.getQValue(state, action);
                double targetQ = 0;

                if (nextState.getIdxInArray() >= state2Actions.size()) {
                    targetQ = reward;
                    // TODO: 2020-08-03 : step counter BUG
//                    System.out.println(String.format("End @ Episode %3d: steps: %3d", i, steps));
                    terminated = true;
                } else {
                    targetQ = reward + g * qTable.getMaxReward(nextState);
                }
                double updatedQ = predictQ + a * (targetQ - predictQ);
                qTable.update(state, action, updatedQ);
                state = nextState;
                steps++;
            }
//            System.out.println(String.format("End @ Episode %3d: steps: %3d", i, steps));
        }
    }

    public void showQTable() {
        qTable.show();
    }
}
