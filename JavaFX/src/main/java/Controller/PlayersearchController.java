package Controller;

import Client.Client;
import Common.LoadScene;
import Common.Message;
import javafx.application.Platform;
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
        if (!client.isSearchGame()){
            client.sendMessage((new Message("search", "search")));
            client.setSearchGame(true);
        }
        GamescreenController gamescreenController = new GamescreenController(client);
        loadScene.loadScene("/fxml/Gamescreen.fxml", playersearch, gamescreenController);
    }



    public void initialize(){


    }
}
