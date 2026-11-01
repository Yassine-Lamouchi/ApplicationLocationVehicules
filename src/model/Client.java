package model;

public class Client {

    private static int compteurId = 1000; // pour générer un id unique

    private final String idClient;
    private String nom;
    private String prenom;

    public Client(String nom, String prenom) {
        this.idClient = "C" + compteurId++;
        this.nom = nom;
        this.prenom = prenom;
    }

    // Constructeur utilisé au chargement CSV
    public Client(String id, String nom, String prenom) {
        this.idClient = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getIdClient() {
        return idClient;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Client [" + idClient + "] : " + nom + " " + prenom;
    }
}

