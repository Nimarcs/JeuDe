package fr.ul.miage.mr.jeuDe.modele;

public class JeuDeEnCours implements JeuDeState {

    private De de1;
    private De de2;
    private int nbDeMancheJoue;
    private final static int NB_MANCHE = 10;

    public JeuDeEnCours(){
        de1 = new De();
        de2 = new De();
        nbDeMancheJoue = 0;
    }

    @Override
    public int jouer(JeuDe jeuDe) {
        if (nbDeMancheJoue < NB_MANCHE) {
            //On joue la manche
            nbDeMancheJoue++;
            de1.lancer();
            de2.lancer();
            if (de1.getValeur() + de2.getValeur() >= 7)
                return 10;
            else
                return 0;
        } else {
            //On finit le jeu
            jeuDe.finDePartie();
            jeuDe.setJeuDeState(new JeuDePasCommence());
            return 0;
        }
    }
}
