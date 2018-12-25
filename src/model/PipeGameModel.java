package model;

import controller.PipeGameController;
import view.MazeDisplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

public class PipeGameModel extends Observable {
    public PipeGameController controller;

    private Socket theServer = null;

    // connection method....
    public void PipeGameModel(String ip, int port) throws IOException {

        System.out.println("Creating socket to '" + ip + "' on port " + port);
        theServer = new Socket(ip, port);
        if(theServer == null)
        {
            System.err.println("never to be printed!!!!");
            throw new IOException("no connection astablse");
        }
        if(theServer.isClosed())
        {
            throw new IOException("server is close");
        }
        setChanged();
        notifyObservers("update");

    }

    public void solve(MazeDisplayer board) throws IOException {
        if(theServer == null) {
            System.err.println("never to be printed!!!!");
            throw new IOException("no connection established");
        }
        if(theServer.isClosed()) {
            throw new IOException("server is close");
        }
        if(board.getMazeData().isEmpty()){
            setChanged();
            notifyObservers("Error");
            throw new IOException("board is empty");

        }
        try {
            String line;
            PrintWriter out = new PrintWriter(theServer.getOutputStream());
            ArrayList data = board.getMazeData();

            int i = 0;
            while (i < data.size()) {
                out.println(data.get(i));
                ++i;
            }
            out.println("done");
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
            while (!(line = in.readLine()).equals("done")) {
                int i2 = Integer.parseInt(line.split(",")[0]);
                int j = Integer.parseInt(line.split(",")[1]);
                int times = Integer.parseInt(line.split(",")[2]);
                //setChanged();
                //notifyObservers("switch");
                board.switchCell(i2, j, times);
            }
            in.close();
            out.close();
            theServer.close();
        }
        catch (IOException e) {
            //JOptionPane.showMessageDialog(board, e.getMessage());
        //} catch (UnknownHostException e) {
       //     e.printStackTrace();
       // } catch (IOException e) {
       //     e.printStackTrace();
        }

        // when we the sol from sever we notify to our observer.
        this.notifyObservers("solution....");
    }

    private String[][] convertDvirBlagan(ArrayList Data){
        String[][] array = new String[Data.size()][];
        for (int i = 0; i < Data.size(); i++) {
            ArrayList<String> row = (ArrayList<String>) Data.get(i);
            array[i] = row.toArray(new String[row.size()]);
        }
        return array;

    }
}
