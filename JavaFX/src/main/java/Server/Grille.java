package Server;

public class Grille {
    private int[][] grille;
    private int nbLig;
    private int nbCol;

    public Grille() {
        //Taille de la grille de puissance 4: 6*7
        this.nbLig = 6;
        this.nbCol = 7;
        grille = new int[nbLig][nbCol];
        //Initialisation de la grille
        for (int i = 0; i < nbLig; i++) {
            for (int j = 0; j < nbCol; j++) {
                grille[i][j] = 0;
            }
        }
    }

    public void afficherGrille() {
        for (int i = 0; i < nbLig; i++) {
            for (int j = 0; j < nbCol; j++) {
                System.out.print(grille[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean jouer(int joueur, int colonne) {
        if (colonne < 0 || colonne >= nbCol) {
            return false;
        }
        for (int i = nbLig - 1; i >= 0; i--) {
            if (grille[i][colonne] == 0) {
                grille[i][colonne] = joueur;
                return true;
            }
        }
        return false;
    }

    public boolean isWin(int joueur) {
        //Vérification des lignes
        for (int i = 0; i < nbLig; i++) {
            for (int j = 0; j < nbCol - 3; j++) {
                if (grille[i][j] == joueur && grille[i][j + 1] == joueur && grille[i][j + 2] == joueur && grille[i][j + 3] == joueur) {
                    return true;
                }
            }
        }
        //Vérification des colonnes
        for (int i = 0; i < nbLig - 3; i++) {
            for (int j = 0; j < nbCol; j++) {
                if (grille[i][j] == joueur && grille[i + 1][j] == joueur && grille[i + 2][j] == joueur && grille[i + 3][j] == joueur) {
                    return true;
                }
            }
        }
        //Vérification des diagonales
        for (int i = 0; i < nbLig - 3; i++) {
            for (int j = 0; j < nbCol - 3; j++) {
                if (grille[i][j] == joueur && grille[i + 1][j + 1] == joueur && grille[i + 2][j + 2] == joueur && grille[i + 3][j + 3] == joueur) {
                    return true;
                }
            }
        }
        for (int i = 3; i < nbLig; i++) {
            for (int j = 0; j < nbCol - 3; j++) {
                if (grille[i][j] == joueur && grille[i - 1][j + 1] == joueur && grille[i - 2][j + 2] == joueur && grille[i - 3][j + 3] == joueur) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < nbLig; i++) {
            for (int j = 0; j < nbCol; j++) {
                if (grille[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getNbLig() {
        return nbLig;
    }

    public int getNbCol() {
        return nbCol;
    }

    public int getCase(int lig, int col) {
        return grille[lig][col];
    }

}
