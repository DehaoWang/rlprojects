package algorithms.models;

public class State {
    private int idxInArray;

    public State(int idxInArray) {
        this.idxInArray = idxInArray;
    }

    public int getIdxInArray() {
        return idxInArray;
    }

    public void setIdxInArray(int idxInArray) {
        this.idxInArray = idxInArray;
    }

    @Override
    public String toString() {
        return "State{" +
                "idxInArray=" + idxInArray +
                '}';
    }
}
