package util;

import model.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FichierUtil {

    // ===================== SAUVEGARDE / CHARGEMENT PARC =====================

    public static void sauvegarderParc(File fichier, ArrayList<Vehicule> parc) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichier))) {

            for (Vehicule v : parc) {
                String type = v.getClass().getSimpleName();
                // getClass(): Returns the runtime class of this Object.
                // getSimpleName(): Returns the simple name of the underlying class as given in
                // the source code.
                String line = type + "," + v.getReference() + "," + v.getMarque() + "," + v.getModele() + ","
                        + v.getPrixJour() + "," + v.isDisponible();

                if (v instanceof Voiture voiture) {
                    line += "," + voiture.getNbPortes();
                } else if (v instanceof Moto moto) {
                    line += "," + moto.getCylindre();
                }
                bw.write(line);
                bw.newLine();
            }
        }
    }

    public static ArrayList<Vehicule> chargerParc(File fichier) throws IOException {

        ArrayList<Vehicule> parc = new ArrayList<>();
        if (!fichier.exists())
            return parc;

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] t = line.split(",");

                String type = t[0];
                String ref = t[1];
                String marque = t[2];
                String modele = t[3];
                double prix = Double.parseDouble(t[4]);
                boolean dispo = Boolean.parseBoolean(t[5]);

                Vehicule v = null;

                switch (type) {
                    case "Voiture": {
                        int portes = Integer.parseInt(t[6]);
                        v = new Voiture(ref, marque, modele, prix, portes);
                        break;
                    }
                    case "Moto": {
                        int cylindre = Integer.parseInt(t[6]);
                        v = new Moto(ref, marque, modele, prix, cylindre);
                        break;
                    }
                }
                if (v != null)
                    v.setDisponible(dispo);
                parc.add(v);
            }
        }
        return parc;
    }

    // ===================== CLIENTS =====================

    public static void sauvegarderClients(File fichier, ArrayList<Client> clients) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichier))) {

            for (Client c : clients) {
                String line = c.getIdClient() + "," + c.getNom() + "," + c.getPrenom();

                bw.write(line);
                bw.newLine();
            }
        }
    }

    public static ArrayList<Client> chargerClients(File fichier) throws IOException {

        ArrayList<Client> clients = new ArrayList<>();
        if (!fichier.exists())
            return clients;

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] t = line.split(",");

                String id = t[0];
                String nom = t[1];
                String prenom = t[2];
                Client c = new Client(id, nom, prenom);
                clients.add(c);
            }
        }
        return clients;
    }

    // ===================== LOCATIONS =====================

    public static void sauvegarderLocations(File fichier, ArrayList<Location> locations) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichier))) {

            for (Location l : locations) {
                String line = l.getIdClient() + "," + l.getIdVehicule() + "," + l.getDateDebut() + ","
                        + l.getDateFin() + "," + l.getMontantTotal();

                bw.write(line);
                bw.newLine();
            }
        }
    }

    public static ArrayList<Location> chargerLocations(File fichier) throws IOException {

        ArrayList<Location> locations = new ArrayList<>();
        if (!fichier.exists())
            return locations;

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] t = line.split(",");

                String idClient = t[0];
                String idVehicule = t[1];
                LocalDate debut = LocalDate.parse(t[2]);
                LocalDate fin = LocalDate.parse(t[3]);
                double prix = Double.parseDouble(t[4]);

                Location l = new Location(idClient, idVehicule, debut, fin, prix);
                locations.add(l);
            }
        }

        return locations;
    }
}

