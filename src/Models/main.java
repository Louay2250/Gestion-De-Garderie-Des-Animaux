/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Azertyy
 */
public class main {
     private static Scanner scanner = new Scanner(System.in);

    private static List<Animal> animaux = new ArrayList<>();
    private static List<Client> clients = new ArrayList<>();
    private static List<Zone> zones = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static List<Personnel> personnels = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("** Menu principal **");
            System.out.println("1. Gestion des animaux");
            System.out.println("2. Gestion des clients");
            //System.out.println("3. Gestion des zones");
            System.out.println("4. Gestion des réservations");
            //System.out.println("5. Gestion du personnel");
            System.out.println("6. Quitter");

            int choix = scanner.nextInt();

            switch (choix) {
                case 1 -> gestionAnimaux();
                case 2 -> gestionClients();
               // case 3 -> gestionZones();
                case 3 -> gestionReservations();
               // case 5 -> gestionPersonnel();
                case 4 -> {
                    System.out.println("Au revoir!");
                    return;
                }
                default -> System.out.println("Erreur : Veuillez saisir un choix valide.");
            }
        }
    }
    
    private static void gestionAnimaux() {
        while (true) {
            System.out.println("** Menu de gestion des animaux **");
            System.out.println("1. Ajouter un animal");
            System.out.println("2. Supprimer un animal");
            System.out.println("3. Afficher la liste des animaux");
            System.out.println("4. Retourner au menu principal");

            int choix = scanner.nextInt();

            switch (choix) {
                case 1 -> ajouterAnimal();
                //case 2 -> supprimerAnimal();
                case 2 -> afficherAnimaux();
                case 3 -> {
                    return;
                }
                default -> System.out.println("Erreur : Veuillez saisir un choix valide.");
            }
        }
    }

    private static void ajouterAnimal() {
        System.out.println("Entrez le nom de l'animal : ");
        String nomAnimal = scanner.next();

        System.out.println("Entrez la date de naissance de l'animal (yyyy-MM-dd) : ");
        // Date dateNaissanceAnimal;
         //dateNaissanceAnimal = new Date(scanner.next());
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print("Enter the animal's birthdate (YYYY-MM-DD): ");
        String userInput = scanner.next();
        Date dateNaissanceAnimal=null;

        try {
            
            // Parse the user input into a Date object
           dateNaissanceAnimal = dateFormat.parse(userInput);

            // Now you can use the dateNaissanceAnimal object as needed
            System.out.println("Birthdate: " + dateNaissanceAnimal);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    

        System.out.println("Entrez le type d'animal : ");
        String typeAnimal = scanner.next();

        System.out.println("Entrez le nom du propriétaire de l'animal : ");
        String nomProprietaire = scanner.next();

        Client proprietaire = trouverClientParNom(nomProprietaire);

        if (proprietaire != null) {
            Animal animal = new Animal(proprietaire, nomAnimal, dateNaissanceAnimal, typeAnimal);
            animaux.add(animal);
            System.out.println("Animal ajouté avec succès.");
        } else {
            System.out.println("Le propriétaire spécifié n'existe pas.");
        }
    }

//    private static void supprimerAnimal() {
//        System.out.println("Entrez le nom de l'animal à supprimer : ");
//        String nomAnimal = scanner.next();
//
//        Animal animal = trouverAnimalParNom(nomAnimal);
//
//        if (animal != null) {
//            animaux.remove(animal);
//            System.out.println("Animal supprimé avec succès.");
//        } else {
//            System.out.println("L'animal spécifié n'existe pas.");
//        }
//    }

    private static void afficherAnimaux() {
        for (Animal animal : animaux) {
            System.out.println(animal);
        }
    }

    private static void gestionClients() {
        while (true) {
        System.out.println("** Menu de gestion des clients**");
        System.out.println("1. Ajouter un client");
        System.out.println("2. Supprimer un client");
        System.out.println("3. Afficher la liste des clients");
        System.out.println("4. Afficher les détails d'un client");
        System.out.println("5. Retourner au menu principal");

        int choix = scanner.nextInt();

        switch (choix) {
            case 1 -> ajouterClient();
            case 2 -> supprimerClient();
            case 3 -> afficherListeClients();
            case 4 -> afficherDetailsClient();
            case 5 -> {
                return;
                }
            default -> System.out.println("Erreur : Veuillez saisir un choix valide.");
        }
    }
    }
    private static void ajouterClient() {
    System.out.println("Entrez le CIN du client : ");
    int CIN = scanner.nextInt();

    System.out.println("Entrez le nom du client : ");
    String nomClient = scanner.next();

    System.out.println("Entrez l'adresse du client : ");
    String adresseClient = scanner.next();

    System.out.println("Entrez le numéro de téléphone du client : ");
    String telephoneClient = scanner.next();

    System.out.println("Entrez l'email du client : ");
    String emailClient = scanner.next();

    Client client = new Client(CIN, nomClient, adresseClient, telephoneClient, emailClient);
    clients.add(client);
    System.out.println("Client ajouté avec succès.");
}

private static void supprimerClient() {
    System.out.println("Entrez le CIN du client à supprimer : ");
    int CIN = scanner.nextInt();

    Client client = trouverClientParCIN(CIN);

    if (client != null) {
        clients.remove(client);
        System.out.println("Client supprimé avec succès.");
    } else {
        System.out.println("Le client spécifié n'existe pas.");
    }
}

private static void afficherListeClients() {
    for (Client client : clients) {
        System.out.println(client);
    }
}

private static void afficherDetailsClient() {
    System.out.println("Entrez le CIN du client dont vous souhaitez voir les détails : ");
    int CIN = scanner.nextInt();

    Client client = trouverClientParCIN(CIN);

    if (client != null) {
        System.out.println("Informations du client :");
        System.out.println("CIN : " + client.getCin());
        System.out.println("Nom : " + client.getNom());
        System.out.println("Adresse : " + client.getAdresse());
        System.out.println("Numéro de téléphone : " + client.getTelephone());
        System.out.println("Email : " + client.getEmail());
    } else {
        System.out.println("Le client spécifié n'existe pas.");
    }
}
private static void gestionReservations() {
    while (true) {
        System.out.println("** Menu de gestion des réservations **");
        System.out.println("1. Créer une réservation");
        System.out.println("2. Annuler une réservation");
        System.out.println("3. Afficher la liste des réservations");
        System.out.println("4. Retourner au menu principal");

        int choix = scanner.nextInt();

        switch (choix) {
            case 1 -> creerReservation();
            case 2 -> annulerReservation();
            case 3 -> afficherListeReservations();
            case 4 -> {
                return;
            }
            default -> System.out.println("Erreur : Veuillez saisir un choix valide.");
        }
    }
}
private static void creerReservation() {
    System.out.println("Entrez le nom du client : ");
    String nomClient = scanner.next();

    Client client = trouverClientParNom(nomClient);

    if (client != null) {
        System.out.println("Sélectionnez l'animal à réserver : ");
        afficherAnimaux();

        int choixAnimal = scanner.nextInt();
        Animal animal = animaux.get(choixAnimal - 1);

        System.out.println("Entrez la date de début de la réservation (yyyy-MM-dd) : ");
        Date dateDebut = new Date (scanner.next());

        System.out.println("Entrez la date de fin de la réservation (yyyy-MM-dd) : ");
        Date dateFin = new Date(scanner.next());
        System.out.println("choose zone");
        Zone z= new Zone("A", 20, 10);
        float tarif=20;
        Reservation reservation = new Reservation(dateDebut, dateFin,z,client,tarif);
        reservations.add(reservation);
        System.out.println("Réservation créée avec succès.");
    } else {
        System.out.println("Le client spécifié n'existe pas.");
    }
}

private static void annulerReservation() {
    System.out.println("Entrez le numéro de la réservation à annuler : ");
    int numeroReservation = scanner.nextInt();

    //Reservation reservation = trouverReservationParNumero(numeroReservation);

//    if (reservation != null) {
//        reservations.remove(reservation);
//        System.out.println("Réservation annulée avec succès.");
//    } else {
//        System.out.println("La réservation spécifiée n'existe pas.");
//    }
}

private static void afficherListeReservations() {
    for (Reservation reservation : reservations) {
        System.out.println(reservation);
    }
}

    private static Client trouverClientParNom(String nomClient) {
    Client client1=null;
        for (Client client : clients) {
        if (client.getNom().equals(nomClient)) {
            client1=client;
        }
    }
        return client1;
         
}

    private static Client trouverClientParCIN(int CIN) {
        Client client1=null;
       for (Client client : clients) {
        if (client.getCin()==CIN) {
            client1= client;
        }      
    }
       return client1;   
    }
}

