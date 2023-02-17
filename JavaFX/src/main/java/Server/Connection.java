package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection implements Runnable{

	private final Server server;
	private final ServerSocket serverSocket;

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


			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}