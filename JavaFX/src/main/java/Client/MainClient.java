package Client;
import Common.HashPwd;
import Models.UserModel;
import beans.User;
import javafx.application.Application;
import java.util.Scanner;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * starts a client. Reads the address and port from the command line argument
 * @author Remi Watrigant
 *
 */
public class MainClient{
	/**
	 * construct a new client
	 * @param args
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {;
	}
	private static void printUsage() {
		System.out.println("java client.Client <address> <port>");
		System.out.println("\t<address>: server's ip address");
		System.out.println("\t<port>: server's port");
	}
}