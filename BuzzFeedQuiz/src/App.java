import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        
       
        HashMap<String, Integer> Cmatches = new HashMap<String, Integer>();

   
    for(int i = 0; i < 10; i++){
        Quiz game = new Quiz();
        String Match = game.playgame();
        if (Cmatches.containsKey(Match)) {
            Cmatches.put(Match, Cmatches.get(Match) + 1);
            // increases it by one!
        } else {
            Cmatches.put(Match, 1);
        }
        System.out.println("Results Frequency:");
        for(String key : Cmatches.keySet()){
            System.out.println(key + ": " + Cmatches.get(key));
}

}
}
}
