package view;

import controller.PipeGameController;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.PipeGameModel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class MainWindowController extends Observable implements Initializable {
    PipeGameController controller;
    public String max = "";
    public ArrayList<String> mazeData = new ArrayList<String>();
    private int time = 1;
    public  String SavedTime = "Time :00:00:00";
    public int points = 0;
    private String connection = "disconnected";
    private Music musicPokemon;
    private int soundSafe = 0;
    private FileChooser fileChooser;
    private File Maze;

    //Controllers


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
        this._ip.setText("localhost");
        this._port.setText("6400");
        this.controller = new PipeGameController(new PipeGameModel(), this);

        try {
            musicPokemon = new Music();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        this.score.setText("Moves: " + points);
        UpdateConnection();
        mazeDisplayer.setMazeData(mazeData);
        mazeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            mazeDisplayer.requestFocus();
            int i = (int) map((long) e.getY(), 0, (long) mazeDisplayer.getHeight(), 0, (long) mazeDisplayer.getMazeData().size());
            //System.out.println(i);
            int j = (int) map((long) e.getX(), 0, (long) mazeDisplayer.getWidth(), 0, (long) mazeDisplayer.getMazeData().get(0).length());
            //System.out.println(j);
            mazeDisplayer.switchCell(i, j, time);

            points++;
            if (mazeDisplayer.flag == 1) {
                points--;
            }
            mazeDisplayer.flag = 0;
            this.score.setText("Moves: " + points);
        });

    }

    public void OpenFile() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Level");
        fileChooser.setInitialDirectory(new File("./resources"));
        Maze = fileChooser.showOpenDialog(null);
        if (Maze != null) {
            fileMaker();
        }else{
            System.out.println("Not Found");

        }
    }

    public void newGame(ActionEvent event) {
        if(fileChooser == null) {
            loadUI("NewGameError");
        }else{
            fileMaker();
            points = 0;
            this.score.setText("Moves: " + points);
        }
    }

    private void fileMaker() {
        mazeData = readMaze(Maze);
        mazeDisplayer.setMazeData(mazeData);
        for (int i = 0; i < mazeData.size(); i++) {
            if (mazeData.get(i).length() > max.length()) {
                max = mazeData.get(i);
            }
        }
        timer();
    }


    public ArrayList<String> readMaze(File Maze) {
        points = 0;
        SavedTime = "Time :00:00:00";
        BufferedReader buff = null;
        try {
            buff = new BufferedReader(new FileReader(Maze));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String streader;
        int i =0;
        ArrayList<String> gameboard = new ArrayList<>();
        try {
            streader = buff.readLine();
            while (streader != null) {
                i++;
                gameboard.add(streader);
                streader = buff.readLine();
            }
            if(!(gameboard.get(--i).contains(Character.toString('g')))) {
                String TimerAndPoints = gameboard.get(i);
                SavedTime = TimerAndPoints.substring(0, TimerAndPoints.indexOf(','));
                points = Integer.parseInt(TimerAndPoints.substring(TimerAndPoints.indexOf(',') + 1));
                gameboard.remove(i);
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
        if(soundSafe == 1) {
            musicPokemon.play();
            musicPokemon.resetAudioStream();
            soundSafe = 0;
        }else {
            System.out.println("Music still playing in the background");
        }
   }

    public void SoundOff(){
        if(soundSafe == 0) {
            musicPokemon.stop();
            soundSafe = 1;
        }else{
            System.out.println("Music not playing in the background");
        }
    }

    public void gameSave(){
        try {
            PrintWriter write = new PrintWriter("./resources/Saves/GameSave.txt");
            for(int i =0;i<mazeData.size();i++){
                write.println(mazeData.get(i));

            }
            write.println(_time.getText()+","+points);
            write.flush();
            write.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void solveGame(){
        System.out.println(_connect.getText());
        if(_connect.getText().equals("Connection Status: We connected")){
            setChanged();
            notifyObservers("Solve");
        }
    }

    public void timer(){

        long start = System.currentTimeMillis();
        Label timeLabel = this._time;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long n = System.currentTimeMillis();
                String[] parts = SavedTime.split(":");
                //long hours = Long.parseLong(parts[1])*3600000;
                long minutes = Long.parseLong(parts[2])*60000;
                long second = Long.parseLong(parts[3])*1000;
                long millis= n - start+second+minutes;
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                timeLabel.setText("Time: " + hms);
            }
        };
        timer.start();


        //Dvir this is for you!
//        String[] s = "34:23:54".split(":");
//        long milliseconds = TimeUnit.SECONDS.toMillis(
//                TimeUnit.HOURS.toSeconds(Integer.parseInt(s[0])) +
//                        TimeUnit.MINUTES.toSeconds(Integer.parseInt(s[1]))
//        );
//
//        start = milliseconds;
    }

    public MazeDisplayer getMazeDisplayer() {
        return mazeDisplayer;
    }

    public void switchCall(int i,int j ,int time){
        mazeDisplayer.switchCell(i,j,time);
    }


    private void loadUI(String fxmlPage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UI/"+fxmlPage+".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void aboutPage(ActionEvent event ){
        loadUI("About");
    }

    @FXML
    void helpPage(ActionEvent event){
        loadUI("HelpPage");
    }
}