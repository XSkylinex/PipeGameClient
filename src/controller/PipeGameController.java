package controller;

import model.PipeGameModel;
import view.MainWindowController;
import view.MazeDisplayer;

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
                }else if(arg.equals("update")) {
                    view.setConnect("We connected");
                }else if(arg.equals("Error")){
                    view.setConnect("disconnected");
                }
            }
        }else if(o == view){
            // send level sol request to models
            if(arg instanceof String){
                if(((String)arg).equals("Connect")){
                    try {
                        model.PipeGameModel(view.getIp(),Integer.parseInt(view.getPort()));

//                        File xmlFile=new File("./resources/connection.xml");
//                        GameStatus connection=new GameStatus();
//                        connection.set_ip(view.getIp());
//                        connection.set_port(Integer.parseInt(view.getPort()));
//                        JAXB.ConvertObjectToXML(xmlFile, connection);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(((String)arg).equals("Solve")){
//                    try {
////                        File xmlFile=new File("./resources/connection.xml");
////                        GameStatus connection = JAXB.ConvertXMLToObject(xmlFile, GameStatus.class);
                        MazeDisplayer board = view.getMazeDisplayer();
                    try {
                        //view.switchCell(1,5,3);
                        model.solve(board);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }
    }
}
