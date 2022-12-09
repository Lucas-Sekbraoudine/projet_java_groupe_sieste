package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Common.Message;



public class ClientReceive implements Runnable{
	private Client client;
	private Socket socket;
	private ObjectInputStream in;
	
	public ClientReceive(Client client, Socket socket) {
		this.client = client;
		this.socket = socket;
	}

	public void run() {
		Message mess = null;
		try {
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean isActive = true ;
		while(isActive) {
			try {
				mess = (Message) in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (mess != null) {
				this.client.messageReceived(mess);
			} else {
				isActive = false;
			}
		}
		try {
			client.disconnectedServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	

	
}
