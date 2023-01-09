import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.println("-- Menu --");
            System.out.println("1- Ajouter un contact");
            System.out.println("2- Lister les contacts");
            System.out.println("3- Quitter");
            System.out.print("Entrez votre choix : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "a":
                    boolean add_contact = false;
                    while (!add_contact) {
                        System.out.println("-- Ajouter un contact --");
                        System.out.println("Prénom : ");
                        System.out.println("Nom : ");
                        System.out.println("Téléphone : ");
                        System.out.println("3. Retour au menu principal");
                        System.out.print("Entrez votre choix : ");
                        String add_contact_choix = scanner.nextLine();

                        switch (add_contact_choix) {
                            case "p":
                                break;
                            case "n":
                                break;
                            case "t":
                                break;
                            case "q":
                                return;
                            default:
                                System.out.println("Option non valide");
                                break;
                        }
                    }
                    break;
                case "l":
                    boolean subMenuQuit = false;
                    while (!subMenuQuit) {
                        System.out.println("-- Liste des contacts --");
                        System.out.println("Contact 1");
                        System.out.println("Contact 2");
                        System.out.println("3. Retour au menu principal");
                        System.out.print("Entrez votre choix : ");
                        String subMenuChoice = scanner.nextLine();

                        switch (subMenuChoice) {
                            case "1":
                                break;
                            case "2":
                                break;
                            case "q":
                                return;
                            default:
                                System.out.println("Option non valide");
                                break;
                        }
                    }
                    break;
                case "q":
                    return;
                default:
                    System.out.println("Option non valide");
                    break;
            }
        }
    }
}
