package Controller;

import Client.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.format.TextStyle;

public class GamescreenController {

    @FXML
    public Text your_turn;

    @FXML
    public Text not_your_turn;
    Client client;

    public GamescreenController(Client client){

        this.client = client;

        Platform.runLater(() -> {
            Thread t = new Thread(() -> {
                while (true){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (client.getPlayerNumber() == client.getCurrentPlayer()) {
                        your_turn.setVisible(true);
                        not_your_turn.setVisible(false);
                    } else if (client.getCurrentPlayer() == client.getAdversaire()){
                        your_turn.setVisible(false);
                        not_your_turn.setVisible(true);
                    }
                }
            });
            t.start();
        });
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

    @FXML
    public void setTurnVisible(){
        if(client.getPlayerNumber() == client.getCurrentPlayer()){
            your_turn.setVisible(true);
            not_your_turn.setVisible(false);
        } else {
            your_turn.setVisible(false);
            not_your_turn.setVisible(true);
        }
    }

}
