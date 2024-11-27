package fr.ul.miage.mr.jeuDe.persistance;


public interface AbstractMeilleurScoreFactory {

    /**
     * Crée le meilleur score correspondant
     * @return implementation du MeilleurScore
     */
    public MeilleurScore construire();

}
