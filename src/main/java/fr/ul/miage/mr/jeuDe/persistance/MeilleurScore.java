package fr.ul.miage.mr.jeuDe.persistance;

import fr.ul.miage.mr.jeuDe.Entree;
import fr.ul.miage.mr.jeuDe.JoueurDe;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public abstract class MeilleurScore {

    private List<Entree> entreeList;

    protected MeilleurScore(){
        this.entreeList = new ArrayList<>();
    }

    /**
     * Sauvegarde le meilleur score courant
     */
    public abstract void sauvegarder();

    /**
     * Charge le meilleur score sauvegarder de la même manière avant
     * @return MeilleurScore
     */
    public abstract MeilleurScore charger();

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

}
