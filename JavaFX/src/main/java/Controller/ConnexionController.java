package Controller;

import Models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ConnexionController {

    @FXML
    public TextField userName;

    @FXML
    public TextField passWord;

    @FXML
    public Hyperlink inscription;

    @FXML
    public  void handleConnexionPress(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserModel userModel = new UserModel();
        userModel.init();
        userModel.loginUser(userName.getText(), passWord.getText());

    }

    @FXML
    public void handleInscriptionPress(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Inscription.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Jacord - Inscription");
        Stage currentStage = (Stage)inscription.getScene().getWindow();
        currentStage.close();
        newStage.show();
    }
}
