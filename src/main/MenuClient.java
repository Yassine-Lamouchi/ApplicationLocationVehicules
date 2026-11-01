package main;

import model.Client;
import model.LocationException;
import model.Vehicule;
import service.AgenceLocation;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuClient {

    public static void menu(AgenceLocation agence, Scanner sc) {
        System.out.print("Votre id client : ");
        String idClient = sc.nextLine();
        Client client = agence.trouverClientParId(idClient);
        if (client == null) {
            System.out.println("Client introuvable !");
            return;
        }

        String choix;
        do {
            System.out.println("\n===== MENU CLIENT =====");
            System.out.println("1. Consulter véhicules disponibles");
            System.out.println("2. Louer un véhicule");
            System.out.println("3. Restituer un véhicule");
            System.out.println("0. Retour menu principal");
            System.out.print("Votre choix : ");
            choix = sc.nextLine();

            try {
                switch (choix) {
                    case "1":
                        consulterVehicules(agence);
                        break;
                    case "2":
                        louerVehicule(agence, client, sc);
                        break;
                    case "3":
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

    private static void consulterVehicules(AgenceLocation agence) {
        List<Vehicule> dispo = agence.vehiculesDisponibles();
        if (dispo.isEmpty())
            System.out.println("Aucun véhicule disponible.");
        else {
            for (Vehicule v : dispo) {
                System.out.println(v.afficheDetails());
            }
        }
    }

    private static void louerVehicule(AgenceLocation agence, Client client, Scanner sc) throws LocationException {
        System.out.print("Référence véhicule : ");
        String ref = sc.nextLine();
        System.out.print("Date début (YYYY-MM-DD) : ");
        LocalDate debut = LocalDate.parse(sc.nextLine());
        System.out.print("Date fin (YYYY-MM-DD) : ");
        LocalDate fin = LocalDate.parse(sc.nextLine());
        agence.louer(client, ref, debut, fin);
        System.out.println("Location enregistrée !");
    }

    private static void restituerVehicule(AgenceLocation agence, Scanner sc) throws LocationException {
        System.out.print("Référence véhicule à restituer : ");
        String ref = sc.nextLine();
        agence.restituer(ref);
        System.out.println("Véhicule restitué !");
    }
}

