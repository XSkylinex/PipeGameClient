package view;

import controller.PipeGameController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.PipeGameModel;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainWindowController extends Observable implements Initializable {
    PipeGameController controller;

    public ArrayList<String> mazeData = new ArrayList<String>();
    @FXML
    MazeDisplayer mazeDisplayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controller = new PipeGameController(new PipeGameModel(), this);
        mazeDisplayer.setMazeData(mazeData);
        mazeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> mazeDisplayer.requestFocus());
        mazeDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                int row = mazeDisplayer.getcRow();
                int col = mazeDisplayer.getcCol();
                if (event.getCode() == KeyCode.UP) {
                    mazeDisplayer.setPlayerPosition(row - 1, col);
                }
                if (event.getCode() == KeyCode.DOWN) {
                    mazeDisplayer.setPlayerPosition(row + 1, col);
                }
                if (event.getCode() == KeyCode.LEFT) {
                    mazeDisplayer.setPlayerPosition(row, col - 1);
                }
                if (event.getCode() == KeyCode.RIGHT) {
                    mazeDisplayer.setPlayerPosition(row, col + 1);
                }
            }
        });
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
            while (streader!=null)
            {
                gameboard.add(streader);
                streader = buff.readLine();
            }
            gameboard.add("done");
            return  gameboard;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}