import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.println("--- Menu ---");
            System.out.println("1. ");
            System.out.println("2. Option 2");
            System.out.println("3. Quitter");
            System.out.print("Entrez votre choix : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "a":
                    // Code pour exécuter l'option 1
                    boolean subMenuQuit = false;
                    while (!subMenuQuit) {
                        System.out.println("--- Sous-menu ---");
                        System.out.println("1. Option 1");
                        System.out.println("2. Option 2");
                        System.out.println("3. Retour au menu principal");
                        System.out.print("Entrez votre choix : ");
                        String subMenuChoice = scanner.nextLine();

                        switch (subMenuChoice) {
                            case "1":
                                // Code pour exécuter l'option 1
                                break;
                            case "q":
                                // Code pour exécuter l'option 2
                                return;
                            case "r":
                                subMenuQuit = true;
                                break;
                            default:
                                System.out.println("Option non valide");
                                break;
                        }
                    }
                    break;
                case "q":
                    // Code pour exécuter l'option 2
                    return;
                case "3":
                    return;
                default:
                    System.out.println("Option non valide");
                    break;
            }
        }
    }
}
