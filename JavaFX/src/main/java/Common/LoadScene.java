package Common;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;


public class LoadScene {

    @FXML
    public void loadScene(String pathScene, Hyperlink currentScene, Object objectController) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pathScene));
        fxmlLoader.setController(objectController);
        Parent root = (Parent) fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.setTitle("Jacord");
        Stage currentStage = (Stage)currentScene.getScene().getWindow();
        currentStage.close();
        newStage.show();
    }
}
