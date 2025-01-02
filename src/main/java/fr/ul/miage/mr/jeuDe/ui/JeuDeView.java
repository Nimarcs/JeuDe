package fr.ul.miage.mr.jeuDe.ui;

import fr.ul.miage.mr.jeuDe.modele.JeuDe;

import java.util.Observable;
import java.util.Observer;

public class JeuDeView implements Observer {
    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     */
    @Override
    public void update(Observable o, Object arg) {
        assert o instanceof JeuDe;
        JeuDe jeuDe = (JeuDe) o;
        System.out.println("Nouveau état de jeu : " + jeuDe.getJeuDeEtat());
    }
}
