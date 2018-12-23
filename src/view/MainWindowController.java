package view;

import controller.PipeGameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.PipeGameModel;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

public class MainWindowController extends Observable implements Initializable{
    PipeGameController controller;
    public String max = "";
    public ArrayList<String> mazeData = new ArrayList<String>();
    private int time = 1;
    private int points = 0;
    private String connection = "disconnected";

    @FXML
    MazeDisplayer mazeDisplayer;

    @FXML
    Label score;

    @FXML
    TextField _ip;


    @FXML
    TextField _port;

    @FXML
    Label _connect;

    void UpdateConnection()
    {
        this._connect.setText(String.format("Connection Status: %s", connection));
    }

    private long map(long x, long in_min, long in_max, long out_min, long out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controller = new PipeGameController(new PipeGameModel(), this);
        this.score.setText("Score: " + points);

        UpdateConnection();
        mazeDisplayer.setMazeData(mazeData);
        mazeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED,(e)->{
            mazeDisplayer.requestFocus();
            int i = (int)map((long)e.getY(), 0, (long)mazeDisplayer.getHeight(), 0, (long)mazeDisplayer.getMazeData().size());
            System.out.println(i);
            int j = (int)map((long)e.getX(), 0, (long)mazeDisplayer.getWidth(), 0, (long) mazeDisplayer.getMazeData().get(0).length());
            System.out.println(j);
            mazeDisplayer.switchCell(i,j,time);
            points++;
            this.score.setText("Score: " + points);
        });

    }

    public void solve() {
        System.out.println("Start Pipe game");

    }

    public void OpenFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Level");
        fileChooser.setInitialDirectory(new File("./resources"));
        File Maze = fileChooser.showOpenDialog(null);
        if (Maze != null) {
            mazeData = readMaze(Maze);
            mazeDisplayer.setMazeData(mazeData);
            for (int i = 0; i < mazeData.size(); i++) {
                if (mazeData.get(i).length() > max.length()) {
                    max = mazeData.get(i);
                }
            }
        } else {
            System.out.println("Not Found");

        }

    }

    public ArrayList<String> readMaze(File Maze){
        BufferedReader buff = null;
        try {
            buff = new BufferedReader(new FileReader(Maze));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String streader = null;
        ArrayList<String> gameboard = new ArrayList<>();
        try {
            streader = buff.readLine();
            while (streader != null) {
                gameboard.add(streader);
                streader = buff.readLine();
            }
            return gameboard;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void chooseTheme(ActionEvent event){
        String text = ((MenuItem)event.getSource()).getText();
        System.out.println(text);
        mazeDisplayer.setThemeName(text.substring(6));
    }

    public String getIp() {
        return _ip.getText();
    }

    public String getPort() {
        return _port.getText();
    }

    public void setConnect(String connect){
        this.connection=connect;
        UpdateConnection();
    }

    public void connect(){
        setChanged();
        notifyObservers("Connect");
    }

}
