package main;

import service.AgenceLocation;

import java.util.Scanner;

public class AppLocation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AgenceLocation agence = new AgenceLocation();
        agence.chargerDonnees();

        while (true) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Espace Administrateur");
            System.out.println("2. Espace Client");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            String choix = sc.nextLine();

            switch (choix) {
                case "1":
                    MenuAdmin.menu(agence, sc);
                    break;
                case "2":
                    MenuClient.menu(agence, sc);
                    break;
                case "0":
                    System.out.println("Sauvegarde des données...");
                    agence.sauvegarderDonnees();
                    System.out.println("Au revoir !");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Choix invalide !");
            }
        }

    }
}

