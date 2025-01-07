public class App {
    

public static void main(String[] args) {
    // Start the game by calling the playGame method
    int highscore = Integer.MAX_VALUE;
    for(int i = 0; i < 3; i++){
        NumberGuessGame game = new NumberGuessGame();
    game.playGame();
    if (game.guess < highscore){
highscore = game.guess;
System.out.println("high score is: " + highscore);
    }
}
}
}