package fr.ul.miage.mr.jeuDe.modele;

public class JeuTermine implements JeuDeState {

    @Override
    public int jouer(JeuDe jeuDe) {
        return 0;
    }

    @Override
    public void updateJoueur(JeuDe jeuDe, String nomJoueur) {

    }

    @Override
    public EtatJeuDe getEtat() {
        return EtatJeuDe.TERMINE;
    }
}


