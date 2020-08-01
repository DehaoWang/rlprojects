package ai;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wangdehao on 18/5/14.
 */
public class Task1AiRL implements AiTask1{
    private double[][] values;
    private Maze maze;
    private double greedyFactor = 0.0;
    int statesCount = 0;

    int[][] R;
    double[][] Q;
    double gamma = 0.9;
    double alpha = 0.1;

    private int reward = 100;
    private int penalty = -100;
    private int stepPenalty = -1;

    @Override
    public Location submitLocationOnExplorations(int numOfExp, int maxStepsForEachExp) {
        return null;
    }

    public Task1AiRL(Maze maze) {
        this.maze = maze;
        int sizeM = maze.getMazeSizeM();
        int sizeN = maze.getMazeSizeN();
        this.values = new double[sizeM][sizeN];
        statesCount = sizeM * sizeN;
    }

    @Override
    public Location submitNextLocation(Location currLocation) throws InterruptedException {

        int x = currLocation.getX();
        int y = currLocation.getY();
        int state = x * maze.getMazeSizeM() + y;
        int best = getPolicyFromState(state);
        Location loc = getLocFromState(best);
        return loc;
    }

    private Location getLocFromState(int state) {
        int x = state / maze.getMazeSizeM();
        int y = state - x * maze.getMazeSizeM();
        return new Location(x, y);
    }

    @Override
    public Direction submitPotentialMove(Location currLocation) throws InterruptedException {
        // TODO: 18/5/14

        // value[i][j]: e-greedy

        double max = Integer.MIN_VALUE;
        Integer bestDirection = -1;
        for(Integer direction: Direction.DIRECTIONS){
            Location potentialLocation = Engine.getPotLoc(currLocation, new Direction(direction));
            FeedBack feedBack = Engine.getFeedBackByMLD(maze, potentialLocation);
            if(feedBack.getType() != FeedBack.VALID){
                System.out.println("invalid move");
                continue;
            }
            int x = potentialLocation.getX();
            int y = potentialLocation.getY();
            if(maze.isBlock(x, y)){
                continue;
            }
            if(values[x][y] > max){
                max = values[x][y];
                bestDirection = direction;
            }
        }

        Random random = new Random();
        double nd = random.nextDouble();
        printValueTable(values);
        if(nd >= greedyFactor){
            // exploration
            int direction = Math.abs(random.nextInt()) % Direction.DIRECTIONS.size();
            return new Direction(direction);
        }
        else {
            // exploitation
            return new Direction(bestDirection);
        }


    }
    public void printR(){
        System.out.printf("%26s", "States: ");
        for (int i = 0; i < statesCount; i++) {
            System.out.printf("%4s", i);
        }
        System.out.println();

        for (int i = 0; i < statesCount; i++) {
            System.out.print(String.format("Possible states from %2d :[", i));
            for (int j = 0; j < statesCount; j++) {
                System.out.printf("%4s", R[i][j]);
            }
            System.out.println("]");
        }
    }

    public void init(){
        R = new int[statesCount][statesCount];
        Q = new double[statesCount][statesCount];

        for(int i = 0; i < statesCount; i++){
            for(int j = 0; j < statesCount; j++){
                R[i][j] = setReward(i, j);
            }
        }
    }

    private int setReward(int i, int j) {
        int ix = i / maze.getMazeSizeM();
        int iy = i - ix * maze.getMazeSizeM();

        int jx = j / maze.getMazeSizeM();
        int jy = j - jx * maze.getMazeSizeM();

        List<Location> nextLocations = maze.getValidNextSpaces(ix, iy);
        boolean connected = false;
        for(Location next: nextLocations){
            FeedBack feedBack = Engine.getFeedBackByMLD(maze, next);
            if(jx == next.getX() && jy == next.getY()){
                connected = true;
                if(feedBack.getType() == FeedBack.BLOCK){
                    return penalty;
                }
                else if(feedBack.getType() == FeedBack.FINAL){
                    return reward;
                }
            }
        }
        if(connected){
            return 0;
        }
        return stepPenalty;
    }

