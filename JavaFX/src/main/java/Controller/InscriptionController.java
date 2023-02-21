package Controller;

import Common.HashPwd;
import Common.LoadScene;
import Models.UserModel;
import beans.User;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class InscriptionController {

    @FXML
    public Hyperlink connexion;

    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public TextField userName;

    @FXML
    public PasswordField passWord;

    @FXML
    public Text isUserExist;

    UserModel userModel = new UserModel();

    LoadScene loadScene = new LoadScene();


    @FXML
    public void handleConnexionPress() throws IOException {
        loadScene.loadScene("/fxml/Connexion.fxml", connexion, null, 600, 400);
    }

    @FXML
    public void handleInscriptionPress() throws NoSuchAlgorithmException, InvalidKeySpecException {
        try{
            User user = new User(firstName.getText(), lastName.getText(), userName.getText(), HashPwd.hashPassword(passWord.getText(), "123"));
            userModel.init();
            userModel.createUser(user);
            userModel.readAllUser();
            loadScene.loadScene("/fxml/Connexion.fxml", connexion, null, 600, 400);

        } catch (MongoWriteException e){
            if(e.getError().getCategory() == ErrorCategory.DUPLICATE_KEY){
                userName.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
                isUserExist.setFill(Color.rgb(174,34,34));
                isUserExist.setVisible(true);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
