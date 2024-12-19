/*   Kai, Graham, Miles
A question class with Answers.
*/ 
import java.util.Scanner;

public class Question {
    // Fields
    String label;
    Answer[] possibleAnswers = new Answer[5];
    Scanner scanner = new Scanner(System.in);

    Question(String label) {
        this.label = label;
    }

    // ask a question, and return the category that corresponds to the answer
    Category ask(Scanner sc) {
        System.out.println(this.label);
        // prints out all the answer choices
        for (int i = 0; i < this.possibleAnswers.length; i++) {
            String choice = Integer.toString(i + 1);
            System.out.println("[" + choice + "]:" +
                    this.possibleAnswers[i].label);
        }
        int ans = getUserInt();
        //More error checking: Graham + Miles
        if (ans < 1 || ans > 5) {
            System.out.println("Unidentifiable input. Please enter 1-5");
            ans = getUserInt();
        }
            return possibleAnswers[ans - 1].cat;

    }
    //ERROR CHECKING: KAI
    public int getUserInt() {
        if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Please enter an actual valid number.");
                scanner.next();
                return getUserInt();
            }
        }


}
