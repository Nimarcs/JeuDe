package fr.ul.miage.mr.jeuDe.ui.scenes;

import fr.ul.miage.mr.jeuDe.modele.De;
import fr.ul.miage.mr.jeuDe.modele.JeuDeFacade;
import fr.ul.miage.mr.jeuDe.modele.JoueurDe;
import fr.ul.miage.mr.jeuDe.persistance.MeilleurScore;
import fr.ul.miage.mr.jeuDe.persistance.MeilleurScoreJSONFactory;
import fr.ul.miage.mr.jeuDe.ui.MainApp;
import fr.ul.miage.mr.jeuDe.ui.views.DeView;
import fr.ul.miage.mr.jeuDe.ui.views.JeuDeView;
import fr.ul.miage.mr.jeuDe.ui.views.JoueurDeView;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class JeuScene {
    private final BorderPane root;
    private JeuDeView jeuDeView;
    private JeuDeFacade jeuDeFacade;

    public JeuScene(MainApp app, String nomJoueur) {
        this.root = new BorderPane();

        this.jeuDeView = new JeuDeView();
        MeilleurScore meilleurScore = new MeilleurScoreJSONFactory().construire();
        JoueurDe joueurDe = new JoueurDe(nomJoueur, 0);
        JoueurDeView joueurDeView = new JoueurDeView(joueurDe);
        joueurDe.addObserver(joueurDeView);
        De de1 = new De();
        De de2 = new De();
        this.jeuDeFacade = new JeuDeFacade(de1, de2, joueurDe, meilleurScore, jeuDeView);

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
        lancerButton.setOnAction(e -> jeuDeFacade.jouer());

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
