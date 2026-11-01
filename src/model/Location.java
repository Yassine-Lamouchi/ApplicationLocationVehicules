package model;

import java.time.LocalDate;

public class Location {

    private String idClient;
    private String idVehicule;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double montantTotal;

    // ------------------- Constructeur ---------------------
    public Location(Client client, Vehicule vehicule, LocalDate dateDebut, LocalDate dateFin) {
        this.idClient = client.getIdClient();
        this.idVehicule = vehicule.getReference();
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;

        this.montantTotal = calculerMontant(vehicule.getPrixJour());
    }

    // ------------------- Constructeur utilisé au chargement CSV ---------------------
    public Location(String idClient, String idVehicule, LocalDate dateDebut, LocalDate dateFin, double montantTotal) {
        this.idClient = idClient;
        this.idVehicule = idVehicule;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montantTotal = montantTotal;
    }

    // ------------------- Calcul du montant ---------------------
    private double calculerMontant(double prixJour) {
        // Calculer le nombre de jours entre dateDebut et dateFin
        int jours = (int) dateDebut.until(dateFin).getDays();
        if (jours <= 0)
            jours = 1;
        return jours * prixJour;
    }

    // ------------------- Getters ---------------------
    public String getIdClient() {
        return idClient;
    }

    public String getIdVehicule() {
        return idVehicule;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    // ------------------- CSV (toString) ---------------------
    @Override
    public String toString() {
        return idClient + "," + idVehicule + "," + dateDebut + "," + dateFin + "," + montantTotal;
    }
}

