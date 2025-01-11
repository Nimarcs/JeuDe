package fr.ul.miage.mr.jeuDe.ui;

import fr.ul.miage.mr.jeuDe.modele.JoueurDe;
import fr.ul.miage.mr.jeuDe.persistance.AbstractMeilleurScoreFactory;
import fr.ul.miage.mr.jeuDe.persistance.MeilleurScore;
import fr.ul.miage.mr.jeuDe.persistance.MeilleurScoreJSONFactory;
import fr.ul.miage.mr.jeuDe.ui.scenes.AccueilScene;
import fr.ul.miage.mr.jeuDe.ui.scenes.FinJeuScene;
import fr.ul.miage.mr.jeuDe.ui.scenes.JeuScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Bien penser à ajouter une VM option pour javafx dans la configuration de lancement IntelliJ
 * avec cette valeur :
 * --module-path "C:\Program Files\Java\javafx-sdk-22.0.1\lib" --add-modules javafx.controls,javafx.fxml
 * (modifier le chemin)
 */

public class MainApp extends Application {
    private MeilleurScore meilleurScore;
    private Stage primaryStage;
    private Scene accueilScene;
    private Scene jeuScene;
    private Scene finJeuScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        AbstractMeilleurScoreFactory meilleurScoreFactory = new MeilleurScoreJSONFactory();
        meilleurScore = meilleurScoreFactory.construire();

        // Afficher la scène d'accueil
        primaryStage.setTitle("Jeu de Dé");
        showAccueilScene();
        primaryStage.show();
    }

    public void showAccueilScene() {
        AccueilScene accueilScene = new AccueilScene(this, meilleurScore);
        this.accueilScene = new Scene(accueilScene.getView(), 800, 400);
        primaryStage.setScene(this.accueilScene);
    }

    public void showJeuScene(String nomJoueur) {
        JeuScene jeuScene = new JeuScene(this, nomJoueur, meilleurScore);
        this.jeuScene = new Scene(jeuScene.getView(), 800, 400);
        primaryStage.setScene(this.jeuScene);
    }

    public void showFinJeuScene(JoueurDe joueurDe) {
        FinJeuScene finJeuScene = new FinJeuScene(this, joueurDe);
        this.finJeuScene = new Scene(finJeuScene.getView(), 800, 400);
        primaryStage.setScene(this.finJeuScene);
    }
}
