package engines.models;

import algorithms.models.Action;
import algorithms.models.Agent;
import algorithms.models.State;

import java.util.List;
import java.util.Map;

public class PlayerRL extends Agent {


    public PlayerRL(double e, Map<State, List<Action>> state2Actions) {
        super(e, state2Actions);
    }
}
