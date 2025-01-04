package fr.ul.miage.mr.jeuDe.ui.views;

import fr.ul.miage.mr.jeuDe.modele.De;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.util.Observable;
import java.util.Observer;

public class DeView extends StackPane implements Observer {

    private final Label dieLabel;

    public DeView() {
        dieLabel = new Label("0");
        dieLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;"); // Style du texte
        this.getChildren().add(dieLabel);
        this.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-padding: 10px;"); // Bordure pour représenter un dé
    }

    /**
     * Mise à jour de l'affichage du dé lorsque sa valeur change.
     * @param o   l'objet observable.
     * @param arg un argument passé à la méthode notifyObservers.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof De) {
            De de = (De) o;
            dieLabel.setText(String.valueOf(de.getValeur()));
        }
    }
}
