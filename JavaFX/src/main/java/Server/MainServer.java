package Server;
import java.io.IOException;
/**
 * start a server. Reads the server's port from the command line argument
 * @author Remi Watrigant
 *
 */
public class MainServer {
	public static void main(String[] args) {
		try {

				Integer port = new Integer(3060);
				Thread threadServer = new Thread(new Server(port));
				threadServer.start();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private static void printUsage() {
		System.out.println("java server.Server <port>");
		System.out.println("\t<port>: server's port");
	}
}