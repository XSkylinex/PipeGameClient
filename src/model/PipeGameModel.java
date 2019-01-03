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
    //public static boolean alreadySolve = false;
    // connection method....
    public void connectToServer(String ip, int port) throws IOException {

        System.out.println("Creating socket to '" + ip + "' on port " + port);
        theServer = new Socket(ip, port);
        if(theServer.isClosed())
        {
            throw new IOException("server is close");
        }
        setChanged();
        notifyObservers("update");

    }
    public boolean mazeCheck(MazeDisplayer board) throws IOException
    {
        MazeDisplayer tBoard = new MazeDisplayer();
        tBoard.setMazeData(board.getMazeData());
        this.solve(tBoard);
        return tBoard.getMazeData() == board.getMazeData();
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
            PrintWriter out = new PrintWriter(theServer.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
            ArrayList<String> data = board.getMazeData();


            for(String l : data) {
                out.println(l);
                System.out.println(l);
            }
            out.println("done");
            out.flush();

            new Thread(()->{
                while(!Thread.currentThread().isInterrupted()) {
                    try {
                        String _line;
                        while (!(_line = in.readLine()).equals("done")) {
                            int i2 = Integer.parseInt(_line.split(",")[0]);
                            int j = Integer.parseInt(_line.split(",")[1]);
                            int times = Integer.parseInt(_line.split(",")[2]);
                            board.switchCell(i2, j, times);
                            Thread.sleep(100);
                        }
                        Thread.currentThread().interrupt();
                    }catch(InterruptedException | IOException ignored){}
                }
            }).start();
//            in.close();
//            out.close();
//            theServer.close();
        }
        catch (IOException ignored) {
        }

        // when we the sol from sever we notify to our observer.
        this.notifyObservers("solution....");
    }

    public void disconnectFromServer() throws IOException{
        if(theServer.isClosed()) {
            throw new IOException("server is close");
        }
        PrintWriter out = new PrintWriter(theServer.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
        in.close();
        out.close();
        theServer.close();
    }



}
