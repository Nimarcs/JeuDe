package fr.ul.miage.mr.jeuDe.persistance;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.UpdateResult;
import fr.ul.miage.mr.jeuDe.Entree;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MeilleurScoreMongo extends MeilleurScore{

    public static final String MONGODB_URI = "mongodb://localhost:27017/";

    /**
     * Sauvegarde le meilleur score courant
     */
    @Override
    public void sauvegarder() {
        // Replace the placeholder with your MongoDB deployment's connection string
        try (MongoClient mongoClient = MongoClients.create(MONGODB_URI)) {
            MongoDatabase database = mongoClient.getDatabase("jeuDe");
            MongoCollection<Document> collection = database.getCollection("meilleurScore");

            Bson query = eq(true);
            // Creates a new document
            Document replaceDocument = new Document().
                    append("entreeList", getEntreeList().stream().map(entree -> new Document()
                            .append(entree.getNom(), entree.getScore())).toList());

            // Instructs the driver to insert a new document if none match the query
            ReplaceOptions opts = new ReplaceOptions().upsert(true);

            // Replaces the first document that matches the filter with a new document
            UpdateResult result = collection.replaceOne(query, replaceDocument, opts);
            if (!result.wasAcknowledged()){
                System.out.println("La sauvegarde n'a pas eu de réponse de la part de la base de données");
            }

        } catch (MongoException e){
            System.out.println("Sauvegarde échoué");
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
        try (MongoClient mongoClient = MongoClients.create(MONGODB_URI)) {
            MongoDatabase database = mongoClient.getDatabase("jeuDe");
            MongoCollection<Document> collection = database.getCollection("meilleurScore");

            Document doc = collection.find(eq(true)).first();

            if (doc == null) {
                return new MeilleurScoreMongo();
            } else {
                String jsonText = doc.toJson();
                JSONObject jsonMeilleurScore = new JSONObject(jsonText);
                JSONArray entreesJSONarray = (JSONArray) jsonMeilleurScore.get("entreeList");
                List<Entree> entrees = entreesJSONarray.toList().stream().map(o -> {
                    HashMap<String, Object> mapEntree = (HashMap) o;
                    String nom = mapEntree.keySet().iterator().next();
                    return new Entree(nom, (Integer) mapEntree.get(nom));
                }).toList();
                this.setEntreeList(new ArrayList<>(entrees));

                return this;
            }

        } catch (MongoException e) {
            System.out.println("Chargement échoué");
            e.printStackTrace();
            return null;
        }
    }
}
