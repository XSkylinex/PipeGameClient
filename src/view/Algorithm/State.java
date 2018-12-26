package view.Algorithm;

public class State<T> implements Comparable<State<T>>{

    private T state; // the state represented by a T
    private double cost; // cost to reach this state
    private State<T> cameFrom; // the state we came from to this state

    public State(T state) {
        this.state = state;
        this.cost=0;
        this.cameFrom=null;
    }

    public State(T state, double cost, State<T> cameFrom) {
        this.state = state;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public State<T> getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(State<T> cameFrom) {
        this.cameFrom = cameFrom;
    }

    @Override
    public int compareTo(State<T> arg0) {
        return Double.compare(this.getCost(),arg0.getCost());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State))
            return false;
        if (obj == this)
            return true;
        return this.equals(((State<T>) obj));
    }
    public boolean equals(State<T> s){
        return this.state.equals(s.state);
    }

    @Override
    public int hashCode() {
        return this.state.hashCode();
    }

    @Override
    public String toString() {
        return state.toString();
    }


}
