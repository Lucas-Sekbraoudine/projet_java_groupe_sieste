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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnexionController {

    @FXML
    public TextField userName;

    @FXML
    public PasswordField passWord;

    @FXML
    public Hyperlink inscription;

    @FXML
    public Text invalidCredentials;

    LoadScene loadScene = new LoadScene();


    public ConnexionController() throws IOException {
    }

    @FXML
    public TextField handleConnexionPress(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        UserModel userModel = new UserModel();
        userModel.init();

        String[] isConnected = userModel.loginUser(userName.getText(), passWord.getText());

        if(isConnected[0].equals("true")){
            Client client = new Client("127.0.0.1", 3060);
            Thread threadClient = new Thread(client);
            threadClient.start();
            PlayersearchController playersearchController = new PlayersearchController(client);
            loadScene.loadScene("/fxml/Playersearch.fxml", inscription, playersearchController, 600, 400);
        } else if(isConnected[0].equals("false")){
            userName.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            passWord.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            invalidCredentials.setVisible(true);
            invalidCredentials.setFill(Color.rgb(174,34,34));
        }

        return userName;
    }

    @FXML
    public void handleInscriptionPress(ActionEvent event) throws IOException {
        loadScene.loadScene("/fxml/Inscription.fxml", inscription, null, 600, 400);
    }
}
