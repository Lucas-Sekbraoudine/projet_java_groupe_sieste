package Common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Server.ConnectedClient;

public class Game implements Runnable {
    private ConnectedClient client1;
    private ConnectedClient client2;
    private ObjectOutputStream out1;
    private ObjectOutputStream out2;
    private ObjectInputStream in1;
    private ObjectInputStream in2;
    private int[][] grid;
    private int currentPlayer;
    private boolean gameOver;

    public Game(ConnectedClient client1, ConnectedClient client2) throws IOException {
        this.client1 = client1;
        this.client2 = client2;
        out1 = new ObjectOutputStream(client1.getSocket().getOutputStream());
        out2 = new ObjectOutputStream(client2.getSocket().getOutputStream());
        in1 = new ObjectInputStream(client1.getSocket().getInputStream());
        in2 = new ObjectInputStream(client2.getSocket().getInputStream());
        grid = new int[6][7];
        currentPlayer = 1;
        gameOver = false;
    }

    public void run() {
        while (!gameOver) {
            try {
                if (currentPlayer == 1) {
                    Message mess = (Message) in1.readObject();
                    if (mess.getAction().equals("MOVE")) {
                        int column = Integer.parseInt(mess.getData());
                        int row = getNextOpenRow(column);
                        grid[row][column] = 1;
                        currentPlayer = 2;
                        Message response = new Message("MOVE", row + " " + column, "SERVER");
                        out2.writeObject(response);
                        out2.flush();
                    }
                } else if (currentPlayer == 2) {
                    Message mess = (Message) in2.readObject();
                    if (mess.getAction().equals("MOVE")) {
                        int column = Integer.parseInt(mess.getData());
                        int row = getNextOpenRow(column);
                        grid[row][column] = 2;
                        currentPlayer = 1;
                        Message response = new Message("MOVE", row + " " + column, "SERVER");
                        out1.writeObject(response);
                        out1.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
