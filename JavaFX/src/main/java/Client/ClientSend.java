package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import Common.Message;

public class ClientSend implements Runnable{
	private Socket socket;
	private ObjectOutputStream out;
	
	public ClientSend(Socket socket, ObjectOutputStream out) {
		this.socket = socket;
		this.out = out;
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		Message mess = new Message("client","");
		boolean exit = true;
		while (exit) {			
			System.out.print("Votre message (tapez \"exit\" pour quitter le programme) >> ");
			String m = sc.nextLine();
			System.out.println("Mess : " + m);
			exit = "exit".equals(m);
				
			mess = new Message("client", m);
			
			try {
				if(!mess.getContent().isBlank()) {
					out.writeObject(mess);
					out.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	
	
	
	
	
}
