package fr.ul.miage.mr.jeuDe.modele;

public class JeuDePasCommence implements JeuDeState{

    private De de1;
    private De de2;

    public JeuDePasCommence(){
        de1 = new De();
        de2 = new De();
    }

    @Override
    public int jouer(JeuDe jeuDe) {
        //On démarre le jeu (cette action ne lance pas une manche)
        jeuDe.setJeuDeState(new JeuDeEnCours());
        return 0;
    }
}
