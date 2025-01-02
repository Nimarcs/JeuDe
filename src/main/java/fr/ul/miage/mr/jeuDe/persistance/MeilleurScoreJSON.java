package fr.ul.miage.mr.jeuDe.persistance;

import fr.ul.miage.mr.jeuDe.modele.Entree;
import fr.ul.miage.mr.jeuDe.modele.JoueurDe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MeilleurScoreJSON extends MeilleurScore implements java.io.Serializable {

    private static final String FILEURI = "fr/ul/miage/mr/jeuDe/save.json";

    public static void main(String[] args) {
        AbstractMeilleurScoreFactory meilleurScoreFactory = new MeilleurScoreMongoFactory();
        JoueurDe joueurDe = new JoueurDe("Bilbo", 7);
        JoueurDe joueurDe2 = new JoueurDe("Frodo", 6);
        MeilleurScore meilleurScore1 = meilleurScoreFactory.construire();
        meilleurScore1.ajouter(joueurDe2);
        meilleurScore1.ajouter(joueurDe);
        System.out.println(meilleurScore1);
        meilleurScore1.sauvegarder();
        MeilleurScore meilleurScore2 = meilleurScoreFactory.construire();
        meilleurScore2.charger();
        System.out.println("chargée : \n" + meilleurScore2);
    }

    /**
     * Sauvegarde le meilleur score courant
     */
    @Override
    public void sauvegarder() {
        JSONObject jsonObject = new JSONObject(this);
        String jsonString = jsonObject.toString();

        try (FileWriter fileWriter = new FileWriter(String.valueOf(Objects.requireNonNull(MeilleurScoreJSON.class.getClassLoader().getResource(FILEURI)).getPath()));
             BufferedWriter writer = new BufferedWriter(fileWriter);) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge le meilleur score sauvegarder de la même manière avant
     *
     * @return MeilleurScore
     */
    @Override
    public MeilleurScore charger() {
        String jsonText;
        try {
            Path path = Paths.get(Objects.requireNonNull(Objects.requireNonNull(MeilleurScoreJSON.class.getClassLoader().getResource(FILEURI)).toURI()));
            List<String> lines = Files.readAllLines(path);
            if (lines.isEmpty()) {
                this.setEntreeList(new ArrayList<>());
                return this;
            }
            jsonText = lines.get(0);

            JSONObject jsonMeilleurScore = new JSONObject(jsonText);
            JSONArray entreesJSONarray = (JSONArray) jsonMeilleurScore.get("entreeList");
            List<Entree> entrees = entreesJSONarray.toList().stream().map(o -> {
                HashMap<String, Object> mapEntree = (HashMap) o;
                return new Entree(mapEntree.get("nom").toString(), (Integer) mapEntree.get("score"));
            }).toList();
            this.setEntreeList(new ArrayList<>(entrees));

            return this;

        } catch (IOException | JSONException e) {
            return new MeilleurScoreJSON();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
