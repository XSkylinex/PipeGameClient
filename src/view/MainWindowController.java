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

    private String[][] mazeData;

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
        File choose = fileChooser.showOpenDialog(null);
        System.out.println(choose.getPath());
        if (choose != null) {
            mazeData = readMaze(choose.getPath());
        } else {
            System.out.println("Not Found");

        }
    }

    public String[][] readMaze(String fileName) throws IOException {
        String[][] a = null;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        return a;
    }
}