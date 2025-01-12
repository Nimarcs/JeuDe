package fr.ul.miage.mr.jeuDe.modele;

public class JeuDeEnCours implements JeuDeState {

    private final static int NB_MANCHE = 10;
    private De de1;
    private De de2;
    private int nbDeMancheJoue;

    public JeuDeEnCours(De de1, De de2) {
        this.de1 = de1;
        this.de2 = de2;
        nbDeMancheJoue = 0;
    }

    @Override
    public int jouer(JeuDe jeuDe) {
        //On joue la manche
        nbDeMancheJoue++;
        de1.lancer();
        de2.lancer();
        if (nbDeMancheJoue == NB_MANCHE) {
            //On finit le jeu
            jeuDe.finDePartie();
            jeuDe.setJeuDeState(new JeuTermine());
            return 0;
        }
        if (de1.getValeur() + de2.getValeur() == 7) {
            return 10;
        } else {
            return 0;
        }
    }

    @Override
    public void updateJoueur(JeuDe jeuDe, String nomJoueur) {
        throw new IllegalStateException("On ne definit pas le joueur en cours de partie");
    }

    @Override
    public EtatJeuDe getEtat() {
        return EtatJeuDe.EN_COURS;
    }
}
