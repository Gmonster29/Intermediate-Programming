
/*
 * Kai, Graham, Miles
 * This is the class where we create the Quiz and run it. It has the main method.  
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Quiz {
        static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) throws Exception {
                // Create Categories
                //KAI + GRAHAM + MILES
                Category Sky = new Category("Sky", "You act like a teen girl who loves animals and has a scar, you live in with a shark in water");
                Category TnTina = new Category("TnTina", "You are a quality fortnite boss who loves tnt who lives on a rig");
                Category Brutus = new Category("Brutus","You are an overweight big fornite boss who lives underground with a minigun. You command the room!");
                Category Meowscles = new Category("Meowscles", "You are a buff cat who lives in his \"catty-corner\" with a shotgun and movement item");
                Category Midas = new Category("Midas", "You are a god that turns everything to gold, runs the map, is a leader and has a drum gun");
                // Create Questions
                //QUESTIONS: FIRST 5: GROUP EFFORT BETWEEN MILES AND GRAHAM
                Question q1 = new Question("Which colors do you like the most?");
                // Attach Answers to Questions
                q1.possibleAnswers[0]= new Answer("Gold", Midas);
                q1.possibleAnswers[1] = new Answer("Black", Brutus);
                q1.possibleAnswers[2] = new Answer("Blue", Sky);
                q1.possibleAnswers[3] = new Answer("Red", TnTina);
                q1.possibleAnswers[4] = new Answer("Tan", Meowscles);

                Question q2 = new Question("What type of music do you like the most?");
                q2.possibleAnswers[0]= new Answer("Classical", Midas);
                q2.possibleAnswers[1] = new Answer("Hip-Hop", Brutus);
                q2.possibleAnswers[2] = new Answer("Pop", Sky);
                q2.possibleAnswers[3] = new Answer("Rock", TnTina);
                q2.possibleAnswers[4] = new Answer("Jazz", Meowscles);

                // ... more questions here
                Question q3 = new Question("What's your ideal friday night?");
                q3.possibleAnswers[0]= new Answer("A fancy dinner", Midas);
                q3.possibleAnswers[1] = new Answer("Party with friends", Brutus);
                q3.possibleAnswers[2] = new Answer("Exploring a new hobby", Sky);
                q3.possibleAnswers[3] = new Answer("Movie night at home", TnTina);
                q3.possibleAnswers[4] = new Answer("Going to sleep early", Meowscles);

                Question q4 = new Question("Which pet matches your vibe the most?");
                q4.possibleAnswers[0]= new Answer("Fish", Midas);
                q4.possibleAnswers[1] = new Answer("Dog", Brutus);
                q4.possibleAnswers[2] = new Answer("Bird", Sky);
                q4.possibleAnswers[3] = new Answer("Hamster", TnTina);
                q4.possibleAnswers[4] = new Answer("Cat", Meowscles);

                Question q5 = new Question("What season do you like the most?");
                q5.possibleAnswers[0]= new Answer("Winter", Midas);
                q5.possibleAnswers[1] = new Answer("Fall", Brutus);
                q5.possibleAnswers[2] = new Answer("Summer", Sky);
                q5.possibleAnswers[3] = new Answer("Spring", TnTina);
                q5.possibleAnswers[4] = new Answer("All", Meowscles);
                //Last three: Kai
                Question q6 = new Question("What is your ideal way to spend a day off?");
                q6.possibleAnswers[0]= new Answer("Working on a personal project or hobby", Midas);
                q6.possibleAnswers[1] = new Answer("Relaxing at home with a good book or movie and eating a lot", Brutus);
                q6.possibleAnswers[2] = new Answer("Taking time to journal or meditate", Sky);
                q6.possibleAnswers[3] = new Answer("Throwing a party or hanging out with friends", TnTina);
                q6.possibleAnswers[4] = new Answer("Exploring a new city or hiking trail", Meowscles);

                Question q7 = new Question("What’s your go-to snack during an adventure?");
                q7.possibleAnswers[0]= new Answer("Granola bars or trail mix", Midas);
                q7.possibleAnswers[1] = new Answer("Chocolate or candy", Brutus);
                q7.possibleAnswers[2] = new Answer("Chips or popcorn", Sky);
                q7.possibleAnswers[3] = new Answer("Homemade snacks", TnTina);
                q7.possibleAnswers[4] = new Answer("Fresh fruit", Meowscles);

                Question q8 = new Question(" Which movie genre best fits your life story?");
                q8.possibleAnswers[0]= new Answer("Fantasy or sci-fi epic", Midas);
                q8.possibleAnswers[1] = new Answer("Laugh-out-loud comedy", Brutus);
                q8.possibleAnswers[2] = new Answer("Uplifting indie film", Sky);
                q8.possibleAnswers[3] = new Answer("Heartfelt drama", TnTina);
                q8.possibleAnswers[4] = new Answer("Action-packed thriller", Meowscles);

                // For each question, ask, read input, store answer.
                gameIntro();
                //EXTRA CREDIT: KAI -- Made an arraylist then shuffle--questions appear in random order every time.
                ArrayList<Question> qList = new ArrayList<>();
                qList.add(q1);
                qList.add(q2);
                qList.add(q3);
                qList.add(q4);
                qList.add(q5);
                qList.add(q6);
                qList.add(q7);
                qList.add(q8);
                Collections.shuffle(qList);
                for (Question q : qList) {
                        Category c = q.ask(sc);
                        c.points++;
                }
                // Get most common category from the questions asked
                // Return Category
                Category[] cList = { Midas, Brutus, Sky, TnTina, Meowscles };
                // these need to be in the same order or the points will be incorrect!
                int index = getMostPopularCatIndex(cList);
                System.out.println("If you were a fortnite character, you would be " + cList[index].label + ". ");
                System.out.println(cList[index].description);

        }

        public static void gameIntro() {
                // requires 1 to keep going
                System.out.println("Which Fortnite Character Are You?");
                System.out.println("You get to choose numbers 1-5 for every question. Enter '1' to play!");
                int play = sc.nextInt();
                if (play != 1) {
                        System.out.println("Unidentifiable input. Please enter '1' to play");
                        gameIntro();
                }
        }

        // returns the index that is the max
        // the tie breaker is the first Category that has the count is the "max" :/ 
        public static int getMostPopularCatIndex(Category[] counts) {
                int maxCount = 0;
                int maxIndex = 0;
                for (int i = 0; i < counts.length; i++) {
                        if (counts[i].points > maxCount) {
                                maxCount = counts[i].points;
                                maxIndex = i;
                        }
                }
                return maxIndex;
        }
}
