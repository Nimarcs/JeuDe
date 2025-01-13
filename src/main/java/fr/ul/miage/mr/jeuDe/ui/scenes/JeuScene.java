package fr.ul.miage.mr.jeuDe.ui.scenes;

import fr.ul.miage.mr.jeuDe.modele.De;
import fr.ul.miage.mr.jeuDe.modele.EtatJeuDe;
import fr.ul.miage.mr.jeuDe.modele.JeuDeFacade;
import fr.ul.miage.mr.jeuDe.modele.JoueurDe;
import fr.ul.miage.mr.jeuDe.persistance.MeilleurScore;
import fr.ul.miage.mr.jeuDe.ui.MainApp;
import fr.ul.miage.mr.jeuDe.ui.views.DeView;
import fr.ul.miage.mr.jeuDe.ui.views.JoueurDeView;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class JeuScene {
    private final BorderPane root;

    public JeuScene(MainApp app, String nomJoueur, MeilleurScore meilleurScore) {
        this.root = new BorderPane();

        JeuDeFacade jeuDeFacade;
        meilleurScore.charger();
        JoueurDe joueurDe = new JoueurDe(nomJoueur, 0);
        JoueurDeView joueurDeView = new JoueurDeView(joueurDe);
        joueurDe.addObserver(joueurDeView);
        De de1 = new De();
        De de2 = new De();
        jeuDeFacade = new JeuDeFacade(de1, de2, joueurDe, meilleurScore);

        DeView deView1 = new DeView();
        de1.addObserver(deView1);
        DeView deView2 = new DeView();
        de2.addObserver(deView2);

        // Construire une HBox pour les dés
        HBox desBox = new HBox();
        desBox.getChildren().addAll(deView1, deView2);
        desBox.setAlignment(javafx.geometry.Pos.CENTER);
        root.setTop(desBox);

        // Bouton LANCER
        Button lancerButton = new Button("LANCER");
        jeuDeFacade.jouer();
        lancerButton.setOnAction(
                e -> {
                    jeuDeFacade.jouer();
                    if (jeuDeFacade.getEtat() == EtatJeuDe.TERMINE) {
                        app.showFinJeuScene(joueurDe);
                    }
                }
        );

        // Construire une VBox pour LANCER et JoueurDeView et JeuDeView
        HBox joueurDeBox = new HBox();
        joueurDeBox.getChildren().addAll(joueurDeView, lancerButton);
        root.setCenter(joueurDeBox);

        // Bouton QUITTER
        Button quitterButton = new Button("QUITTER");
        quitterButton.setOnAction(e -> app.showAccueilScene());
        root.setBottom(quitterButton);
        BorderPane.setAlignment(quitterButton, javafx.geometry.Pos.CENTER);
    }

    public BorderPane getView() {
        return root;
    }
}
