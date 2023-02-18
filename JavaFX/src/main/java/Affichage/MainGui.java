package Affichage;

import java.io.IOException;

import Client.Client;
import Client.ClientPanel;
import Models.UserModel;
import beans.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class MainGui extends Application {

	static ClientPanel clientView;
	
	public static void main(String[] args) {
		Client client;
		clientView = new ClientPanel();


		//client = new Client("127.0.0.1", 3030);
		//client.setView(clientView);
		//clientView.setClient(client);


		Application.launch(MainGui.class, args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Connexion.fxml"));
		primaryStage.setTitle("Jacord - Connexion");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
		
		/*
		Scene scene = new Scene(root);
		scene.setFill(Color.RED);
		Text text = new Text(10, 30, "Hello world");
		root.getChildren().add(text);
		primaryStage.setScene(scene);
		primaryStage.show();
		*/
	}

}
