package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection implements Runnable{

	private Server server;
	private ServerSocket serverSocket;
	
	public Connection(Server server) throws IOException {
		this.server = server;
		this.serverSocket = new ServerSocket(server.getPort());
	}
	
	public void run() {
		while(true) {
			try {
				Socket sockNewClient = serverSocket.accept();
				
				ConnectedClient newClient = new ConnectedClient(server, sockNewClient);
				newClient.setId(server.getNumClients());
				server.addClient(newClient);
				Thread threadNewClient = new Thread(newClient);
				threadNewClient.start();
				if (server.getNumClients()%2 == 0 && server.getNumClients() != 0) {
					//recuperer les deux derniers clients avec leurs sockets dans une liste
					Socket[] sockets = new Socket[2];
					sockets[0] = server.getSocket(server.getNumClients()-2);
					sockets[1] = server.getSocket(server.getNumClients()-1);
					ServerPartie partie = new ServerPartie(sockets);
					Thread threadPartie = new Thread(partie);
					threadPartie.start();
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
