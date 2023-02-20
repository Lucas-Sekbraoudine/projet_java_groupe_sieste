package Affichage;

import Client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class MainGui extends Application {

	public static void main(String[] args) {

		Application.launch(MainGui.class, args);


	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Connexion.fxml"));
		primaryStage.setTitle("Jacord - Connexion");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
	}
}
