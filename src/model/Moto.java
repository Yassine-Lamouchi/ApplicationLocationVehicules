package model;

public class Moto extends Vehicule {

    private int cylindre;
    private static int compteurMoto = 2000;

    public Moto(String marque, String modele, double prixJour, int cylindre) {
        super("M" + compteurMoto++, marque, modele, prixJour);
        this.cylindre = cylindre;
    }

    // ------------------- Constructeur utilisé au chargement CSV ---------------------
    public Moto(String ref, String marque, String modele, double prixJour, int cylindre) {
        super(ref, marque, modele, prixJour);
        this.cylindre = cylindre;
    }

    public int getCylindre() {
        return cylindre;
    }

    @Override
    public String afficheDetails() {
        return "Moto [" + reference + "] - " + marque + " " + modele
                + ", " + cylindre + " cm³, prix=" + prixJour + " dt/j, dispo=" + disponible;
    }
}

