package model;

public class Voiture extends Vehicule {

    private int nbPortes;
    private static int compteurVoiture = 1000;

    public Voiture(String marque, String modele, double prixJour, int nbPortes) {
        super("V" + compteurVoiture++, marque, modele, prixJour);
        this.nbPortes = nbPortes;
    }

    // ------------------- Constructeur utilisé au chargement CSV ---------------------
    public Voiture(String ref, String marque, String modele, double prixJour, int nbPortes) {
        super(ref, marque, modele, prixJour);
        this.nbPortes = nbPortes;
    }

    public int getNbPortes() {
        return nbPortes;
    }

    @Override
    public String afficheDetails() {
        return "Voiture [" + reference + "] - " + marque + " " + modele
                + ", " + nbPortes + " portes" + ", prix=" + prixJour + " dt/j, dispo=" + disponible;
    }
}

