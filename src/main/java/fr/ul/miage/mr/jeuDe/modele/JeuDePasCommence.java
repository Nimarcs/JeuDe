package fr.ul.miage.mr.jeuDe.modele;

public class JeuDePasCommence implements JeuDeState {

    private De de1;
    private De de2;

    public JeuDePasCommence(De de1, De de2) {
        this.de1 = de1;
        this.de2 = de2;
    }

    @Override
    public int jouer(JeuDe jeuDe) {
        //On démarre le jeu (cette action ne lance pas une manche)
        jeuDe.resetScore();
        jeuDe.setJeuDeState(new JeuDeEnCours(de1, de2));
        return 0;
    }

    @Override
    public void updateJoueur(JeuDe jeuDe, String nomJoueur) {
        JoueurDe original = jeuDe.getJoueurDe();
        original.resetScore();
        original.setNom(nomJoueur);
        jeuDe.setJoueurDe(original);
    }

    @Override
    public EtatJeuDe getEtat() {
        return EtatJeuDe.PAS_COMMENCE;
    }

}
