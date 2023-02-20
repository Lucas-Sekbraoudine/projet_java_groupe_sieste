package Client;

import Common.LoadScene;
import Common.Message;
import Common.Puissance4;
import Controller.GamescreenController;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.io.IOException;
import java.util.Scanner;

public class ClientGame implements Runnable{
    private final Client client;
    private final Puissance4 puissance4;
    LoadScene loadScene = new LoadScene();

    /*public GamescreenController gamescreenController;
    @FXML
    public Hyperlink gamescreen;
*/
    public ClientGame(Client client) throws IOException {
        this.client = client;
        this.puissance4 = new Puissance4();
        /*this.gamescreenController = new GamescreenController(client);
        loadScene.loadScene("/fxml/Gamescreen.fxml", gamescreen, this.gamescreenController);*/
    }

    public void run() {
        while (client.isInGame()) {
            this.client.setBoard(puissance4.getBoard());
            //Si c'est le tour du joueur
            if (client.getPlayerNumber() == client.getCurrentPlayer()) {
                //Afficher le plateau
                puissance4.printBoard();
                while (client.getCoupJoueur() == -1) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                int move = client.getCoupJoueur();
                //Jouer le coup du joueur
                puissance4.dropToken(move);
                //Envoyer le coup du joueur au serveur
                try {
                    client.sendMessage(new Message("move", ""+move));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //Changer de joueur
                client.changeCurrentPlayer();
                puissance4.switchPlayer();
                client.setCoupJoueur(-1);
            } else {
                //Afficher le plateau
                puissance4.printBoard();
                //Attendre que le serveur joue
                while (client.getCoupAdversaire() == -1) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                //Jouer le coup du serveur
                puissance4.dropToken(client.getCoupAdversaire());
                //Remettre le coup du serveur a -1
                client.setCoupAdversaire(-1);
                //Changer de joueur
                client.changeCurrentPlayer();
                puissance4.switchPlayer();
            }
        }
    }
}