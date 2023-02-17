package Server;

import Common.Message;
import Common.Puissance4;
import Server.*;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game implements Runnable{
    private ConnectedClient player1;
    private ConnectedClient player2;
    private Puissance4 puissance4;
    private int currentPlayer;

    public Game(ConnectedClient player1, ConnectedClient player2){
        System.out.println("Nouvelle partie");
        this.player1 = player1;
        this.player2 = player2;
        this.puissance4 = new Puissance4();

    }

    public void run(){
        try {
            //Choisir un joueur au hasard qui commence
            Random rand = new Random();
            int[] tab = {player1.getId(), player2.getId()};
            this.currentPlayer = tab[rand.nextInt(2)];

            //Dis que la partie commence avec le num du joueur qui commence et le numero de chaque joueur
            player1.sendMessage(new Message("Start", currentPlayer + ";" + player1.getId()));
            player2.sendMessage(new Message("Start", currentPlayer + ";" + player2.getId()));

            //Tant que la partie n'est pas finie
            while (!puissance4.isFinished()) {
                //Si c'est le tour du joueur 1
                if (currentPlayer == 1) {
                    //Attendre que le joueur 1 joue
                    while (player1.getMove() == -1) {

                    }
                    //Jouer le coup du joueur 1
                    puissance4.dropToken(player1.getMove());
                    //Envoyer le coup du joueur 1 au joueur 2
                    player2.sendMessage(new Message("Play", ""+player1.getMove()));
                    //Remettre le coup du joueur 1 a -1
                    player1.setMove(-1);
                    //Changer de joueur
                    currentPlayer = 2;
                } else {
                    //Attendre que le joueur 2 joue
                    while (player2.getMove() == -1) {
                    }
                    //Jouer le coup du joueur 2
                    puissance4.dropToken(player2.getMove());
                    //Envoyer le coup du joueur 2 au joueur 1
                    player1.sendMessage(new Message("Play", ""+player2.getMove()));
                    //Remettre le coup du joueur 2 a -1
                    player2.setMove(-1);
                    //Changer de joueur
                    currentPlayer = 1;
                }
            }
            //Envoie le resultat de la partie a chaque joueur
            if (puissance4.getWinner() == 1) {
                player1.sendMessage(new Message("Game", "win"));
                player2.sendMessage(new Message("Game", "lose"));
            } else if (puissance4.getWinner() == 2) {
                player1.sendMessage(new Message("Game", "lose"));
                player2.sendMessage(new Message("Game", "win"));
            } else {
                player1.sendMessage(new Message("Game", "draw"));
                player2.sendMessage(new Message("Game", "draw"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

