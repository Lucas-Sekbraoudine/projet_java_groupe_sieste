package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Common.Message;

public class Client implements Runnable{
	private String address;
	private int port;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ClientPanel view;
	private boolean inGame = false;
	private boolean SearchGame = false;
	private String adversaire = "";
	private int playerNumber = -1;
	private int currentPlayer = -1;
	private int coupAdversaire = -1;
	
	public void setView(ClientPanel view) {
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
	
	public void messageReceived(Message mess) {
		view.printNewMessage(mess);
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
					if (mess.getAction().equals("Start")) {
						System.out.println(mess.toString());
						String[] data = mess.getMess().split(";");
						//TODO : Quand BDD ok
						// setAdversaire(data[0]);
						setCurrentPlayer(Integer.parseInt(data[0]));
						setPlayerNumber(Integer.parseInt(data[1]));
						this.inGame = true;
						this.SearchGame = false;
						Thread ClientGame = new Thread(new ClientGame(this));
						ClientGame.start();
					}
				} else {
					break;
				}
			} else if (this.inGame) {
				try {
					mess = (Message) in.readObject();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				if (mess != null) {
					if(mess.getAction().equals("Play")) {
						setCoupAdversaire(Integer.parseInt(mess.getMess()));
					} else if (mess.getAction().equals("Game")) {
						System.out.println(mess.getMess());
						resetGame();
					}
				} else {
					break;
				}
			}

		}
	}

	public void setAdversaire(String adversaire) {
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
		this.adversaire = "";
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
}
	

