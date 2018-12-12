package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    int[][] mazeData = {{1,0,1,1,1,1,1,1,1,1,1,1},
                        {1,1,0,1,1,1,1,1,1,1,1,1},
                        {1,1,1,0,1,1,1,1,1,1,1,1},
                        {1,1,1,0,1,1,1,1,1,1,1,1},
                        {1,1,1,0,1,1,1,1,1,1,1,1},
                        {1,1,1,0,1,1,1,1,1,1,1,1},
                        {1,1,1,0,1,1,1,1,1,1,1,1},
                        {1,1,1,0,0,0,0,0,1,1,1,1},
                        {1,1,1,1,1,1,1,0,1,1,1,1},
                        {1,1,1,1,1,1,1,0,0,0,0,0}
    };



    public void seteEnd(int rEnd) {
        this.rEnd = rEnd;
    }

    int cStart;
    int rEnd;

    @FXML
    MazeDisplayer mazeDisplayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mazeDisplayer.setMazeData(mazeData);
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

    void setPlayer(int row,int col){
        this.cStart = col;
        this.rEnd = row;
    }
    public int getcStart() {
        return cStart;
    }

    public void setcStart(int cStart) {
        this.cStart = cStart;
    }

    public int geteEnd() {
        return rEnd;
    }

}
