package fr.ul.miage.mr.jeuDe;

import fr.ul.miage.mr.jeuDe.modele.De;
import fr.ul.miage.mr.jeuDe.modele.JeuDeFacade;
import fr.ul.miage.mr.jeuDe.modele.JoueurDe;
import fr.ul.miage.mr.jeuDe.persistance.MeilleurScore;
import fr.ul.miage.mr.jeuDe.persistance.MeilleurScoreJSONFactory;
import fr.ul.miage.mr.jeuDe.ui.views.DeView;
import fr.ul.miage.mr.jeuDe.ui.views.JeuDeView;
import fr.ul.miage.mr.jeuDe.ui.views.JoueurDeView;

public class Principale {

    public static void main(String[] args) {
        De de1 = new De();
        DeView deView1 = new DeView();
        de1.addObserver(deView1);

        De de2 = new De();
        DeView deView2 = new DeView();
        de2.addObserver(deView2);

        JoueurDe joueurDe = new JoueurDe("Anonymous", 0);
        JoueurDeView joueurDeView = new JoueurDeView(joueurDe);
        joueurDe.addObserver(joueurDeView);
        MeilleurScore meilleurScore = new MeilleurScoreJSONFactory().construire();
        meilleurScore.charger();

        JeuDeView jeuDeView = new JeuDeView();
        JeuDeFacade jeuDeFacade = new JeuDeFacade(de1, de2, joueurDe, meilleurScore, jeuDeView);
        jeuDeFacade.changerJoueur("Patrick");
        jeuDeFacade.jouer();
        jeuDeFacade.jouer();
        jeuDeFacade.jouer();
        jeuDeFacade.jouer();
        jeuDeFacade.jouer();
        jeuDeFacade.jouer();
        jeuDeFacade.jouer();
        jeuDeFacade.jouer();
        jeuDeFacade.jouer();
        jeuDeFacade.jouer();
        jeuDeFacade.jouer();
        jeuDeFacade.jouer();
        jeuDeFacade.changerJoueur("Michel");
        jeuDeFacade.jouer();
        jeuDeFacade.jouer();
        System.out.println(meilleurScore);
    }
}
