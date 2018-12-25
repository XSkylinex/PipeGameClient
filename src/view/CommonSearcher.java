package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class CommonSearcher<T> implements Searcher<T>{
    protected Collection<State<T>> openList;
    public CommonSearcher(){ //create new search
        newSearch();
    }

    protected abstract void newSearch(); // Reset the search
    protected abstract boolean addToOpenList(State<T> initialState); // add to collections
    protected abstract State<T> popOpenList(); // take out from the collections


    @Override
    public boolean Search(Searchable<T> s){ // from algorithm to OOP Presentation number 9
        newSearch();
        addToOpenList(s.getInitialState());
        Set<State<T>> closedSet=new HashSet<>();
        while(!openList.isEmpty()){
            State<T> n=popOpenList();// dequeue
            closedSet.add(n);
            //System.out.println("n:"+n.toString());
            if(s.IsGoalState(n)){
                try {
                    return true;
                    // private method, back traces through the parents
                } catch (Exception e) {}
            }
            Collection<State<T>> successors=s.getAllPossibleStates(n);//however it is implemented
            //System.out.println("successors: " + successors.toString());
            for(State<T> state: successors){
                if(!closedSet.contains(state)){
                    if(!openList.contains(state)){
                        addToOpenList(state);
                    }
                    else{
                        if(openList.removeIf(t -> t.equals(state)&&t.getCost()>state.getCost())){
                            addToOpenList(state);
                        }
                    }
                }

            }
        }
        return false;
    }
}
