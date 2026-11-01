package main;

import model.Administrateur;
import model.Client;
import model.LocationException;
import model.Vehicule;
import service.AgenceLocation;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuAdmin {

    private static final Administrateur admin = new Administrateur("admin", "admin@*1234");

    public static void menu(AgenceLocation agence, Scanner sc) {

        System.out.print("Login : ");
        String login = sc.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = sc.nextLine();

        if (!admin.authentifier(login, mdp)) {
            System.out.println("Identifiants incorrects !");
            return;
        }

        String choix;
        do {
            System.out.println("\n===== MENU ADMINISTRATEUR =====");
            System.out.println("1. Ajouter véhicule");
            System.out.println("2. Supprimer véhicule");
            System.out.println("3. Modifier prix véhicule");
            System.out.println("4. Afficher parc");
            System.out.println("5. Ajouter client");
            System.out.println("6. Afficher clients");
            System.out.println("7. Louer un véhicule");
            System.out.println("8. Afficher locations actives");
            System.out.println("9. Restituer un véhicule");
            System.out.println("0. Retour menu principal");
            System.out.print("Votre choix : ");
            choix = sc.nextLine();

            try {
                switch (choix) {
                    case "1":
                        ajouterVehicule(agence, sc);
                        break;
                    case "2":
                        supprimerVehicule(agence, sc);
                        break;
                    case "3":
                        modifierPrix(agence, sc);
                        break;
                    case "4":
                        afficherParc(agence);
                        break;
                    case "5":
                        ajouterClient(agence, sc);
                        break;
                    case "6":
                        afficherClients(agence);
                        break;
                    case "7":
                        louerVehicule(agence, sc);
                        break;
                    case "8":
                        agence.afficherLocationsActives();
                        break;
                    case "9":
                        restituerVehicule(agence, sc);
                        break;
                    case "0":
                        System.out.println("Retour au menu principal...");
                        break;
                    default:
                        System.out.println("Choix invalide !");
                }
            } catch (LocationException e) {
                System.out.println("Erreur : " + e.getMessage());
            }

        } while (!choix.equals("0"));
    }

    // Méthodes pour gérer le menu

    private static void ajouterVehicule(AgenceLocation agence, Scanner sc) throws LocationException {
        System.out.print("Type (Voiture/Moto) : ");
        String type = sc.nextLine();
        System.out.print("Marque : ");
        String marque = sc.nextLine();
        System.out.print("Modèle : ");
        String modele = sc.nextLine();
        System.out.print("Prix/jour : ");
        double prix = Double.parseDouble(sc.nextLine());

        Vehicule v = null;
        switch (type.toLowerCase()) {
            case "voiture":
                System.out.print("Nombre de portes : ");
                int portes = Integer.parseInt(sc.nextLine());
                v = new model.Voiture(marque, modele, prix, portes);
                break;
            case "moto":
                System.out.print("Cylindre : ");
                int cyl = Integer.parseInt(sc.nextLine());
                v = new model.Moto(marque, modele, prix, cyl);
                break;
            default:
                System.out.println("Type de véhicule invalide !");
        }

        if (v != null) {
            agence.ajouterVehicule(v);
            System.out.println("Véhicule ajouté : " + v.afficheDetails());
        }
    }

    private static void supprimerVehicule(AgenceLocation agence, Scanner sc) throws LocationException {
        System.out.print("Référence véhicule à supprimer : ");
        String ref = sc.nextLine();
        agence.supprimerVehicule(ref);
        System.out.println("Véhicule supprimé.");
    }

    private static void modifierPrix(AgenceLocation agence, Scanner sc) throws LocationException {
        System.out.print("Référence véhicule : ");
        String ref = sc.nextLine();
        System.out.print("Nouveau prix : ");
        double prix = Double.parseDouble(sc.nextLine());
        agence.modifierPrix(ref, prix);
        System.out.println("Prix modifié.");
    }

    private static void afficherParc(AgenceLocation agence) {
        List<Vehicule> parc = agence.getParc();
        if (parc.isEmpty()) {
            System.out.println("Le parc est vide.");
        } else {
            for (Vehicule v : parc) {
                System.out.println(v.afficheDetails());
            }
        }
    }

    private static void ajouterClient(AgenceLocation agence, Scanner sc) throws LocationException {
        System.out.print("Nom : ");
        String nom = sc.nextLine();
        System.out.print("Prénom : ");
        String prenom = sc.nextLine();
        Client c = new Client(nom, prenom);
        agence.ajouterClient(c);
        System.out.println("Client ajouté : " + c);
    }

    private static void afficherClients(AgenceLocation agence) {
        List<Client> clients = agence.getClients();
        if (clients.isEmpty())
            System.out.println("Aucun client enregistré.");
        else
            clients.forEach(System.out::println);
    }

    private static void louerVehicule(AgenceLocation agence, Scanner sc) throws LocationException {
        System.out.print("Id client : ");
        String idClient = sc.nextLine();
        System.out.print("Référence véhicule : ");
        String ref = sc.nextLine();
        System.out.print("Date début (YYYY-MM-DD) : ");
        LocalDate debut = LocalDate.parse(sc.nextLine());
        System.out.print("Date fin (YYYY-MM-DD) : ");
        LocalDate fin = LocalDate.parse(sc.nextLine());

        Client c = agence.trouverClientParId(idClient);
        if (c == null) {
            System.out.println("Client introuvable !");
            return;
        }

        agence.louer(c, ref, debut, fin);
        System.out.println("Location enregistrée !");
    }

    private static void restituerVehicule(AgenceLocation agence, Scanner sc) throws LocationException {
        System.out.print("Référence véhicule à restituer : ");
        String ref = sc.nextLine();
        agence.restituer(ref);
        System.out.println("Véhicule restitué !");
    }
}

