package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Client.ClientPanel;
import Common.Message;



public class ConnectedClient implements Runnable{
	private static int idCounter = 0;
	private int id;
	private Server server;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ClientPanel view;



	private boolean isSearchingGame;
	
	public void setView(ClientPanel view) {
		this.view = view;
	}

	public ConnectedClient(Server server, Socket socket) throws IOException {
		this.server = server;
		this.socket = socket;
		id = idCounter++;
		out = new ObjectOutputStream(socket.getOutputStream());
		isSearchingGame = false;
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
					mess.setSender(String.valueOf(id));
					server.broadcastMessage(mess, id);
					
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

	public Socket getSocket() {
		return this.socket;
	}

	public boolean isSearchingGame() {
		return isSearchingGame;
	}

	public void setSearchingGame(boolean searchingGame) {
		isSearchingGame = searchingGame;
	}
	
}
