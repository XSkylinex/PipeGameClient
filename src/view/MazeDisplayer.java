package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
            Image Limg = null;
            try {
                Limg = new Image(new FileInputStream("./resources/L.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image Iimg = null;
            try {
                Iimg = new Image(new FileInputStream("./resources/I.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image img1 = null;
            try {
                img1 = new Image(new FileInputStream("./resources/-.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image img7 = null;
            try {
                img7 = new Image(new FileInputStream("./resources/7.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imgJ = null;
            try {
                imgJ = new Image(new FileInputStream("./resources/7.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imgF = null;
            try {
                imgF = new Image(new FileInputStream("./resources/F.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imgstart = null;
            try {
                imgstart = new Image(new FileInputStream("./resources/S.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imgend = null;
            try {
                imgend = new Image(new FileInputStream("./resources/end.jpg"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            GraphicsContext graphicsContext = getGraphicsContext2D();
            graphicsContext.clearRect(0,0,width,high);
            for (int i = 0;i<mazeData.size();i++)
            {
                for (int j = 0;j<mazeData.get(i).length();j++)
                {
                    letter = mazeData.get(i).charAt(j);
                    if(letter=='|')
                    {
                        graphicsContext.drawImage(Iimg,j*w,i*h,w,h);
                    }
                    if(letter=='-')
                    {
                        graphicsContext.drawImage(img1,j*w,i*h,w,h);
                    }
                    if(letter=='L')
                    {
                        graphicsContext.drawImage(Limg,j*w,i*h,w,h);
                    }
                    if(letter=='7')
                    {
                        graphicsContext.drawImage(img7,j*w,i*h,w,h);
                    }
                    if(letter=='J')
                    {
                        graphicsContext.drawImage(imgJ,j*w,i*h,w,h);
                    }
                    if(letter=='F')
                    {
                        graphicsContext.drawImage(imgF,j*w,i*h,w,h);
                    }
                    if(letter=='s')
                    {
                        graphicsContext.drawImage(imgstart,j*w,i*h,w,h);
                    }
                    if(letter=='g')
                    {
                        graphicsContext.drawImage(imgend,j*w,i*h,w,h);
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
