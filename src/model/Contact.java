package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

public class Contact {
    private static final String SEPARATEUR = ";";

private String nom;
private String prenom;
private String mail;
private String telephone;
private String dateNaissance;

/**
 * Retourne le nom du contact.
 * 
 * @return le nom du contact en tant que chaîne de caractères.
 */
public String getNom() {
    return nom;
}

/**
 * Définit le nom du contact.
 * 
 * @param nom le nom du contact en tant que chaîne de caractères.
 */
public void setNom(String nom) {
    this.nom = nom;
}

/**
 * Retourne le prénom du contact.
 * 
 * @return le prénom du contact en tant que chaîne de caractères.
 */
public String getPrenom() {
    return prenom;
}

/**
 * Définit le prénom du contact.
 * 
 * @param prenom le prénom du contact en tant que chaîne de caractères.
 */
public void setPrenom(String prenom) {
    this.prenom = prenom;
}

/**
 * Retourne l'adresse courriel du contact.
 * 
 * @return l'adresse courriel du contact en tant que chaîne de caractères.
 */
public String getMail() {
    return mail;
}

/**
 * Définit l'adresse courriel du contact.
 * 
 * @param mail l'adresse courriel du contact en tant que chaîne de caractères.
 */
public void setMail(String mail) {
    this.mail = mail;
}

/**
 * Retourne le numéro de téléphone du contact.
 * 
 * @return le numéro de téléphone du contact en tant que chaîne de caractères.
 */
public String getTelephone() {
    return telephone;
}

/**
Cette méthode permet de définir le numéro de téléphone d'un contact.
@param telephone Le numéro de téléphone du contact en tant que chaîne de caractères.
*/
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

/**
 * Retourne la date de naissance du contact.
 * 
 * @return la date de naissance du contact en tant que chaîne de caractères.
 */
public String getDateNaissance() {
    return dateNaissance;
}

/**
 * Définit la date de naissance du contact.
 * 
 * @param dateNaissance la date de naissance du contact en tant que chaîne de caractères.
 */
public void setDateNaissance(String dateNaissance) {
    this.dateNaissance = dateNaissance;
}

/**
 * Enregistre le contact dans un fichier nommé "contacts.csv".
 * 
 * @throws IOException si une erreur se produit lors de l'écriture dans le fichier.
 */
public void enregistrer() throws IOException {
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv", true)));
    try {
        pw.println(this.toString());
    } finally {
        pw.close();
    }
}

/**
 * Liste tous les contacts du fichier nommé "contacts.csv"
 * 
 * @return ArrayList des objets Contact
 * @throws FileNotFoundException 
 * @throws IOException 
 * @throws ParseException 
 */
public static ArrayList<Contact> lister() throws FileNotFoundException, IOException, ParseException {
    ArrayList<Contact> list = new ArrayList<>();
    try (BufferedReader buf = new BufferedReader(new FileReader("contacts.csv"))) {
        String ligne = buf.readLine();
        while (ligne != null) {
            String[] tab = ligne.split(SEPARATEUR);
            Contact c = new Contact();
            c.setNom(tab[0]);
            c.setPrenom(tab[1]);
            c.setMail(tab[2]);
            c.setTelephone(tab[3]);
            c.setDateNaissance(tab[4]);
            list.add(c);
            ligne = buf.readLine();
        }
    }
    return list;
}

/**
Cette méthode permet de retourner un contact sous forme de chaîne de caractères en utilisant le séparateur pour diviser les différentes valeurs.
@return La chaîne de caractères représentant le contact.
*/
    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append(this.getNom());
        build.append(SEPARATEUR);
        build.append(this.getPrenom());
        build.append(SEPARATEUR);
        build.append(this.getMail());
        build.append(SEPARATEUR);
        build.append(this.getTelephone());
        build.append(SEPARATEUR);
        build.append(this.getDateNaissance());
        return build.toString();
    }

/**
Cette méthode permet de supprimer un contact du fichier "contacts.csv" en comparant les différentes valeurs du contact.
@throws IOException 
@throws ParseException 
*/
    public void supprimer() throws IOException, ParseException {
        ArrayList<Contact> contacts = lister();
        for (Contact c : contacts) {
            if (c.getNom().equals(this.nom) && c.getPrenom().equals(this.prenom) && c.getMail().equals(this.mail)
                    && c.getTelephone().equals(this.telephone) && c.getDateNaissance().equals(this.dateNaissance)) {
                contacts.remove(c);
                break;
            }
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv")));
        try {
            for (Contact c : contacts) {
                pw.println(c.toString());
            }
        } finally {
            pw.close();
        }
    }

}
