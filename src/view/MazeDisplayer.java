package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MazeDisplayer extends Canvas{
    private ArrayList<String> mazeData = null;
    private String max = "";
    private String themeName ="1";
    public int flag =0;

    public void switchCell(int i, int j, int times) {
        int t = 0;
        String fix;
        while (t < times) {
            switch (this.mazeData.get(i).charAt(j)) {

                case '|':
                    fix = dvirStringFix(i,j,'-');
                    mazeData.set(i,fix);
                    break;

                case '-':
                    fix = dvirStringFix(i,j,'|');
                    mazeData.set(i,fix);
                    break;

                case 'F':
                    fix = dvirStringFix(i,j,'7');
                    mazeData.set(i,fix);
                    break;

                case '7':
                    fix = dvirStringFix(i,j,'J');
                    mazeData.set(i,fix);
                    break;

                case 'J':
                    fix = dvirStringFix(i,j,'L');
                    mazeData.set(i,fix);
                    break;

                case 'L':
                    fix = dvirStringFix(i,j,'F');
                    mazeData.set(i,fix);
                    break;

                case 'g': {
                    flag = 1;
                }
                case 's': {
                    flag = 1;
                }
            }
            this.redraw();
            if (t < times - 1) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }
            ++t;
        }
    }

    private String dvirStringFix(int i,int j,char letter){
        StringBuilder dvirWhy = new StringBuilder(mazeData.get(i));
        dvirWhy.setCharAt(j, letter);
        return dvirWhy.toString();
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

            Image LimL = null;
            try {
                LimL = new Image(new FileInputStream("./resources/Theme_"+themeName+"/L.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image IimI = null;
            try {
                IimI = new Image(new FileInputStream("./resources/Theme_"+themeName+"/I.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image img1 = null;
            try {
                img1 = new Image(new FileInputStream("./resources/Theme_"+themeName+"/-.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            Image img7 = null;
            try {
                img7 = new Image(new FileInputStream("./resources/Theme_"+themeName+"/7.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image imgJ = null;
            try {
                imgJ = new Image(new FileInputStream("./resources/Theme_"+themeName+"/J.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image imgF = null;
            try {
                imgF = new Image(new FileInputStream("./resources/Theme_"+themeName+"/F.png"));
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
            Image back = null;
            try {
                back = new Image(new FileInputStream("./resources/back.jpeg"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            GraphicsContext graphicsContext = getGraphicsContext2D();
            graphicsContext.clearRect(0,0,width,high);

            // draw background
            graphicsContext.drawImage(back, 0, 0, getWidth(), getHeight());

            for (int i = 0;i<mazeData.size();i++)
            {
                for (int j = 0;j<mazeData.get(i).length();j++)
                {
                    letter = mazeData.get(i).charAt(j);
                    if(letter=='-')
                    {
                        graphicsContext.drawImage(img1,j*w,i*h,w,h);
                    }
                    if(letter=='|')
                    {
                        graphicsContext.drawImage(IimI,j*w,i*h,w,h);
                    }
                    if(letter=='L')
                    {
                        graphicsContext.drawImage(LimL,j*w,i*h,w,h);
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
            //System.out.println("Redraw!!!");
            //graphicsContext.setFill(Color.RED);

        }
    }

    public ArrayList<String> getMazeData() {
        return mazeData;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
        redraw();
    }

}
