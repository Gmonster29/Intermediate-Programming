package Game;

import java.util.Scanner;

public class ErrorCheck {

    public static int getValidBound(Scanner sc) {
        // Variable to hold the user's input
        int bound = 0;

        // Loop to repeatedly prompt the user until a valid integer is entered
        while (true) {
       

            // Check if the input is a valid integer
            if (sc.hasNextInt()) {
                bound = sc.nextInt();
                break; // Exit the loop if the input is valid
            } else {

                System.out.println("This is not a valid number, please try again");

                // Discard the invalid input
                sc.next();
            }
        }
       return bound; 
    }
    
}
