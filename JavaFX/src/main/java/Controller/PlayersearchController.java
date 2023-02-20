package Controller;

import Client.Client;
import Common.LoadScene;
import Common.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.io.IOException;

public class PlayersearchController {

    Client client;

    LoadScene loadScene = new LoadScene();


    @FXML
    public Hyperlink playersearch;

    public PlayersearchController(Client client){
        this.client = client;
    }
    @FXML
    public void handlePlayerSearch(ActionEvent event) throws IOException {
        client.sendMessage((new Message("search", "search")));
        client.setSearchGame(true);
        loadScene.loadScene("/fxml/Gamescreen.fxml", playersearch, null);
    }

    public void initialize(){


    }
}
