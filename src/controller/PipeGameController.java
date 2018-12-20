package controller;

import model.PipeGameModel;
import view.MainWindowController;

import java.util.Observable;
import java.util.Observer;

public class PipeGameController implements Observer {
    PipeGameModel model;
    MainWindowController view;

    public PipeGameController(PipeGameModel model, MainWindowController view) {
        this.model = model;
        this.view = view;
        model.addObserver(this);
        view.addObserver(this);

    }

    public void solve() {
        model.solve();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o == model) {
            // send soltuion to view
        } else if(o == view) {
            // send level sol request to models
        }
    }
}
