package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Common.Message;



public class ConnectedClient implements Runnable{
	private static int idCounter = 0;
	private int id;
	private final Server server;
	private final Socket socket;
	private final ObjectOutputStream out;
	private ObjectInputStream in;
	private int Move = -1;
	private boolean isSearchingGame = false;
	private String message = null;

	public ConnectedClient(Server server, Socket socket) throws IOException {
		this.server = server;
		this.socket = socket;
		id = idCounter++;
		out = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("Nouvelle connexion, id = " + id);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void run() {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			boolean isActive = true;
			while (isActive) {

				Message mess = (Message) in.readObject();
				if(mess != null) {
					if (mess.getSender().equals("move")) {
						System.out.println("Client " + this.getId() + " move " + mess.getMess());
						this.setMove(Integer.parseInt(mess.getMess()));
					}
					else if (mess.getSender().equals("search")) {
						this.setSearchingGame(true);
						System.out.println("Client " + this.getId() + " is searching game");
					}
					else if (mess.getSender().equals("stopSearchGame")) {
						this.setSearchingGame(false);
						System.out.println("Client " + this.getId() + " stop searching game");
					}
					else if (mess.getSender().equals("disconnect")) {
						server.disconnectedClient(this);
						isActive = false;
					}else if(mess.getSender().equals("message")){
						this.setMessage(mess.getMess());
						System.out.println("Client " + this.getId() + " send message : " + mess.getMess());
					} else {
						System.out.println("Message vide");
						System.out.println(mess.toString());
					}
				}else {
					server.disconnectedClient(this);
					isActive = false;
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendMessage(Message mess) throws IOException{
		this.out.flush();
		this.out.writeObject(mess);
	}

	public void closeClient() throws IOException {
		this.in.close();
		this.out.close();
		this.socket.close();
	}

	public boolean isSearchingGame() {
		return isSearchingGame;
	}

	public void setSearchingGame(boolean searchingGame) {
		isSearchingGame = searchingGame;
	}

	public int getMove() {
		return Move;
	}

	public void setMove(int move) {
		Move = move;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}