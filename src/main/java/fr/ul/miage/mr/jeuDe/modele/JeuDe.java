package fr.ul.miage.mr.jeuDe.modele;

import fr.ul.miage.mr.jeuDe.persistance.MeilleurScore;

import java.util.Observable;

public class JeuDe extends Observable {

    private MeilleurScore meilleurScore;

    private JoueurDe joueurDe;

    private JeuDeState jeuDeState;

    protected JeuDe(De de1, De de2, JoueurDe joueurDe, MeilleurScore meilleurScore) {
        this.jeuDeState = new JeuDePasCommence(de1, de2);
        this.joueurDe = joueurDe;
        this.meilleurScore = meilleurScore;
    }

    public void jouer() {
        joueurDe.addToScore(jeuDeState.jouer(this));
    }

    public void changerJoueur(String nomJoueur) {
        jeuDeState.updateJoueur(this, nomJoueur);
    }

    public void finDePartie() {
        meilleurScore.ajouter(joueurDe);
    }

    public void setJeuDeState(JeuDeState jeuDeState) {
        this.jeuDeState = jeuDeState;
        setChanged();
        notifyObservers();
    }

    public JoueurDe getJoueurDe() {
        return joueurDe;
    }

    public void setJoueurDe(JoueurDe joueurDe) {
        this.joueurDe = joueurDe;
    }

    public void resetScore() {
        joueurDe.resetScore();
    }

    public EtatJeuDe getJeuDeEtat() {
        return jeuDeState.getEtat();
    }
}
