package Controller;

import Client.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.time.format.TextStyle;

public class GamescreenController {

    @FXML
    public ImageView rouge_1_1;
    @FXML
    public ImageView rouge_1_2;
    @FXML
    public ImageView rouge_1_3;
    @FXML
    public ImageView rouge_1_4;
    @FXML
    public ImageView rouge_1_5;
    @FXML
    public ImageView rouge_1_6;
    @FXML
    public ImageView rouge_3_1;
    @FXML
    public ImageView rouge_3_2;
    @FXML
    public ImageView rouge_3_3;
    @FXML
    public ImageView rouge_3_4;
    @FXML
    public ImageView rouge_3_5;
    @FXML
    public ImageView rouge_3_6;
    @FXML
    public ImageView rouge_2_1;
    @FXML
    public ImageView rouge_2_2;
    @FXML
    public ImageView rouge_2_3;
    @FXML
    public ImageView rouge_2_4;
    @FXML
    public ImageView rouge_2_5;
    @FXML
    public ImageView rouge_2_6;
    @FXML
    public ImageView rouge_4_1;
    @FXML
    public ImageView rouge_4_2;
    @FXML
    public ImageView rouge_4_3;
    @FXML
    public ImageView rouge_4_4;
    @FXML
    public ImageView rouge_4_5;
    @FXML
    public ImageView rouge_4_6;
    @FXML
    public ImageView rouge_5_1;
    @FXML
    public ImageView rouge_5_2;
    @FXML
    public ImageView rouge_5_3;
    @FXML
    public ImageView rouge_5_4;
    @FXML
    public ImageView rouge_5_5;
    @FXML
    public ImageView rouge_5_6;
    @FXML
    public ImageView rouge_6_1;
    @FXML
    public ImageView rouge_6_2;
    @FXML
    public ImageView rouge_6_3;
    @FXML
    public ImageView rouge_6_4;
    @FXML
    public ImageView rouge_6_5;
    @FXML
    public ImageView rouge_6_6;
    @FXML
    public ImageView rouge_7_1;
    @FXML
    public ImageView rouge_7_2;
    @FXML
    public ImageView rouge_7_3;
    @FXML
    public ImageView rouge_7_4;
    @FXML
    public ImageView rouge_7_5;
    @FXML
    public ImageView rouge_7_6;
    @FXML
    public ImageView jaune_1_1;
    @FXML
    public ImageView jaune_1_2;
    @FXML
    public ImageView jaune_1_3;
    @FXML
    public ImageView jaune_1_4;
    @FXML
    public ImageView jaune_1_5;
    @FXML
    public ImageView jaune_1_6;
    @FXML
    public ImageView jaune_3_1;
    @FXML
    public ImageView jaune_3_2;
    @FXML
    public ImageView jaune_3_3;
    @FXML
    public ImageView jaune_3_4;
    @FXML
    public ImageView jaune_3_5;
    @FXML
    public ImageView jaune_3_6;
    @FXML
    public ImageView jaune_2_1;
    @FXML
    public ImageView jaune_2_2;
    @FXML
    public ImageView jaune_2_3;
    @FXML
    public ImageView jaune_2_4;
    @FXML
    public ImageView jaune_2_5;
    @FXML
    public ImageView jaune_2_6;
    @FXML
    public ImageView jaune_4_1;
    @FXML
    public ImageView jaune_4_2;
    @FXML
    public ImageView jaune_4_3;
    @FXML
    public ImageView jaune_4_4;
    @FXML
    public ImageView jaune_4_5;
    @FXML
    public ImageView jaune_4_6;
    @FXML
    public ImageView jaune_5_1;
    @FXML
    public ImageView jaune_5_2;
    @FXML
    public ImageView jaune_5_3;
    @FXML
    public ImageView jaune_5_4;
    @FXML
    public ImageView jaune_5_5;
    @FXML
    public ImageView jaune_5_6;
    @FXML
    public ImageView jaune_6_1;
    @FXML
    public ImageView jaune_6_2;
    @FXML
    public ImageView jaune_6_3;
    @FXML
    public ImageView jaune_6_4;
    @FXML
    public ImageView jaune_6_5;
    @FXML
    public ImageView jaune_6_6;
    @FXML
    public ImageView jaune_7_1;
    @FXML
    public ImageView jaune_7_2;
    @FXML
    public ImageView jaune_7_3;
    @FXML
    public ImageView jaune_7_4;
    @FXML
    public ImageView jaune_7_5;
    @FXML
    public ImageView jaune_7_6;
    


    @FXML
    public Text your_turn;

    @FXML
    public Text not_your_turn;

    @FXML
    public Text search_game;

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
                    search_game.setVisible(client.isSearchGame());
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
}
