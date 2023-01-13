import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Collections;
import java.util.Comparator;
//import org.fusesource.jansi.AnsiConsole;
//import com.googlecode.lanterna.gui2.*;
//import com.googlecode.lanterna.TerminalSize;
//import com.googlecode.lanterna.TextColor;
// jar cd out
//cd artifacts % cd tkt_jar 
//java -jar tkt.jar 
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
                    System.out.println("---- " + "\033[34mCréer contact \033[0m" + "----");
                    add_contact();
                    break;
                case "2":
                    System.out.println("-------- " + "\033[34mListe \033[0m" + "--------");
                    listerContacts();
                    break;
                case "3":
                    System.out.println("--- " + "\033[34mModifier contact \033[0m" + "--");
                    update_contact();
                    break;
                case "4":
                    System.out.println("-- " + "\033[34mSupprimer contact \033[0m" + "--");
                    supp_contact();
                    break;
                case "5":
                    System.out.println("-- " + "\033[34mRecherche contact \033[0m" + "--");
                    System.out.println("Recherche par prénom");
                    rechercherParPrenom();
                    break;
                case "6":
                    tri_contact();
                    System.out.print("redirection vers : ");
                    String tri = scanner.nextLine();
                    switch (tri) {
                        case "1":
                            System.out.println();
                            System.out.println("\033[34mLISTE PAR NOM CROISSANT\033[0m");
                            System.out.println();
                            tri_Nom();
                            break;
                        case "2":
                            System.out.println();
                            System.out.println("\033[34mLISTE PAR NOM DECROISSANT\033[0m");
                            System.out.println();
                            tri_Nominverse();
                            break;
                        case "3":
                            System.out.println();
                            System.out.println("\033[34mLISTE PAR MAIL\033[0m");
                            System.out.println();
                            tri_Mail();
                            break;
                        case "4":
                            System.out.println();
                            System.out.println("\033[34mLISTE PAR DATE DE NAISSANCE\033[0m");
                            System.out.println();
                            tri_Date();
                            break;
                        case "5":
                            System.out.println();
                            System.out.println("\033[34mLISTE PAR PRENOM CROISSANT\033[0m");
                            System.out.println();
                            tri_Prenom();
                            break;
                        case "6":
                            System.out.println();
                            System.out.println("\033[34mLISTE PAR PRENOM DECROISSANT\033[0m");
                            System.out.println();
                            tri_Prenominverse();
                            break;
                        case "q":
                            break;
                        default:
                            System.out.println("\033[31mChoisissez une option\033[0m");
                            break;
                    }
                    break;
                case "q":
                    return;
                default:
                    System.out.println("\033[31mChoisissez une option\033[0m");
                    break;
            }
        }
    }

    public static void afficherMenu() {
        ArrayList<String> menus = new ArrayList<>();
        menus.add("-------- " + "\033[34mMenu \033[0m" + "---------");
        menus.add("1- Ajouter un contact");
        menus.add("2- Liste des contacts");
        menus.add("3- Modifier un contact");
        menus.add("4- Supprimer un contact");
        menus.add("5- Rechercher un contact");
        menus.add("6- Trier les contacts");
        menus.add("q- Quitter");
        for (String menu : menus) {
            System.out.println(menu);
        }
    }

    public static void tri_contact() {
        ArrayList<String> tri = new ArrayList<>();
        System.out.println("---- " + "\033[34mTrier contact \033[0m" + "----");
        System.out.println("differents types de tri");
        System.out.println("1- Tri par nom croissant");
        System.out.println("2- Tri par nom décroissant");
        System.out.println("3- Tri par mail");
        System.out.println("4- Tri par date de naissance");
        System.out.println("5- Tri par prénom croissant");
        System.out.println("6- Tri par prénom décroissant");
        System.out.println("q- Quitter");
        for (String tri_3 : tri) {
            System.out.println(tri_3);
        }
    }

    private static void rechercherParPrenom() {
        System.out.print("Prénom : ");
        String prenomRecherche = scanner.nextLine();
        try {
            ArrayList<Contact> list = Contact.lister();
            long nbResultat = list.stream()
                    .filter(c -> c.getPrenom().toLowerCase().startsWith(prenomRecherche))
                    .peek(c -> {
                        System.out.println();
                        System.out.println("\033[34mCONTACT RECHERCHER\033[0m");
                        System.out.println();
                        System.out.println("Nom : " + c.getNom());
                        System.out.println("Prénom : " + c.getPrenom());
                        System.out.println("Mail : " + c.getMail());
                        System.out.println("Téléphone : " + c.getTelephone());
                        System.out.println("Date de naissance : " + c.getDateNaissance());
                    })
                    .count();
            if (nbResultat == 0) {
                System.out.println("\033[31mAucun contact ne commence par : \033[0m" + prenomRecherche);
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
            System.out.println("\033[31mLe nom est obligatoire\033[0m");
            System.out.println();
            System.out.print("Saisir le nom : ");
            nom = scanner.nextLine();
        }
        c.setNom(nom);

        System.out.print("Saisir le prénom : ");
        String prenom = scanner.nextLine();
        while (prenom.isBlank()) {
            System.out.println("\033[31mLe prénom est obligatoire\033[0m");
            System.out.println();
            System.out.print("Saisir le prénom : ");
            prenom = scanner.nextLine();
        }
        c.setPrenom(prenom);

        System.out.print("Saisir le mail : ");
        String email = scanner.nextLine();
        while (!isValidEmail(email)) {
            System.out.println("\033[31mMail non valide\033[0m");
            System.out.println();
            System.out.print("Saisir le mail : ");
            email = scanner.nextLine();
        }
        c.setMail(email);

        System.out.print("Saisir le téléphone : ");
        String phone = scanner.nextLine();
        while (!isValidPhone(phone)) {
            System.out.println("\033[31mNuméro de téléphone non valide\033[0m");
            System.out.println();
            System.out.print("Saisir le téléphone : ");
            phone = scanner.nextLine();
        }
        c.setTelephone(phone);

        System.out.print("Saisir la date de naissance : ");
        String date = scanner.nextLine();
        while (!isValidDate(date)) {
            System.out.println("\033[31mDate de naissance non valide\033[0m");
            System.out.println();
            System.out.print("Saisir la date de naissance : ");
            date = scanner.nextLine();
        }
        c.setDateNaissance(date);

        c.enregistrer();
        System.out.println("\033[32mContact enregistré\033[0m");
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
                System.out.println("\033[31mAucun contact enregistré\033[0m");
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
                    System.out.println("\033[31mMail non valide\033[0m");
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
                    System.out.println("\033[31mNuméro de téléphone non valide\033[0m");
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
                    System.out.println("\033[31mDate de naissance non valide\033[0m");
                    System.out.println();
                }
            }

            c.enregistrer();
            System.out.println("Contact mis à jour");
        } catch (Exception e) {
            System.out.println("\033[31mPas de contact modifié\033[0m");
        }
    }

    private static void supp_contact() {
        try {
            ArrayList<Contact> list = Contact.lister();
            if (list.isEmpty()) {
                System.out.println("\033[31mAucun contact enregistré\033[0m");
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
            System.out.println("\033[32mContact supprimé\033[0m");
        } catch (Exception e) {
            System.out.println("\033[31mPas de contact supprimé\033[0m");
        }
    }

    private static void tri_Nom() {
        try {
            ArrayList<Contact> list = Contact.lister();

            Collections.sort(list, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    return c1.getNom().toLowerCase().compareTo(c2.getNom().toLowerCase());
                }
            });

            int i = 1;
            for (Contact contact : list) {
                System.out.println(i + ": " + contact.getNom() + " " + contact.getPrenom());
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void tri_Nominverse() {
        try {
            ArrayList<Contact> list = Contact.lister();

            Collections.sort(list, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    return c2.getNom().toLowerCase().compareTo(c1.getNom().toLowerCase());
                }
            });

            int i = 1;
            for (Contact contact : list) {
                System.out.println(i + ": " + contact.getNom() + " " + contact.getPrenom());
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void tri_Mail() {
        try {
            ArrayList<Contact> list = Contact.lister();

            Collections.sort(list, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    return c1.getMail().toLowerCase().compareTo(c2.getMail().toLowerCase());
                }
            });

            int i = 1;
            for (Contact contact : list) {
                System.out.println(i + ": " + contact.getMail());
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void tri_Date() {
        try {
            ArrayList<Contact> list = Contact.lister();

            Collections.sort(list, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    return c1.getDateNaissance().compareTo(c2.getDateNaissance().toLowerCase());
                }
            });

            int i = 1;
            for (Contact contact : list) {
                System.out.println(
                        i + ": " + contact.getDateNaissance() + " " + contact.getNom() + " " + contact.getPrenom());
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public class Compare {
        public static ArrayList<Contact> triParNom(ArrayList<Contact> list) {
            Collections.sort(list, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    return c1.getNom().toLowerCase().compareTo(c2.getNom().toLowerCase());
                }
            });
            return list;
        }

        public static ArrayList<Contact> triParMail(ArrayList<Contact> list) {
            Collections.sort(list, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    return c1.getMail().toLowerCase().compareTo(c2.getMail().toLowerCase());
                }
            });
            return list;
        }

        public static ArrayList<Contact> triParDateNaissance(ArrayList<Contact> list) {
            Collections.sort(list, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    return c1.getDateNaissance().compareTo(c2.getDateNaissance());
                }
            });
            return list;
        }
    }

    private static void tri_Prenom() {
        try {
            ArrayList<Contact> list = Contact.lister();

            Collections.sort(list, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    return c1.getPrenom().toLowerCase().compareTo(c2.getPrenom().toLowerCase());
                }
            });

            int i = 1;
            for (Contact contact : list) {
                System.out.println(i + ": " + contact.getPrenom() + " " + contact.getNom());
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void tri_Prenominverse() {
        try {
            ArrayList<Contact> list = Contact.lister();

            Collections.sort(list, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    return c2.getPrenom().toLowerCase().compareTo(c1.getPrenom().toLowerCase());
                }
            });

            int i = 1;
            for (Contact contact : list) {
                System.out.println(i + ": " + contact.getPrenom() + " " + contact.getNom());
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}