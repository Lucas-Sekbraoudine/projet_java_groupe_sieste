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
	private int startPlayer = -1;
	private int coupAdversaire = -1;
	private String ResPartie = "";
	
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
		/*Thread clientReceive = new Thread(new ClientReceive(this, socket));
		clientReceive.start();*/

		
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

	public void run(){
		//Si pas game ni de recherche de game on ouvre le scanner
		if(!this.inGame && !this.SearchGame) {
			System.out.println("search or exit");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			if (s.equals("exit")) {
				try {
					this.disconnectedServer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (s.equals("search")) {
				//envoie au serveur qu on cherche une game
				this.SearchGame = true;
			}
		} else if(this.SearchGame){

		} else if (this.inGame){

		}

	}
	
}
