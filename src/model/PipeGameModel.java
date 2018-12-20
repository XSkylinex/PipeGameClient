package model;

import controller.PipeGameController;

import java.util.Observable;

public class PipeGameModel extends Observable {
    public PipeGameController controller;

    // connection method....
    public void solve() {
        // TODO connect to SERVER and get solution...
        System.out.println("MODEL SOLVE IS CALLED");

        // when we the sol from sever we notify to our observer.
        this.notifyObservers("solution....");
    }
}
