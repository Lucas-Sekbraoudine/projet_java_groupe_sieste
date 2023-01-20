package Client;
import Common.HashPwd;
import Models.UserModel;
import beans.User;
import javafx.application.Application;

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
		UserModel userModel = new UserModel();
		HashPwd hashPwd = new HashPwd();
		User user = new User("Lucas", "Antunes", "Twistere14", hashPwd.hashPassword("Salut"));
		userModel.init();
		userModel.createUser(user);
		userModel.readAllUser();
	}
	private static void printUsage() {
		System.out.println("java client.Client <address> <port>");
		System.out.println("\t<address>: server's ip address");
		System.out.println("\t<port>: server's port");
	}
}