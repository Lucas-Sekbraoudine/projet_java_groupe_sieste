package Controller;

import Client.Client;
import javafx.fxml.FXML;

public class GamescreenController {

    Client client;

    public GamescreenController(Client client){
        this.client = client;
    }

    @FXML
    public void handleButton1(){
        if(client.getPlayerNumber() == client.getCurrentPlayer()){
            client.setCoupJoueur(0);
        }
    }

    @FXML
    public void handleButton2(){
        if(client.getPlayerNumber() == client.getCurrentPlayer()){
            client.setCoupJoueur(1);
        }
    }

    @FXML
    public void handleButton3(){
        if(client.getPlayerNumber() == client.getCurrentPlayer()){
            client.setCoupJoueur(2);
        }
    }

    @FXML
    public void handleButton4(){
        if(client.getPlayerNumber() == client.getCurrentPlayer()){
            client.setCoupJoueur(3);
        }
    }

    @FXML
    public void handleButton5(){
        if(client.getPlayerNumber() == client.getCurrentPlayer()){
            client.setCoupJoueur(4);
        }
    }

    @FXML
    public void handleButton6(){
        if(client.getPlayerNumber() == client.getCurrentPlayer()){
            client.setCoupJoueur(5);
        }
    }

    @FXML
    public void handleButton7(){
        if(client.getPlayerNumber() == client.getCurrentPlayer()){
            client.setCoupJoueur(6);
        }
    }


}
