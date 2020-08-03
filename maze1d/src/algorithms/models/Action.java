package algorithms.models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return dir == action.dir;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dir);
    }
}
