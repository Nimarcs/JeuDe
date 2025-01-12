package fr.ul.miage.mr.jeuDe.modele;

import fr.ul.miage.mr.jeuDe.persistance.MeilleurScore;

public class JeuDeFacade {

    private JeuDe jeuDe;

    public JeuDeFacade(De de1, De de2, JoueurDe joueurDe, MeilleurScore meilleurScore) {
        jeuDe = new JeuDe(de1, de2, joueurDe, meilleurScore);
    }

    public void jouer() {
        jeuDe.jouer();
    }

    public void changerJoueur(String nomJoueur) {
        if (getEtat() == EtatJeuDe.PAS_COMMENCE)
            jeuDe.changerJoueur(nomJoueur);
    }

    public EtatJeuDe getEtat() {
        return jeuDe.getJeuDeEtat();
    }

}
