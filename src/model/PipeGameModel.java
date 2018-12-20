package model;

import controller.PipeGameController;
import view.MazeDisplayer;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

public class PipeGameModel extends Observable {
    public PipeGameController controller;

    // connection method....

    public static void solve(String ip, int port, MazeDisplayer board) {
//        try {
//            String line;
//            Socket theServer = new Socket(ip, port);
//            PrintWriter out = new PrintWriter(theServer.getOutputStream());
//            String[][] data = board.getMazeData();
//            int i = 0;
//            while (i < data.length) {
//                out.println(new String(String.valueOf(data[i])));
//                ++i;
//            }
//            out.println("done");
//            out.flush();
//            BufferedReader in = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
//            while (!(line = in.readLine()).equals("done")) {
//                int i2 = Integer.parseInt(line.split(",")[0]);
//                int j = Integer.parseInt(line.split(",")[1]);
//                int times = Integer.parseInt(line.split(",")[2]);
//                board.switchCell(i2, j, times);
//            }
//            in.close();
//            out.close();
//            theServer.close();
//        }
//        catch (IOException e) {
//            JOptionPane.showMessageDialog(board, e.getMessage());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // when we the sol from sever we notify to our observer.
       // this.notifyObservers("solution....");
    }
}
