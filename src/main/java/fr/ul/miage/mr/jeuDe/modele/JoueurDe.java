package fr.ul.miage.mr.jeuDe.modele;

import java.util.Observable;

public class JoueurDe extends Observable {

    private JeuDeEnCours jeuDe;

    private String nom;
    private int score;

    public JoueurDe(String nom, int score) {
        this.nom = nom;
        this.score = score;
    }

    public void setNom(String nom) {
        this.nom = nom;
        setChanged();
        notifyObservers();
    }

    public void setScore(int score) {
        if (score < 0) throw new IllegalArgumentException("Le score doit être positif");
        this.score = score;
        setChanged();
        notifyObservers();
    }

    public void addToScore(int score){
        if (score < 0) throw new IllegalArgumentException("Le score doit être positif");
        this.score += score;
        setChanged();
        notifyObservers();
    }

    public void resetScore() {
        score = 0;
        setChanged();
        notifyObservers();
    }

    public int getScore() {
        return score;
    }

    public String getNom() {
        return nom;
    }
}
