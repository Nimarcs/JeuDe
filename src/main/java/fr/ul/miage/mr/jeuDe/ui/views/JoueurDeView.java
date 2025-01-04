package fr.ul.miage.mr.jeuDe.ui.views;

import fr.ul.miage.mr.jeuDe.modele.JoueurDe;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Observable;
import java.util.Observer;

public class JoueurDeView extends HBox implements Observer {

    private final Label joueurLabel;

    public JoueurDeView(JoueurDe joueurDe) {
        joueurLabel = new Label();
        this.getChildren().add(joueurLabel);
        this.setStyle("-fx-padding: 10px; -fx-font-size: 14px;"); // Style du conteneur

        // Initialisation de l'affichage avec les informations actuelles
        update(joueurDe, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof JoueurDe) {
            JoueurDe joueurDe = (JoueurDe) o;
            joueurLabel.setText("Score de " + joueurDe.getNom() + " : " + joueurDe.getScore());
        }
    }
}
