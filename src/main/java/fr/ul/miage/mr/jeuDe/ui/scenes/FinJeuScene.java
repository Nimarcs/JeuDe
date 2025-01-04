package fr.ul.miage.mr.jeuDe.ui.scenes;

import fr.ul.miage.mr.jeuDe.modele.JoueurDe;
import fr.ul.miage.mr.jeuDe.ui.MainApp;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class FinJeuScene {
    private final BorderPane root;

    public FinJeuScene(MainApp app, JoueurDe joueurDe) {
        root = new BorderPane();

        // Affichage du score final
        Label scoreFinalLabel = new Label("Score final de "+joueurDe.getNom()+" : " + joueurDe.getScore());
        root.setCenter(scoreFinalLabel);

        // Bouton QUITTER
        Button quitterButton = new Button("Retour à l'accueil");
        quitterButton.setOnAction(e -> app.showAccueilScene());
        root.setBottom(quitterButton);
        BorderPane.setAlignment(quitterButton, javafx.geometry.Pos.CENTER);
    }

    public BorderPane getView() {
        return root;
    }
}
