// test Samba
import java.util.ArrayList;
import java.util.Scanner;

public class test_samba {
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
}


//System.out.println (menu);
//ajouter contact
//lister contact
//q pour quitter l'appli 
