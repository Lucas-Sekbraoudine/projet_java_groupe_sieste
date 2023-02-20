package Controller;

import Client.Client;
import Common.LoadScene;
import Models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ConnexionController {

    @FXML
    public TextField userName;

    @FXML
    public PasswordField passWord;

    @FXML
    public Hyperlink inscription;

    LoadScene loadScene = new LoadScene();



    public ConnexionController() throws IOException {
    }

    @FXML
    public TextField handleConnexionPress(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        UserModel userModel = new UserModel();
        userModel.init();
        userModel.loginUser(userName.getText(), passWord.getText());
        Client client = new Client("127.0.0.1", 3060);
        Thread threadClient = new Thread(client);
        threadClient.start();
        loadScene.loadScene("/fxml/Accueil.fxml", inscription);
        return userName;
    }

    @FXML
    public void handleInscriptionPress(ActionEvent event) throws IOException {
        loadScene.loadScene("/fxml/Inscription.fxml", inscription);
    }
}
