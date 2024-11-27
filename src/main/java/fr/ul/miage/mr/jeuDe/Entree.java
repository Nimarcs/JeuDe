package fr.ul.miage.mr.jeuDe;

import fr.ul.miage.mr.jeuDe.modele.MeilleurScoreOld;

public class Entree {

    private String nom;
    private int score;

    public Entree(String nom, int score){
        this.nom = nom;
        this.score = score;
    }

    public String getNom() {
        return nom;
    }

    public Integer getScore() {
        return Integer.valueOf(score);
    }

    @Override
    public String toString() {
        return  " - " + nom + " : " + score;
    }


}
