package fr.ul.miage.mr.jeuDe.persistance;

/**
 * Redis
 */
public class MeilleurScoreMongoFactory implements AbstractMeilleurScoreFactory {

    /**
     * Crée le meilleur score correspondant
     *
     * @return implementation du MeilleurScore
     */
    @Override
    public MeilleurScore construire() {
        return new MeilleurScoreMongo();
    }
}
