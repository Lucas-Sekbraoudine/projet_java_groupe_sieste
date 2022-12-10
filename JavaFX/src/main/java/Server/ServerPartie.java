package Server;

import java.util.Random;

public class ServerPartie {
    private Grille grille;
    private int joueurCourant;

    public ServerPartie() {
        grille = new Grille();
        Random rand = new Random();
        joueurCourant = rand.nextInt(2) + 1; //Choix aléatoire du joueur qui commence
        //Dis aux clients que la partie commence
        //TODO: créer un thread pour chaque client et envoyer le message
        //TODO: créer un thread pour le chat
        while (!grille.isFull() && !grille.isWin(joueurCourant)) {
            deroulementPartie();
        }
        if (grille.isWin(joueurCourant)) {
            System.out.println("Le joueur numéro " + joueurCourant + " a gagné");
            //TODO: mettre a jour la base de données (defaite et victoire)
        } else {
            System.out.println("Match nul");
        }
        //TODO: Arreter tout les threads
    }

    public int getJoueurCourant() {
        return joueurCourant;
    }

    public void deroulementPartie() {
        //Envoie la grille aux joueurs
        //Envoie le numéro du joueur courant
        //En attente de la réponse du joueur courant
        //Si le coup est valide, on change de joueur
        //Sinon on envoie un message d'erreur au joueur
        //fin tour
    }


}
