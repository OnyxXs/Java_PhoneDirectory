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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void enregistrer() throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv", true)));
        try {
            pw.println(this.toString());
        } finally {
            pw.close();
        }
    }

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
