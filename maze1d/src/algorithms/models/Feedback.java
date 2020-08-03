package algorithms.models;

public class Feedback {
    private State state;
    private Double reward;

    public Feedback(State state, Double reward) {
        this.state = state;
        this.reward = reward;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }
}
