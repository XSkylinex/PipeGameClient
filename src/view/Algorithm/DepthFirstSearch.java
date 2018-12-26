package view.Algorithm;

import java.util.Stack;

public class DepthFirstSearch<T> extends CommonSearcher<T> {
    @Override
    protected void newSearch() {
        super.openList= new Stack<>();
    }

    @Override
    protected boolean addToOpenList(State<T> initialState) {
        ((Stack<State<T>>)openList).push(initialState);
        return true;
    }

    @Override
    protected State<T> popOpenList() {
        return ((Stack<State<T>>)openList).pop();
    }
}
