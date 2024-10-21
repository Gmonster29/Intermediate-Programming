
import java.util.Scanner;


public class Javainterview {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Collect user inputs
        System.out.print("What's your name? ");
        String name = scanner.nextLine();


        System.out.print("What's your favorite ice cream flavor? ");
        String favoriteflavor = scanner.nextLine();


        // Print inputs
        System.out.println("Name: " + name);
        System.out.println("Favorite Flavor: " + favoriteflavor);


      
        if (favoriteflavor.equalsIgnoreCase("MintChip")) {
            specialMessage(name, true);
        } else {
            specialMessage(name, false);
        }


        scanner.close();
    }


    // Method to handle different outputs
    public static void specialMessage(String name, boolean likesMintChip) {
        if (likesMintChip) {
            System.out.println("Oh my god, " + name + "! I love MintChip also!");
        } else {
            System.out.println(name + ", thats disguesting !");
        }
    }
}

