package fr.ul.miage.mr.jeuDe.modele;

public interface JeuDeState {

    int jouer(JeuDe jeuDe);

    void updateJoueur(JeuDe jeuDe, String nomJoueur);

    EtatJeuDe getEtat();

}
