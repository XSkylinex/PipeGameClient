package view;

import java.util.Collection;

public interface Searchable<T> {

    public State<T> getInitialState();
    //public State<T> getGoalState();
    public Collection<State<T>> getAllPossibleStates(State<T> s);
    public Boolean IsGoalState(State<T> s);
}