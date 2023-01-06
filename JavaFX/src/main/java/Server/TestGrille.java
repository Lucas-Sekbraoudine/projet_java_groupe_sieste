package Server;

public class TestGrille {

    public static void main(String[] args) {
        Grille grille = new Grille();
        grille.afficherGrille();
        System.out.println();
        grille.jouer(2, 0);
        grille.jouer(1, 0);
        grille.afficherGrille();
        System.out.println();
        grille.jouer(2, 1);
        grille.jouer(1, 0);
        grille.afficherGrille();
        System.out.println();
        grille.jouer(2, 0);
        grille.jouer(1, 0);
        grille.afficherGrille();
        System.out.println();
        grille.jouer(2, 1);
        grille.jouer(1, 0);
        grille.afficherGrille();
        System.out.println();
        System.out.println("Le joueur numéro 1 a gagné: "+ grille.isWin(1));
    }

}

