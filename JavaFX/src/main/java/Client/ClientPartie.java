package Client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientPartie {

    private Grille grille;

    private int numJoueur;

    private Socket server;

    public ClientPartie(Socket server) throws IOException {
        this.server = server;
        //
        demarrerPartie();
        //
    }

    private void demarrerPartie() throws IOException {
        grille = new Grille();
        //Recoie son numéro de joueur
        numJoueur = this.grille.recevoirNumeroJoueur(server);
        //Deroulement de la partie
        deroulementPartie();
        if (grille.isWin(numJoueur)) {
            System.out.println("Vous avez gagné!");
        } else if (grille.isFull()) {
            System.out.println("Match nul!");
        } else {
            System.out.println("Vous avez perdu!");
        }
    }

    private void deroulementPartie() throws IOException {
        while (!grille.isEnd()) {
            //Recoie la grille
            grille.recevoirGrille(server);
            //Affiche la grille
            grille.afficherGrille();
            //Joue si le joueurCourant == numJoueur
            if (grille.getJoueurCourant() == numJoueur) {
                //Demande un numéro de colonne
                System.out.println("Joueur " + numJoueur + " à vous de jouer");
                Scanner sc = new Scanner(System.in);
                int colonne = sc.nextInt();
                //Envoie le coup
                grille.envoyerCoup(server, colonne);
            }

        }
    }
}
