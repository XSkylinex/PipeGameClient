package controller;

import model.PipeGameModel;
import view.MainWindowController;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class PipeGameController implements Observer {
    PipeGameModel model;
    MainWindowController view;

    public PipeGameController(PipeGameModel model, MainWindowController view){
        this.model = model;
        this.view = view;
        model.addObserver(this);
        view.addObserver(this);
    }

//    public void solve(){
//        model.solve();
//    }

    @Override
    public void update(Observable o, Object arg) {
        if(o == model){
            if(arg instanceof String){
                if(arg.equals("Connect")){
                    view.notifyObservers();
                }else if(arg.equals("update")){
                    view.setConnect("We connected");

                }
            }
            // send soltuion to view
        }else if(o == view){
            // send level sol request to models
            if(arg instanceof String){
                if(((String)arg).equals("Connect")){
                    try {
                        model.serverConnection(view.getIp(),Integer.parseInt(view.getPort()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
