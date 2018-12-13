package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class MazeDisplayer extends Canvas {
    private int[][] mazeData;
    private int cCol;
    private int cRow;

    public void setMazeData(int[][] mazeData) {
        this.mazeData = mazeData;
        cCol = 0;
        cRow = 0;
        redraw();
    }

    public void setPlayerPosition(int row,int col){
        cCol = col;
        cRow = row;
        redraw();
    }

    public void redraw(){
        if(mazeData != null) {
            double width = getWidth();
            double high = getHeight();
            double w = width / mazeData[0].length;
            double h = high / mazeData.length;

            GraphicsContext graphicsContext = getGraphicsContext2D();
            graphicsContext.clearRect(0,0,width,high);
            for (int i = 0; i < mazeData.length; i++) {
                for (int j = 0; j < mazeData[i].length; j++) {
                    if (mazeData[i][j] != 0) {
                        graphicsContext.fillRect(j * w, i * h, w, h);

                    }
                }
            }
            graphicsContext.setFill(Color.RED);
            graphicsContext.fillOval(cCol * w, cRow * h, w, h);

        }
    }


    public int getcCol() {
        return cCol;
    }

    public int getcRow() {
        return cRow;
    }

}
