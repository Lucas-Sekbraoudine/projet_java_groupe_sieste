package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Common.Message;

public class Client {
	private String address;
	private int port;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ClientPanel view;
	
	public void setView(ClientPanel view) {
		this.view = view;
	}

	public Client(String address, int port) throws IOException {
		this.address = address;
		this.port = port;
		this.socket = new Socket(address, port);
		this.out = new ObjectOutputStream(this.socket.getOutputStream());
		
		/*Thread clientSend = new Thread(new ClientSend(socket, out));
		clientSend.start();*/
		Thread clientReceive = new Thread(new ClientReceive(this, socket));
		clientReceive.start();
		
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
		// TODO Auto-generated method stub
		this.out.flush();
		this.out.writeObject(mess);
	}
	
	
}
