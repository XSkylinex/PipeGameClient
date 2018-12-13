package view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    int[][] mazeData = { {1,1,0,0,1,1,1,1,1},
                         {1,1,1,0,1,1,1,1,1},
                         {1,1,1,0,1,1,1,1,1},
                         {1,1,1,0,1,1,1,1,1},
                         {1,1,1,0,0,1,1,1,1},
                         {1,1,1,1,0,0,0,0,0},
                         {1,1,1,1,1,1,1,1,0} };
    @FXML
    MazeDisplayer mazeDisplayer;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mazeDisplayer.setMazeData(mazeData);
        mazeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)-> mazeDisplayer.requestFocus() );
        mazeDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                int row = mazeDisplayer.getcRow();
                int col = mazeDisplayer.getcCol();
                if(event.getCode() == KeyCode.UP){
                    mazeDisplayer.setPlayerPosition(row-1,col);
                }
                if(event.getCode() == KeyCode.DOWN){
                    mazeDisplayer.setPlayerPosition(row+1,col);
                }
                if(event.getCode() == KeyCode.LEFT){
                    mazeDisplayer.setPlayerPosition(row,col-1);
                }
                if(event.getCode() == KeyCode.RIGHT){
                    mazeDisplayer.setPlayerPosition(row,col+1);
                }
            }
        });
    }
    public void start(){
        System.out.println("Start Pipe game");
    }

    public void OpenFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Level");
        fileChooser.setInitialDirectory(new File("./resources"));
        File choose = fileChooser.showOpenDialog(null);
        if(choose != null){
            System.out.println("Not Found");
        }
    }


}
