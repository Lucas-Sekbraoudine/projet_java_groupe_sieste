package Controller;

import Client.Client;
import Common.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PlayersearchController {

    Client client;

    public PlayersearchController(Client client){
        this.client = client;
    }
    @FXML
    public void handlePlayerSearch(ActionEvent event) throws IOException {
        client.sendMessage((new Message("search", "search")));

    }
}
