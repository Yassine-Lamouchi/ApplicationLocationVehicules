package service;

import model.*;
import util.FichierUtil;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AgenceLocation {

    // Gestion du parc
    private final ArrayList<Vehicule> parc;
    private final ArrayList<Client> clients;
    private final ArrayList<Location> locationsActives;

    public AgenceLocation() {
        this.parc = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.locationsActives = new ArrayList<>();
    }

    // --------------------- Parc automobile ---------------------
    /** Partie 1 **/

    public List<Vehicule> getParc() {
        return new ArrayList<>(parc);
    }

    public List<Client> getClients() {
        return new ArrayList<>(clients); // retourne une copie pour protéger la liste originale
    }

    public List<Location> getLocation() {
        return new ArrayList<>(locationsActives); // retourne une copie pour protéger la liste originale
    }

    public void ajouterVehicule(Vehicule v) throws LocationException {
        if (v == null) {
            throw new LocationException("Le véhicule ne peut pas être null.");
        }

        // Vérifier si une référence identique existe déjà
        if (chercherVehiculeParId(v.getReference()) != null) {
            throw new LocationException("Un véhicule avec cette référence existe déjà.");
        }

        // Ajouter au parc
        parc.add(v);
    }

    public void supprimerVehicule(String reference) throws LocationException {
        if (reference == null || reference.trim().isEmpty()) {
            throw new LocationException("La référence ne peut pas être vide.");
        }
        Iterator<Vehicule> it = parc.iterator();
        while (it.hasNext()) {
            Vehicule v = it.next();
            // Si on trouve le véhicule

            if (v.getReference().equals(reference)) {
                // Vérifier disponibilité

                if (!v.isDisponible()) {
                    throw new LocationException("Impossible de supprimer : le véhicule est loué.");
                }
                it.remove();
                return;
            }
        }
        // Si on sort de la boucle sans trouver
        throw new LocationException("Véhicule introuvable : " + reference);
    }

    public Vehicule chercherVehiculeParId(String id) {
        for (Vehicule v : parc) {
            if (v.getReference().equals(id)) {
                return v;
            }
        }
        return null;
    }

    public void modifierPrix(String reference, double nouveauPrix) throws LocationException {
        if (nouveauPrix < 0)
            throw new LocationException("Prix négatif impossible.");
        Vehicule v = chercherVehiculeParId(reference);
        if (v == null)
            throw new LocationException("Véhicule introuvable : " + reference);
        v.setPrixJour(nouveauPrix);
    }

    public List<Vehicule> vehiculesDisponibles() {
        List<Vehicule> dispo = new ArrayList<>();
        for (Vehicule v : parc) {
            if (v.isDisponible())
                dispo.add(v);
        }
        return dispo;
    }

    /** fin partie 1 **/

    /** partie 2 **/

    // --------------------- Gestion des clients ---------------------

    public void ajouterClient(Client client) throws LocationException {
        if (client == null) {
            throw new LocationException("Le client ne peut pas être null.");
        }

        // Vérifier si un client avec le même ID existe déjà
        if (trouverClientParId(client.getIdClient()) != null) {
            throw new LocationException("Un client avec l'ID " + client.getIdClient() + " existe déjà.");
        }

        // Ajouter le client à la liste
        clients.add(client);
    }

    public Client trouverClientParId(String id) {
        if (id == null)
            return null;
        for (Client c : clients) {
            if (c.getIdClient().equals(id))
                return c;
        }
        return null;
    }

    // --------------------- Gestion des locations ---------------------

    /**
     * Crée une location entre client et véhicule
     */
    public void louer(Client client, String vehiculeId, LocalDate debut, LocalDate fin) throws LocationException {
        Vehicule v = chercherVehiculeParId(vehiculeId);
        if (v == null)
            throw new LocationException("Véhicule introuvable : " + vehiculeId);
        if (!v.isDisponible())
            throw new LocationException("Véhicule non disponible : " + vehiculeId);
        if (debut.isAfter(fin))
            throw new LocationException("Date de début après la date de fin.");

        Location loc = new Location(client, v, debut, fin);
        v.setDisponible(false);
        locationsActives.add(loc);
    }

    /**
     * Affiche toutes les locations actuellement en cours
     */
    public void afficherLocationsActives() {
        if (locationsActives.isEmpty()) {
            System.out.println("Aucune location active.");
            return;
        }
        for (Location l : locationsActives) {
            System.out.println(l);
        }
    }

    /**
     * Restitue un véhicule loué
     */
    public void restituer(String vehiculeId) throws LocationException {
        if (vehiculeId == null || vehiculeId.isEmpty()) {
            throw new LocationException("La référence du véhicule ne peut pas être vide.");
        }
        Iterator<Location> it = locationsActives.iterator();

        while (it.hasNext()) {
            Location loc = it.next();

            // Vérifier si c'est la bonne location

            if (loc.getIdVehicule().equals(vehiculeId)) {

                // retrouver l'objet Vehicule correspondant
                Vehicule v = chercherVehiculeParId(vehiculeId);

                if (v != null) {
                    v.setDisponible(true); // Rendre le véhicule disponible
                }
                // Supprimer la location de la liste
                it.remove();
                return;
            }
        }
        // Si aucune location n'a été trouvée

        throw new LocationException("Aucune location trouvée pour le véhicule : " + vehiculeId);
    }

    /** fin partie 2 **/

    public void chargerDonnees() {
        try {
            parc.clear();
            parc.addAll(FichierUtil.chargerParc(new File("parc.csv")));

            clients.clear();
            clients.addAll(FichierUtil.chargerClients(new File("clients.csv")));

            locationsActives.clear();
            locationsActives.addAll(FichierUtil.chargerLocations(new File("locations.csv")));

            System.out.println(">> Données chargées avec succès.");
        } catch (Exception e) {
            System.out.println(">> Chargement impossible : " + e.getMessage());
        }
    }

    // Méthode : sauvegarde complète
    public void sauvegarderDonnees() {
        try {
            FichierUtil.sauvegarderParc(new File("parc.csv"), parc);
            FichierUtil.sauvegarderClients(new File("clients.csv"), clients);
            FichierUtil.sauvegarderLocations(new File("locations.csv"), locationsActives);

            System.out.println(">> Données sauvegardées avec succès.");
        } catch (Exception e) {
            System.out.println(">> Erreur sauvegarde : " + e.getMessage());
        }
    }
}

