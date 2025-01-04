package fr.ul.miage.mr.jeuDe.ui.views;

import fr.ul.miage.mr.jeuDe.modele.JeuDe;

import java.util.Observable;
import java.util.Observer;

public class JeuDeView implements Observer {
    /**
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     */
    @Override
    public void update(Observable o, Object arg) {
        assert o instanceof JeuDe;
        JeuDe jeuDe = (JeuDe) o;
        System.out.println("Etat actuel : " + jeuDe.getJeuDeEtat());
    }
}
