package Common;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Grille {
    private int[][] grille;
    private int nbLig;
    private int nbCol;

    private boolean isEnd = false;

    private int joueurCourant;

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
                if (isWin(joueur) || isFull()) {
                    isEnd = true;
                }
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

    public String toString(){
        String s = "";
        for (int i = 0; i < nbLig; i++) {
            for (int j = 0; j < nbCol; j++) {
                s += grille[i][j] + " ";
            }
            s += "\n";
        }
        s += joueurCourant;
        return s;
    }

    public void setGrille(String s){
        String[] lignes = s.split("\n");
        for(int i = 0; i < lignes.length - 1; i++){
            String[] cases = lignes[i].split(" ");
            for(int j = 0; j < cases.length; j++){
                grille[i][j] = Integer.parseInt(cases[j]);
            }
        }
        joueurCourant = Integer.parseInt(lignes[lignes.length - 1]);
    }

    public void transmettreGrille(Socket[] clients) throws IOException {
        String grilleString = this.toString();
        byte[] gridBytes = grilleString.getBytes();

        for (Socket client : clients) {
            OutputStream out = client.getOutputStream();
            out.write(gridBytes);
        }
    }

    public void recevoirGrille(Socket client) throws IOException {
        byte[] gridBytes = new byte[1024];
        client.getInputStream().read(gridBytes);
        String grilleString = new String(gridBytes);
        this.setGrille(grilleString);
    }

    public void initNumeroJoueur(Socket[] clients) throws IOException {
        for (int i = 0; i < clients.length; i++) {
            OutputStream out = clients[i].getOutputStream();
            out.write(i+1);
        }
    }

    public void setJoueurCourant(int joueur){
        this.joueurCourant = joueur;
    }

    public int recevoirCoup(Socket client) throws IOException {
        byte[] colonneBytes = new byte[1024];
        client.getInputStream().read(colonneBytes);
        String colonneString = new String(colonneBytes);
        int colonne = Integer.parseInt(colonneString);
        return colonne;
    }

    public void envoyerCoup(Socket serveur, int colonne) throws IOException {
        String colonneString = Integer.toString(colonne);
        byte[] colonneBytes = colonneString.getBytes();
        serveur.getOutputStream().write(colonneBytes);
    }

}
