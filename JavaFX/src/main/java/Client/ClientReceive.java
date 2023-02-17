package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Common.Message;

public class ClientReceive implements Runnable{
	private Client client;
	private Socket socket;
	private ObjectInputStream in;
	private String adversaire = "";
	private int playerNumber = -1;
	private int startPlayer = -1;
	private int coupAdversaire = -1;
	private String ResPartie = "";

	
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
				if(mess.getAction() == "Start"){
					String[] data = mess.getMess().split(";");
					//TODO : Quand BDD ok
					// setAdversaire(data[0]);
					setStartPlayer(Integer.parseInt(data[0]));
					setPlayerNumber(Integer.parseInt(data[1]));
				} else if(mess.getAction() == "Play") {
					setCoupAdversaire(Integer.parseInt(mess.getMess()));
				} else if (mess.getAction() == "Game") {
					setResPartie(mess.getMess());
				}
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

	public void setAdversaire(String adversaire) {
		this.adversaire = adversaire;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public void setStartPlayer(int startPlayer) {
		this.startPlayer = startPlayer;
	}

	public void setCoupAdversaire(int coupAdversaire) {
		this.coupAdversaire = coupAdversaire;
	}

	public void setResPartie(String resPartie) {
		ResPartie = resPartie;
	}

	
}
