package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class MazeDisplayer extends Canvas {
    private ArrayList<String> mazeData;
    public String max = "";




    public void switchCell(int i, int j, int times) {
        int t = 0;
        while (t < times) {
            switch (this.mazeData.get(i).charAt(j)) {
                case '-': {
                   // this.mazeData.get(i).charAt(j) = '|';
                    char[] clickmousenow = this.mazeData.get(i).toCharArray();
                    clickmousenow[j] = '|';
                    mazeData.add(j,clickmousenow.toString());
                    break;
                }
                case '|': {
                    char[] clickmousenow = this.mazeData.get(i).toCharArray();
                    clickmousenow[j] = '-';
                    mazeData.add(j,clickmousenow.toString());
                    break;
                }
                case '7': {
                    char[] clickmousenow = this.mazeData.get(i).toCharArray();
                    clickmousenow[j] = 'J';
                    mazeData.add(j,clickmousenow.toString());
                    break;
                }
                case 'J': {
                    char[] clickmousenow = this.mazeData.get(i).toCharArray();
                    clickmousenow[j] = 'L';
                    mazeData.add(j,clickmousenow.toString());
                    break;
                }
                case 'L': {
                    char[] clickmousenow = this.mazeData.get(i).toCharArray();
                    clickmousenow[j] = 'F';
                    mazeData.add(j,clickmousenow.toString());
                    break;
                }
                case 'F': {
                    char[] clickmousenow = this.mazeData.get(i).toCharArray();
                    clickmousenow[j] = '7';
                    mazeData.add(j,clickmousenow.toString());
                    break;                }
            }
            this.redraw();
            if (t < times - 1) {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }
            ++t;
        }
    }

    public void setMazeData(ArrayList<String> mazeData) {
        this.mazeData = mazeData;
        redraw();
    }



    public void redraw(){
        if(mazeData != null) {

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

        }
    }

    public ArrayList<String> getMazeData() {
        return mazeData;
    }



}
