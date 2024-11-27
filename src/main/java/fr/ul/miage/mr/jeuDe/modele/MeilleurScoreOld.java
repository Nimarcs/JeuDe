package fr.ul.miage.mr.jeuDe.modele;

import fr.ul.miage.mr.jeuDe.Entree;
import fr.ul.miage.mr.jeuDe.JoueurDe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MeilleurScoreOld {

    private static MeilleurScoreOld instance;

    private final List<Entree> entreeList;

    private MeilleurScoreOld() {
        entreeList = new ArrayList<>();
    }

    public void ajouter(JoueurDe joueur) {
        if (joueur == null) return;
        entreeList.add(new Entree(joueur.getNom(), joueur.getScore()));
    }

    public List<Entree> getEntreeList() {
        return Collections.unmodifiableList(entreeList);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entree entree : entreeList.stream().sorted(Comparator.comparingInt(Entree::getScore).reversed()).toList()) {
            stringBuilder.append(entree.toString()).append('\n');
        }
        return stringBuilder.toString();
    }

    public static synchronized MeilleurScoreOld getInstance() {
        if (instance == null) {
            instance = new MeilleurScoreOld();
        }
        return instance;
    }
}
