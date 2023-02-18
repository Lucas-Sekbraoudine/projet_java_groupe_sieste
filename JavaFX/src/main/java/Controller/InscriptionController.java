package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class InscriptionController {

    @FXML
    public Hyperlink connexion;
    @FXML
    public void handleConnexionPress() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Connexion.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Jacord - Connexion");
        Stage currentStage = (Stage)connexion.getScene().getWindow();
        currentStage.close();
        newStage.show();
    }
}
