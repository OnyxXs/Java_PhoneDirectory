import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.*;

public class App {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        boolean Menu = false;
        while (!Menu) {
            afficherMenu();
            System.out.print("redirection vers : ");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    System.out.println("---- Créer contact ----");
                    add_contact();
                    break;
                case "2":
                    System.out.println("-------- Liste --------");
                    listerContacts();
                    break;
                case "3":
                    System.out.println("--- Modifier contact --");
                    update_contact();
                    break;
                case "4":
                    System.out.println("-- Supprimer contact --");
                    supp_contact();
                    break;
                case "5":
                    System.out.println("-- Recherche contact --");
                    rechercherParPrenom();
                    break;
                case "q":
                    return;
                default:
                    System.out.println("Choisissez une option");
                    break;
            }
        }
    }

    public static void afficherMenu() {
        ArrayList<String> menus = new ArrayList<>();
        menus.add("-------- Menu ---------");
        menus.add("1- Ajouter un contact");
        menus.add("2- Liste des contacts");
        menus.add("3- Modifier un contact");
        menus.add("4- Supprimer un contact");
        menus.add("5- Rechercher un contact");
        menus.add("q- Quitter");
        for (String menu : menus) {
            System.out.println(menu);
        }
    }

    private static void rechercherParPrenom() {
        System.out.println("Recherche par prénom");
        System.out.print("Prénom : ");
        String prenomRecherche = scanner.nextLine();
        try {
            ArrayList<Contact> list = Contact.lister();
            ArrayList<Contact> resultatRecherche = new ArrayList<>();
            for (Contact contact : list) {
                if (contact.getPrenom().equalsIgnoreCase(prenomRecherche)) {
                    resultatRecherche.add(contact);
                }
            }
            if (resultatRecherche.isEmpty()) {
                System.out.println("Il n'y a pas de contact avec se prénom");
                System.out.println("1- Rechercher un contact");
                System.out.println("q- Quitter");
                System.out.print("redirection vers : ");
                String recherche = scanner.nextLine();
                switch (recherche) {
                    case "1":
                        rechercherParPrenom();
                        break;
                    case "q":
                        break;
                }
            } else {
                for (Contact contact : resultatRecherche) {
                    System.out.println();
                    System.out.println("CONTACT RECHERCHER");
                    System.out.println();
                    System.out.println("Nom : " + contact.getNom());
                    System.out.println("Prénom : " + contact.getPrenom());
                    System.out.println("Mail : " + contact.getMail());
                    System.out.println("Téléphone : " + contact.getTelephone());
                    System.out.println("Date de naissance : " + contact.getDateNaissance());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listerContacts() {
        try {
            ArrayList<Contact> list = Contact.lister();

            System.out.println("Liste des contact");
            int i = 1;
            for (Contact contact : list) {
                System.out.println(i + ": " + contact.getNom() + " " + contact.getPrenom());
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void add_contact() throws IOException {
        Contact c = new Contact();
        System.out.print("Saisir le nom : ");
        String nom = scanner.nextLine();
        while (nom.isBlank()) {
            System.out.println("Le nom est obligatoire");
            System.out.println();
            System.out.print("Saisir le nom : ");
            nom = scanner.nextLine();
        }
        c.setNom(nom);

        System.out.print("Saisir le prénom : ");
        String prenom = scanner.nextLine();
        while (prenom.isBlank()) {
            System.out.println("Le prénom est obligatoire");
            System.out.println();
            System.out.print("Saisir le prénom : ");
            prenom = scanner.nextLine();
        }
        c.setPrenom(prenom);

        System.out.print("Saisir le mail : ");
        String email = scanner.nextLine();
        while (!isValidEmail(email)) {
            System.out.println("Mail non valide");
            System.out.println();
            System.out.print("Saisir le mail : ");
            email = scanner.nextLine();
        }
        c.setMail(email);

        System.out.print("Saisir le téléphone : ");
        String phone = scanner.nextLine();
        while (!isValidPhone(phone)) {
            System.out.println("Numéro de téléphone non valide");
            System.out.println();
            System.out.print("Saisir le téléphone : ");
            phone = scanner.nextLine();
        }
        c.setTelephone(phone);

        System.out.print("Saisir la date de naissance : ");
        String date = scanner.nextLine();
        while (!isValidDate(date)) {
            System.out.println("Date de naissance non valide");
            System.out.println();
            System.out.print("Saisir la date de naissance : ");
            date = scanner.nextLine();
        }
        c.setDateNaissance(date);

        c.enregistrer();
        System.out.println("Contact enregistré");
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        String phoneRegex = "^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$";

        Pattern pat = Pattern.compile(phoneRegex);
        if (phone == null)
            return false;
        return pat.matcher(phone).matches();
    }

    public static boolean isValidDate(String date) {
        String dateRegex = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";

        Pattern pat = Pattern.compile(dateRegex);
        if (date == null)
            return false;
        return pat.matcher(date).matches();
    }

    public static boolean isValidName(String name) {
        return !name.isBlank();
    }

    private static void update_contact() {
        try {
            ArrayList<Contact> list = Contact.lister();
            if (list.isEmpty()) {
                System.out.println("Aucun contact enregistré");
                return;
            }
            System.out.println("Choisir un contact à modifier");
            int i = 1;
            for (Contact contact : list) {
                System.out.println(i + ": " + contact.getNom() + " " + contact.getPrenom());
                i++;
            }

            System.out.print("Modifier ce contact : ");

            int choix = Integer.parseInt(scanner.nextLine());
            Contact c = list.get(choix - 1);

            c.supprimer();

            System.out.println();
            System.out.print("Nouveau nom [" + c.getNom() + "]:");
            String nom = scanner.nextLine();
            if (!nom.isBlank())
                c.setNom(nom);

            System.out.print("Nouveau prénom [" + c.getPrenom() + "]:");
            String prenom = scanner.nextLine();
            if (!prenom.isBlank())
                c.setPrenom(prenom);

            while (true) {
                System.out.print("Nouveau mail [" + c.getMail() + "]:");
                String mail = scanner.nextLine();
                if (isValidEmail(mail)) {
                    c.setMail(mail);
                    break;
                }
                if (mail.isBlank()) {
                    c.getMail();
                    break;
                } else {
                    System.out.println("Mail non valide");
                    System.out.println();
                }
            }

            while (true) {
                System.out.print("Nouveau numéro de téléphone [" + c.getTelephone() + "]:");
                String telephone = scanner.nextLine();
                if (isValidPhone(telephone)) {
                    c.setTelephone(telephone);
                    break;
                }
                if (telephone.isBlank()) {
                    c.getTelephone();
                    break;
                } else {
                    System.out.println("Numéro de téléphone non valide");
                    System.out.println();
                }
            }

            while (true) {
                System.out.print("Nouvelle date de naissance [" + c.getDateNaissance() + "]:");
                String dateNaissance = scanner.nextLine();
                if (isValidDate(dateNaissance)) {
                    c.setDateNaissance(dateNaissance);
                    break;
                }
                if (dateNaissance.isBlank()) {
                    c.getDateNaissance();
                    break;
                } else {
                    System.out.println("Date de naissance non valide");
                    System.out.println();
                }
            }

            c.enregistrer();
            System.out.println("Contact mis à jour");
        } catch (Exception e) {
            System.out.println("Pas de contact modifier");
        }
    }

    private static void supp_contact() {
        try {
            ArrayList<Contact> list = Contact.lister();
            if (list.isEmpty()) {
                System.out.println("Aucun contact enregistré");
                return;
            }
            System.out.println("Choisir un contact à supprimer");
            int i = 1;
            for (Contact contact : list) {
                System.out.println(i + ": " + contact.getNom() + " " + contact.getPrenom());
                i++;
            }

            System.out.print("Supprimer ce contact : ");

            int choix = Integer.parseInt(scanner.nextLine());
            Contact c = list.get(choix - 1);

            c.supprimer();
            System.out.println("Contact supprimé");
        } catch (Exception e) {
            System.out.println("Pas de contact supprimer");
        }
    }
}