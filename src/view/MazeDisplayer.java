package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MazeDisplayer extends Canvas {
    private ArrayList<String> mazeData;
    private int cCol;
    private int cRow;

    public void setMazeData(ArrayList<String> mazeData) {
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
            String max = "";
            for (int i = 0;i<mazeData.size();i++)
            {
                if(mazeData.get(i).length()>max.length())
                {
                    max=mazeData.get(i);
                }
            }
            double width = getWidth();
            double high = getHeight();
            double w = width / max.length();
            double h = high / mazeData.size();
            char letter;
            GraphicsContext graphicsContext = getGraphicsContext2D();
            graphicsContext.clearRect(0,0,width,high);
            for (int i = 0;i<mazeData.size();i++)
            {
                for (int j = 0;j<mazeData.get(i).length();j++)
                {
                    letter = mazeData.get(i).charAt(j);
                    if(letter=='|')
                    {
                        graphicsContext.fillRect(j*w,i*h,w,h);
                    }
                    if(letter=='-')
                    {
                        graphicsContext.fillRect(j*w,i*h,w,h);
                    }
                    if(letter=='L')
                    {
                        graphicsContext.fillRect(j*w,i*h,w,h);
                    }
                    if(letter=='S')
                    {
                        graphicsContext.fillRect(j*w,i*h,w,h);
                    }
                    if(letter=='G')
                    {
                        graphicsContext.fillRect(j*w,i*h,w,h);
                    }
                }
            }
            graphicsContext.setFill(Color.RED);
            graphicsContext.fillOval(cCol * w, cRow * h, w, h);

        }
    }

    public ArrayList<String> getMazeData() {
        return mazeData;
    }

    public int getcCol() {
        return cCol;
    }

    public int getcRow() {
        return cRow;
    }

}
