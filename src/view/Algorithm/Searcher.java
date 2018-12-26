package view.Algorithm;

import view.Algorithm.Searchable;

public interface Searcher<T>{
    // the search method
    public boolean Search(Searchable<T> s);
    // get how many nodes were evaluated by the algorithm
    default int getNumberOfNodesEvaluated(){
        return 0;
    }
}
