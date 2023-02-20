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
	private boolean SearchGame = false;
	private int adversaire = -1;
	private int playerNumber = -1;
	private int currentPlayer = -1;
	private int coupAdversaire = -1;

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

	public void messageReceived(Message mess) throws IOException, ClassNotFoundException {

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
			//Si pas game ni de recherche de game on ouvre le scanner
			if (!this.inGame && !this.SearchGame) {
				System.out.println("search or exit");
				Scanner sc = new Scanner(System.in);
				String s = sc.nextLine();
				if (s.equals("exit")) {
					try {
						this.disconnectedServer();
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (s.equals("search")) {
					//envoie au serveur qu on cherche une game
					mess = new Message("search", s);
					try {
						if(!mess.getMess().isBlank()) {
							System.out.println("on envoie au serveur");
							out.flush();
							out.writeObject(mess);
							this.SearchGame = true;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if (this.SearchGame) {
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
						setPlayerNumber(Integer.parseInt(data[1]));
						setAdversaire(Integer.parseInt(data[2]));
						this.inGame = true;
						this.SearchGame = false;
						Thread ClientGame = new Thread(new ClientGame(this));
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
						resetGame();
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
		this.currentPlayer = -1;
		this.coupAdversaire = -1;
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

	public void changeCurrentPlayer() {
		if(this.currentPlayer == adversaire) {
			this.currentPlayer = playerNumber;
		} else {
			this.currentPlayer = adversaire;
		}
	}
}

