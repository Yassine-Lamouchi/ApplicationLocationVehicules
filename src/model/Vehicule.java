package model;

public abstract class Vehicule {
    protected String reference;
    protected String marque;
    protected String modele;
    protected double prixJour;
    protected boolean disponible = true;

    public Vehicule(String reference, String marque, String modele, double prixJour) {
        this.reference = reference;
        this.marque = marque;
        this.modele = modele;
        this.prixJour = prixJour;
    }

    // pas d'objet direct possible
    public abstract String afficheDetails();

    public String getReference() {
        return reference;
    }

    public String getModele() {
        return modele;
    }

    public String getMarque() {
        return marque;
    }

    public double getPrixJour() {
        return prixJour;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setPrixJour(double prixJour) {
        this.prixJour = prixJour;
    }
}

