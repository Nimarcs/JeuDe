package fr.ul.miage.mr.jeuDe;

public class JoueurDe {

    private JeuDe jeuDe;

    private String nom;
    private int score;

    public JoueurDe(String nom, int score) {
        this.nom = nom;
        this.score = score;
    }

    public void play(){

    }

    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }
}
