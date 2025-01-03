package fr.ul.miage.mr.jeuDe.ui.scenes;

import fr.ul.miage.mr.jeuDe.ui.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AccueilScene {
    private final BorderPane root;

    public AccueilScene(MainApp app) {
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
        VBox scoreTable = createScoreBoard();

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

    private VBox createScoreBoard() {
        // Label pour le tableau des scores
        Label titleLabel = new Label("MEILLEURS SCORES");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-alignment: center;");

        // Création du tableau des scores
        TableView<ScoreEntry> scoreTable = createScoreTable();

        // Conteneur vertical pour le label et le tableau
        VBox scoreBoard = new VBox(10, titleLabel, scoreTable);
        scoreBoard.setStyle("-fx-padding: 10; -fx-alignment: center;");

        return scoreBoard;
    }

    /**
     * Création du tableau des meilleurs scores.
     */
    private TableView<ScoreEntry> createScoreTable() {
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

        // Ajouter des données fictives
        ObservableList<ScoreEntry> scores = FXCollections.observableArrayList(
                new ScoreEntry("Alice", 120),
                new ScoreEntry("Bob", 95),
                new ScoreEntry("Charlie", 80),
                new ScoreEntry("Diana", 150),
                new ScoreEntry("Eve", 110)
        );
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
