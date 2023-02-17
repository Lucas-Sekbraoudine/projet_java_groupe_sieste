package Client;

import Common.Message;
import Common.Puissance4;

import java.io.IOException;
import java.util.Scanner;

public class ClientGame implements Runnable{
    private Client client;
    private Puissance4 puissance4;

    public ClientGame(Client client){
        this.client = client;
        this.puissance4 = new Puissance4();
    }

    public void run() {
        while (client.isInGame()) {
            //Si c'est le tour du joueur
            if (client.getPlayerNumber() == client.getCurrentPlayer()) {
                //Afficher le plateau
                puissance4.printBoard();
                //Attendre que le joueur joue
                System.out.println("A votre tour de jouer. Entrez un nombre entre 0 et 6");
                Scanner sc = new Scanner(System.in);
                int move = sc.nextInt();
                while (move<0 || move>6) {
                    System.out.println("Veuillez entrer un nombre entre 0 et 6");
                    move = sc.nextInt();
                }
                //Jouer le coup du joueur
                puissance4.dropToken(move);
                //Envoyer le coup du joueur au serveur
                try {
                    client.sendMessage(new Message("move", ""+move));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //Changer de joueur
                client.setCurrentPlayer(client.getCurrentPlayer() == 1 ? 2 : 1);
            } else {
                //Afficher le plateau
                puissance4.printBoard();
                //Attendre que le serveur joue
                while (client.getCoupAdversaire() == -1) {
                }
                //Jouer le coup du serveur
                puissance4.dropToken(client.getCoupAdversaire());
                //Remettre le coup du serveur a -1
                client.setCoupAdversaire(-1);
            }
        }
    }
}
