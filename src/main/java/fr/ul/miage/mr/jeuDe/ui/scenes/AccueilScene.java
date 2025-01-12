package fr.ul.miage.mr.jeuDe.ui.scenes;

import fr.ul.miage.mr.jeuDe.persistance.Entree;
import fr.ul.miage.mr.jeuDe.persistance.MeilleurScore;
import fr.ul.miage.mr.jeuDe.persistance.MeilleurScoreJSONFactory;
import fr.ul.miage.mr.jeuDe.persistance.MeilleurScoreMongoFactory;
import fr.ul.miage.mr.jeuDe.ui.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Comparator;
import java.util.List;

public class AccueilScene {
    private final BorderPane root;

    public AccueilScene(MainApp app, MeilleurScore meilleurScore) {
        root = new BorderPane();
        // Champ de texte pour entrer le nom du joueur
        TextField nomJoueurField = new TextField();
        nomJoueurField.setPromptText("Nom du joueur");
        nomJoueurField.setPrefWidth(200);

        HBox nomJoueurBox = new HBox(nomJoueurField);
        nomJoueurBox.setStyle("-fx-alignment: center;");
        nomJoueurBox.setPrefWidth(200);

        // Bouton JOUER
        Button jouerButton = new Button("JOUER");
        jouerButton.setOnAction(e -> {
            String nomJoueur = nomJoueurField.getText().trim();
            if (nomJoueur.isEmpty()) {
                nomJoueur = "Invité";
            }
            app.showJeuScene(nomJoueur);
        });

        // Bouton QUITTER
        Button quitterButton = new Button("QUITTER");
        quitterButton.setOnAction(e -> System.exit(0));

        // Tableau des meilleurs scores
        VBox scoreTable = createScoreBoard(app, meilleurScore);

        // Disposition des éléments
        VBox centerBox = new VBox(10, jouerButton, nomJoueurBox);
        centerBox.setStyle("-fx-alignment: center;");
        root.setCenter(centerBox);
        root.setBottom(quitterButton);
        root.setRight(scoreTable); // Ajouter le tableau à droite
    }

    public BorderPane getView() {
        return root;
    }

    ///////////////////////////////////////////////////////////////////////////
    // CHATGPT
    ///////////////////////////////////////////////////////////////////////////

    private VBox createScoreBoard(MainApp app, MeilleurScore meilleurScore) {
        // Label pour le tableau des scores
        Label titleLabel = new Label("MEILLEURS SCORES");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-alignment: center;");

        Label scoresDropdownLabel = new Label("Type de stockage :");
        scoresDropdownLabel.setStyle("-fx-font-size: 12px; -fx-alignment: left;  -fx-padding: 5 0 0 0");

        // Dropdown type de persistance
        ComboBox<String> scoreDropdown = new ComboBox<>(FXCollections.observableArrayList("Fichier", "MongoDB"));
        scoreDropdown.setValue(meilleurScore.toString());

        // Création du tableau des scores
        TableView<ScoreEntry> scoreTable = createScoreTable(meilleurScore);

        // Gestion du changement de sélection
        scoreDropdown.setOnAction(event -> {
            meilleurScore.sauvegarder();

            String selection = scoreDropdown.getValue();
            switch (selection) {
                case "Fichier":
                    app.setMeilleurScoreFactory(new MeilleurScoreJSONFactory());
                    app.showAccueilScene();
                    break;
                case "MongoDB":
                    app.setMeilleurScoreFactory(new MeilleurScoreMongoFactory());
                    app.showAccueilScene();
                    break;
                default:
                    throw new IllegalStateException("Type de stockage non pris en compte : " + selection);
            }
            scoreTable.refresh();
        });


        // Conteneur vertical pour le label et le tableau
        VBox scoreBoard = new VBox(10, titleLabel, new HBox(10, scoresDropdownLabel, scoreDropdown), scoreTable);
        scoreBoard.setStyle("-fx-padding: 10; -fx-alignment: center;");

        return scoreBoard;
    }

    /**
     * Création du tableau des meilleurs scores.
     */
    private TableView<ScoreEntry> createScoreTable(MeilleurScore meilleurScore) {
        TableView<ScoreEntry> scoreTable = new TableView<>();

        // Colonne Nom
        TableColumn<ScoreEntry, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(100);

        // Colonne Score
        TableColumn<ScoreEntry, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setPrefWidth(100);

        scoreTable.getColumns().addAll(nameColumn, scoreColumn);

        // Désactiver la colonne vide par défaut
        scoreTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Charger les données dans une liste
        meilleurScore.charger();
        List<ScoreEntry> scoreEntryList = meilleurScore.getEntreeList().stream()
                .sorted(new Comparator<Entree>() {
                    public int compare(Entree s1, Entree s2) {
                        return -s1.getScore().compareTo(s2.getScore());
                    }
                }).map(entree -> new ScoreEntry(entree.getNom(), entree.getScore())).toList();

        // Ajouter la liste
        ObservableList<ScoreEntry> scores = FXCollections.observableArrayList(scoreEntryList);
        scoreTable.setItems(scores);

        return scoreTable;
    }

    /**
     * Classe interne représentant une entrée dans le tableau des scores.
     */
    public static class ScoreEntry {
        private final String name;
        private final int score;

        public ScoreEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
}
