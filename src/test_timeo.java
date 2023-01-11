// test Samba
import java.util.ArrayList;
import java.util.Scanner;

public class test_timeo {
    private static Scanner _scan = new Scanner (System.in);
    public static void main(String[] args) throws Exception {
        while (true){
        menu();
        String choix = _scan.nextLine();
        switch (choix){
            case "1":
            break;
            case "2":
            break;
            case "q":
            return;
            default:
            System.out.println("réessayer avec une bonne touche");
            break;
        }
        }
    } 
    
    public static void menu(){
        ArrayList<String> menu =new ArrayList<>();
        System.out.println("ajouter contact");
        System.out.println("lister contact");
        System.out.println("quitter l'application");
    }
    public class Contact {
        private String nom;
        private String prenom;
        private String mail;
        private String telephone;
        private Date dateNaissance;
    
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
    
        public Date getDateNaissance() {
            return dateNaissance;
        }
}

//System.out.println (menu);
//ajouter contact
//lister contact
//q pour quitter l'appli 
