package Affichage;

import java.io.IOException;

import Client.Client;
import Client.ClientPanel;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGui extends Application {

	static ClientPanel clientView;
	
	public static void main(String[] args) {
		Client client;
		clientView = new ClientPanel();
		try {
			client = new Client("127.0.0.1", 3030);
			client.setView(clientView);
			clientView.setClient(client);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		Application.launch(MainGui.class, args);
		
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Chat Java");
		Group root = new Group();
		
		
		root.getChildren().add(clientView);
		Scene scene = new Scene(root, 600, 500);
		primaryStage.setTitle("Mon application");
		primaryStage.setScene(scene);
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
