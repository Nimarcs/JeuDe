package fr.ul.miage.mr.jeuDe.modele;

import java.util.Random;

public class De {

    private int valeur;

    public De(){

    }

    public void lancer(){
        Random random = new Random();
        valeur = random.nextInt(6) + 1;
    }

    public int getValeur() {
        return valeur;
    }
}
