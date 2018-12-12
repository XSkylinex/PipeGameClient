package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;

public class MazeDisplayer extends Canvas {
    int[][] mazeData;

    public void setMazeData(int[][] mazeData) {
        this.mazeData = mazeData;
        redraw();
    }

    public void redraw(){
        if(mazeData != null){
           double width = getWidth();
           double high = getHeight();
           double w = width/mazeData[0].length;
           double h = high/mazeData.length;

            GraphicsContext graphicsContext = getGraphicsContext2D();


           for(int i= 0 ; i<mazeData.length ; i++){
               for(int j = 0 ; j < mazeData[i].length ; j++){
                   if(mazeData[i][j]!=0){
                       graphicsContext.fillRect(j*w,i*h,w,h);
                   }
               }
           }

        }
    }
}
