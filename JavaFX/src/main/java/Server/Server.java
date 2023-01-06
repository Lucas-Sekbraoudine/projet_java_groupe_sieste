package Server;

import java.net.Socket;
import java.util.List;

import Common.Message;

import java.io.IOException;
import java.util.ArrayList;

public class Server {
	private int port;
	private List<ConnectedClient> clients;
	
	Server(int port) throws IOException{
		this.port = port;
		this.clients = new ArrayList<ConnectedClient>();
		Thread threadConnection = new Thread(new Connection(this));
		threadConnection.start();
	}
	
	public int getPort() { return this.port; }
	public int getNumClients() { return this.clients.size(); }
	
	
	public void addClient(ConnectedClient newClient) throws IOException {
		this.clients.add(newClient);
		broadcastMessage(new Message("Server", newClient.getId()+" vient de se connecter"), newClient.getId());
		
	}
	
	public void broadcastMessage(Message mess, int id) throws IOException {
		
		for (ConnectedClient client : clients) {
			if (client.getId() != id) {
				client.sendMessage(mess);
			}
		}
	}
	
	public void disconnectedClient(ConnectedClient discClient) throws IOException {
		discClient.closeClient();
		clients.remove(discClient);
		broadcastMessage(new Message("Server",discClient.getId()+" vient de se déconnecter"), discClient.getId());
	}

	public Socket getSocket(int num) {
		return clients.get(num).getSocket();
	}
	
}
