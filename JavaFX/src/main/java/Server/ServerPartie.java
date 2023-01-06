package Server;

import Common.Grille;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class ServerPartie {
    private Grille grille;
    private int joueurCourant;
    private Socket[] clients;

    public ServerPartie(Socket[] clients) throws IOException {
        // Vérifiez que les sockets sont valides et que vous avez bien reçu deux clients
        if (clients.length != 2 || clients[0] == null || clients[1] == null) {
            System.out.println("Erreur : nécessite deux clients valides pour démarrer la partie.");
            return;
        } else {
            this.clients = clients;
        }
        //TODO: créer un thread pour chaque client et envoyer le message
        //TODO: créer un thread pour le chat
        demarrerPartie();
        //TODO: Arreter tout les threads
    }


    public int getJoueurCourant() {
        return joueurCourant;
    }

    public void deroulementPartie() throws IOException {
        //Envoie la grille aux joueur
        this.grille.transmettreGrille(this.clients);
        //En attente de la réponse du joueur courant
        this.grille.recevoirCoup(this.clients[this.joueurCourant]);
        //Si le coup est valide, on change de joueur
        if (this.grille.jouer(this.joueurCourant, 0)){
            this.joueurCourant = (this.joueurCourant + 1) % 2;
        } else {
            //Sinon on envoie un message d'erreur au joueur
            this.clients[this.joueurCourant].getOutputStream().write("Coup invalide".getBytes());
        }
        //fin tour
    }

    public void demarrerPartie() throws IOException {
        // Créez une grille
        grille = new Grille();
        //Envoie leurs numéros aux joueurs
        grille.initNumeroJoueur(clients);
        //defini le premier joueur en random
        Random random = new Random();
        joueurCourant = random.nextInt(2) + 1;
        this.grille.setJoueurCourant(joueurCourant);
        //Lance la partie
        deroulementPartie();
        if (grille.isWin(joueurCourant)) {
            System.out.println("Le joueur numéro " + joueurCourant + " a gagné");
            //TODO: mettre a jour la base de données (defaite et victoire)
        } else {
            System.out.println("Match nul");
        }
    }
}