    private void printValueTable(double[][] values) {
        for(int i = -1; i < values.length; i++){
            if(i == -1){
                for(int j = 0; j < 4; j++){
                    System.out.print(" ");
                }
            }
            else {
                System.out.print(String.format("%-4d", i));
            }
        }
        for(int i = 0; i < 2; i++){
            System.out.println();
        }
        for(int i = 0; i < values.length; i++){
            System.out.print(String.format("%-4d", i));
            for(int j = 0; j < values[0].length; j++){
                System.out.print(String.format("%-4.1f", values[i][j]));
            }
            for(int k = 0; k < 2; k++){
                System.out.println();
            }
        }
    }

    public void calculateQ() {
        Random rand = new Random();

        for (int i = 0; i < 10000; i++) { // Train cycles
            // Select random initial state
            int crtState = rand.nextInt(statesCount);

            while (!isFinalState(crtState)) {
                int[] actionsFromCurrentState = possibleActionsFromState(crtState);

                // Pick a random action from the ones possible
                int index = rand.nextInt(actionsFromCurrentState.length);
                int nextState = actionsFromCurrentState[index];

                // Q(state,action)= Q(state,action) + alpha * (R(state,action) + gamma * Max(next state, all actions) - Q(state,action))
                double q = Q[crtState][nextState];
                double maxQ = maxQ(nextState);
                int r = R[crtState][nextState];

                double value = q + alpha * (r + gamma * maxQ - q);
                Q[crtState][nextState] = value;

                crtState = nextState;
            }
//            printQ();
        }
    }

    boolean isFinalState(int state) {
        int i = state / maze.getMazeSizeM();
        int j = state - i * maze.getMazeSizeM();
        Location endLoc = maze.getEndLoc();

        if(i == endLoc.getX() && j == endLoc.getY()){
            return true;
        }

        return false;
    }

    int[] possibleActionsFromState(int state) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < statesCount; i++) {
            if (R[state][i] != -1) {
                result.add(i);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    double maxQ(int nextState) {
        int[] actionsFromState = possibleActionsFromState(nextState);
        double maxValue = Double.MIN_VALUE;
        for (int nextAction : actionsFromState) {
            double value = Q[nextState][nextAction];

            if (value > maxValue)
                maxValue = value;
        }
        return maxValue;
    }

    public void printPolicy() {
        System.out.println("\nPrint policy");
        for (int i = 0; i < statesCount; i++) {
            System.out.println("From state " + i + " goto state " + getPolicyFromState(i));
        }
    }

    public int getStateFromLoc(Location location){
        int i = location.getX();
        int j = location.getY();
        return i * maze.getMazeSizeM() + j;
    }

    public void printPath(int fromState, int toState, int step){

        System.out.println(String.format("step: %2d, state: %d", step, fromState));
        int nextState = getPolicyFromState(fromState);
        if(nextState == toState){
            System.out.println(String.format("EXIT -- step: %2d, state: %d", step, nextState));
            return;
        }
        step++;
        printPath(nextState, toState, step);
    }

    int getPolicyFromState(int state) {
        int[] actionsFromState = possibleActionsFromState(state);

        double maxValue = Double.MIN_VALUE;
        int policyGotoState = state;

        // Pick to move to the state that has the maximum Q value
        for (int nextState : actionsFromState) {
            double value = Q[state][nextState];

            if (value > maxValue) {
                maxValue = value;
                policyGotoState = nextState;
            }
        }
        return policyGotoState;
    }

    public void printQ() {
        System.out.println("Q matrix");
        for (int i = 0; i < Q.length; i++) {
            System.out.print("From state " + i + ":  ");
            for (int j = 0; j < Q[i].length; j++) {
                System.out.printf("%6.2f ", (Q[i][j]));
            }
            System.out.println();
        }
    }
}
