package view;

import controller.PipeGameController;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.PipeGameModel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.net.URL;
import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainWindowController extends Observable implements Initializable {
    PipeGameController controller;
    public String max = "";
    public ArrayList<String> mazeData = new ArrayList<String>();
    private int time = 1;
    private int points = 0;
    private String connection = "disconnected";
    Music musicPokemon;

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

    @FXML
    Label _time;



    void UpdateConnection() {
        this._connect.setText(String.format("Connection Status: %s", connection));
    }

    private long map(long x, long in_min, long in_max, long out_min, long out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controller = new PipeGameController(new PipeGameModel(), this);
        timer();
        try {
            musicPokemon = new Music();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        this.score.setText("Moves: " + points);
        UpdateConnection();
        mazeDisplayer.setMazeData(mazeData);
        mazeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            mazeDisplayer.requestFocus();
            int i = (int) map((long) e.getY(), 0, (long) mazeDisplayer.getHeight(), 0, (long) mazeDisplayer.getMazeData().size());
            System.out.println(i);
            int j = (int) map((long) e.getX(), 0, (long) mazeDisplayer.getWidth(), 0, (long) mazeDisplayer.getMazeData().get(0).length());
            System.out.println(j);
            mazeDisplayer.switchCell(i, j, time);
            points++;
            if (mazeDisplayer.flag == 1) {
                points--;
            }
            mazeDisplayer.flag = 0;
            this.score.setText("Moves: " + points);
        });

    }

    public void solve() {
        System.out.println("Start Pipe game");

    }

    public void OpenFile() {
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

    public ArrayList<String> readMaze(File Maze) {
        BufferedReader buff = null;
        try {
            buff = new BufferedReader(new FileReader(Maze));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String streader;
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

    public void chooseTheme(ActionEvent event) {
        String text = ((MenuItem) event.getSource()).getText();
        System.out.println(text);
        mazeDisplayer.setThemeName(text.substring(6));
    }

    public String getIp() {
        return _ip.getText();
    }

    public String getPort() {
        return _port.getText();
    }

    public void setConnect(String connect) {
        this.connection = connect;
        UpdateConnection();
    }

    public void connect() {
        setChanged();
        notifyObservers("Connect");
    }

   public void SoundOn() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
       musicPokemon.play();
        musicPokemon.resetAudioStream();

    }

    public void SoundOff(){
        musicPokemon.stop();
    }

    public void gameSave(){
        try {
            PrintWriter write = new PrintWriter("./resources/Saves/GameSave.txt");
            for(int i =0;i<mazeData.size();i++){
                write.println(mazeData.get(i));

            }
            write.flush();
            write.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void timer(){
        long start = System.currentTimeMillis();
//        String[] s = "34:23:54".split(":");
//        long milliseconds = TimeUnit.SECONDS.toMillis(
//                TimeUnit.HOURS.toSeconds(Integer.parseInt(s[0])) +
//                        TimeUnit.MINUTES.toSeconds(Integer.parseInt(s[1]))
//        );
//
//        long start = milliseconds;
        Label timeLabel = this._time;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long n = System.currentTimeMillis();
                long millis =  ((n - start));

                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                timeLabel.setText("Time: "+ hms);



            }
        };

        timer.start();
    }

}