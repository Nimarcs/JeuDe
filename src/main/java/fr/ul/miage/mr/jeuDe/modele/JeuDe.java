package fr.ul.miage.mr.jeuDe.modele;

import fr.ul.miage.mr.jeuDe.persistance.MeilleurScore;
import fr.ul.miage.mr.jeuDe.persistance.MeilleurScoreJSON;
import fr.ul.miage.mr.jeuDe.persistance.MeilleurScoreJSONFactory;
import fr.ul.miage.mr.jeuDe.persistance.MeilleurScoreMongoFactory;

public class JeuDe {

    private MeilleurScore meilleurScore;

    private JoueurDe joueurDe;

    private JeuDeState jeuDeState;

    public JeuDe(){
        this.jeuDeState = new JeuDePasCommence();
        this.meilleurScore = new MeilleurScoreJSONFactory().construire();
    }

    public int jouer(JoueurDe joueurDe){
        return jeuDeState.jouer(this);
    }

    public void finDePartie(){
        jeuDeState = new JeuDePasCommence();
        meilleurScore.ajouter(joueurDe);
    }

    public void setJeuDeState(JeuDeState jeuDeState) {
        this.jeuDeState = jeuDeState;
    }

    public void setJoueurDe(JoueurDe joueurDe) {
        this.joueurDe = joueurDe;
    }
}
