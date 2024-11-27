package fr.ul.miage.mr.jeuDe.persistance;

/**
 * JSON
 */
public class MeilleurScoreJSONFactory implements AbstractMeilleurScoreFactory{
    /**
     * Crée le meilleur score correspondant
     *
     * @return implementation du MeilleurScore
     */
    @Override
    public MeilleurScore construire() {
        return new MeilleurScoreJSON();
    }
}
