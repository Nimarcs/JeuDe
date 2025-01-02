package fr.ul.miage.mr.jeuDe.modele;

import java.util.Observable;
import java.util.Random;

public class De extends Observable {

    private int valeur;

    public De(){

    }

    public void lancer(){
        Random random = new Random();
        valeur = random.nextInt(6) + 1;
        setChanged();
        notifyObservers();
    }

    public int getValeur() {
        return valeur;
    }
}
