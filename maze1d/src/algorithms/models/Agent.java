package algorithms.models;

public class Agent {
    private State currState;
    private QTable qTable;

    public Agent(State startState) {
        this.currState = startState;
        this.qTable = new QTable();
    }
}
