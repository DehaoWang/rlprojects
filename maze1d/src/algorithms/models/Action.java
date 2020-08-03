package algorithms.models;

public class Action {
    private int dir;

    public Action(int dir) {
        this.dir = dir;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Action{" +
                "dir=" + dir +
                '}';
    }
}
