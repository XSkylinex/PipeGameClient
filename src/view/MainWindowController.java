package view;

import controller.PipeGameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.PipeGameModel;

import java.awt.event.MouseListener;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Scanner;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class MainWindowController extends Observable implements Initializable {
    PipeGameController controller;
    public String max = "";
    public ArrayList<String> mazeData = new ArrayList<String>();

    @FXML
    MazeDisplayer mazeDisplayer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controller = new PipeGameController(new PipeGameModel(), this);
        mazeDisplayer.setMazeData(mazeData);
        mazeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> mazeDisplayer.requestFocus());
    }

    public void solve() {
        System.out.println("Start Pipe game");

    }

    public void OpenFile() throws IOException {
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

    public ArrayList<String> readMaze(File Maze) throws IOException {
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
            gameboard.add("done");
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
}
