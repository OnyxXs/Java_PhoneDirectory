import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.*;

public class test_jordan {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        boolean Menu = false;
        while (!Menu) {
            afficherMenu();
            String choix = scanner.nextLine();
            switch (choix) {
                case "a":
                    boolean add_contact = false;
                    while (!add_contact) {
                        add_contact();
                    }
                    break;
                case "l":
                    break;
                case "q":
                    return;
                default:
                    System.out.println("Option non valide");
                    break;
            }
        }
    }

    public static void afficherMenu() {
        ArrayList<String> menu = new ArrayList<>();
        System.out.println("-- Menu --");
        System.out.println("a- Ajouter un contact");
        System.out.println("l- Lister les contacts");
        System.out.println("q- Quitter");
        System.out.print("Entrez votre choix : ");
    }

    private static void add_contact() {
        Contact c = new Contact();
        System.out.println("Saisir le nom");
        c.setNom(scanner.nextLine());

        System.out.println("Saisir le prénom");
        c.setPrenom(scanner.nextLine());

        System.out.println("Saisir le mail");
        String email = scanner.nextLine();
        while (!isValidEmail(email)) {
            System.out.println("Mail non valide, saisir le mail");
            email = scanner.nextLine();
        }
        c.setMail(email);

        System.out.println("Saisir le téléphone");
        c.setTelephone(scanner.nextLine());

        while (true) {
            try {
                System.out.println("Saisir la date de naissance");
                c.setDateNaissance(scanner.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("Mauvaise date de naissance");
            }
        }
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}