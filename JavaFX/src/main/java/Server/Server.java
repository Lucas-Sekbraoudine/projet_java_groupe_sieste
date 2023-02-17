package Server;

import java.util.List;

import Common.Message;

import java.io.IOException;
import java.util.ArrayList;

public class Server implements Runnable{
	private int port;
	private List<ConnectedClient> clients;

	private List<ConnectedClient> clientsInGame;
	
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

	@Override
	public void run() {
		while (true) {
			try {
				//Verifie si il y a 2 joueurs en recherche de partie
				List<ConnectedClient> clientsSearchGame = new ArrayList<ConnectedClient>();
				for(ConnectedClient client : clients) {
					if(client.isSearchingGame()) {
						clientsSearchGame.add(client);
					}
					if (clientsSearchGame.size() >= 2) {
						break;
					}
				}
				//Si oui lance un thread de partie
				if (clientsSearchGame.size() >= 2) {
					Thread threadGame = new Thread(new Game(clientsSearchGame.get(0), clientsSearchGame.get(1)));
					threadGame.start();
					clientsSearchGame.get(0).setSearchingGame(false);
					clientsSearchGame.get(1).setSearchingGame(false);
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
