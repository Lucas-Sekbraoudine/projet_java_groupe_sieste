package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import Common.Message;
import Controller.AccueilController;

public class Client implements Runnable{
	private String address;
	private int port;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private AccueilController view;
	private boolean inGame = false;
	private char[][] board = null;
	private boolean winner = false;

	private boolean looser = false;

	private boolean SearchGame = false;
	private int adversaire = -1;
	private int playerNumber = -1;
	private int currentPlayer = -2;
	private int startPlayer = -1;
	private int coupAdversaire = -1;
	private int coupJoueur = -1;

	public void setView(AccueilController view) {
		this.view = view;
	}

	public Client(String address, int port) throws IOException {
		this.address = address;
		this.port = port;
		this.socket = new Socket(address, port);
		this.out = new ObjectOutputStream(this.socket.getOutputStream());
		try {
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnectedServer() throws IOException {
		if(out != null)
			out.close();
		if(socket != null)
			socket.close();
		if(in != null)
			in.close();
		System.exit(0);
	}

	public void messageReceived() throws IOException, ClassNotFoundException {
			Message mess = null;
		 mess = (Message) in.readObject();
		 System.out.println(mess);
	}

	public void sendMessage(Message mess) throws IOException {
		this.out.flush();
		this.out.writeObject(mess);
	}

	@Override
	public void run() {
		while (true) {
			Message mess = null;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}if (this.SearchGame) {

				try {
					mess = (Message) in.readObject();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				if (mess != null) {
					if (mess.getSender().equals("Start")) {
						System.out.println(mess.toString());
						String[] data = mess.getMess().split(";");
						setCurrentPlayer(Integer.parseInt(data[0]));
						setStartPlayer(Integer.parseInt(data[0]));
						setPlayerNumber(Integer.parseInt(data[1]));
						setAdversaire(Integer.parseInt(data[2]));
						this.inGame = true;
						this.SearchGame = false;
						Thread ClientGame = null;
						try {
							ClientGame = new Thread(new ClientGame(this));
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
						ClientGame.start();
					}
				} else {
					break;
				}
			} else if (this.inGame) {
				System.out.println("in game");
				try {
					mess = (Message) in.readObject();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				if (mess != null) {
					System.out.println(mess.toString());
					if(mess.getSender().equals("Play")) {
						setCoupAdversaire(Integer.parseInt(mess.getMess()));
					} else if (mess.getSender().equals("Game")) {
						System.out.println(mess.getMess());
						//Set winner in client
						if(mess.getMess().equals("win")) {
							this.winner = true;
							this.looser = false;
						} else if (mess.getMess().equals("lose")) {
							this.winner = false;
							this.looser = true;
						}
						this.inGame = false;
					}
				} else {
					break;
				}
			}

		}
	}

	public void setAdversaire(int adversaire) {
		this.adversaire = adversaire;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void setCoupAdversaire(int coupAdversaire) {
		this.coupAdversaire = coupAdversaire;
	}

	public void resetGame() {
		this.inGame = false;
		this.SearchGame = false;
		this.adversaire = -1;
		this.playerNumber = -1;
		this.currentPlayer = -2;
		this.coupAdversaire = -1;
		this.startPlayer = -1;
		this.winner = false;
		this.looser = false;
	}

	public boolean isInGame() {
		return inGame;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public int getCoupAdversaire() {
		return coupAdversaire;
	}

	public int getAdversaire() {
		return adversaire;
	}

	public boolean isSearchGame() {
		return SearchGame;
	}

	public void setSearchGame(boolean searchGame) {
		SearchGame = searchGame;
	}

	public void setCoupJoueur(int coupJoueur) {
		this.coupJoueur = coupJoueur;
	}

	public int getCoupJoueur() {
		return coupJoueur;
	}


	public void changeCurrentPlayer() {
		if(this.currentPlayer == adversaire) {
			this.currentPlayer = playerNumber;
		} else {
			this.currentPlayer = adversaire;
		}
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setStartPlayer(int startPlayer) {
		this.startPlayer = startPlayer;
	}

	public int getStartPlayer() {
		return startPlayer;
	}

	public boolean canDropToken(int column) {
		return column >= 0 && column < 7 && board[0][column] == ' ';
	}

	public boolean getWinner() {
		return winner;
	}

	public boolean getLooser() {
		return looser;
	}
}

