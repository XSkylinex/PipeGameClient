package view;

import controller.PipeGameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.PipeGameModel;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

public class MainWindowController extends Observable implements Initializable {
    PipeGameController controller;
    public String max = "";
    public ArrayList<String> mazeData = new ArrayList<String>();
    private int time = 1;
    private int points = 0;
    private String connection = "disconnected";
    private final String pokemonSound = "./resources/Poekmon_opening.WAV";


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

    void UpdateConnection() {
        this._connect.setText(String.format("Connection Status: %s", connection));
    }

    private long map(long x, long in_min, long in_max, long out_min, long out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controller = new PipeGameController(new PipeGameModel(), this);
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

    public void Sound() {
        // Media mp3MusicFile = new Media(getClass().getResource("./resources/Poekmon_opening.mp3").toExternalForm());
        // TODO Auto-generated method stub

//        Media media = new Media(new File(pokemonSound).toURI().toString());
//        //Instantiating MediaPlayer class
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();


        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try {
            InputStream test = new FileInputStream(pokemonSound);
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
        MGP.start(loop);
    }
}
