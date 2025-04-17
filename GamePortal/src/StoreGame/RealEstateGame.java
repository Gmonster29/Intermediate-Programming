package StoreGame;
//Miles worked on making the transactions work(Buying items), and the store front update each turn based on user input,

// I also made the prices update withtin the classes. I also did the error checking
//Graham worked on implementing the pastpurchasecountm to show the player how many times they went once the game ends. 
//Also worked on the different return prices for each sub class of item. Also did the decimal format
//Angel worked on creating the classes and items, as well as setting up the storefront 

import java.util.ArrayList;
import java.util.Scanner;

import Game.Game;
import Game.GameWriteable;

import java.util.Random;
import java.io.File;
import java.text.DecimalFormat;

class Item {
    int purchases = 0;
    String name;
    double price;
    int quantity;

    static void UpdateItem() {

    }

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getreturn() {
        return 0.5 * price;
    }
}

class Apartment extends Item {
    public Apartment(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public double getreturn() {
        return 0.2 * price;
    }
}

class House extends Item {
    public House(String name, double basePrice, int bedrooms, int bathrooms, int quantity) {
        super(name, basePrice + (bedrooms * 150000) + (bathrooms * 50000), quantity);
    }

    @Override
    public double getreturn() {
        return 0.35 * price;
    }
}

class Business extends Item {
    double originalPrice;

    public Business(String name, double basePrice, int quantity) {
        super(name, basePrice, quantity);
        this.originalPrice = basePrice;

    }

    @Override
    public double getreturn() {
        return 0.5 * originalPrice;
    }
}

class Store {
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<String> transactions = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public String buyItem(String itemName, Player player) {

        for (Item item : items) {
            if (item.name.equalsIgnoreCase(itemName) && item.quantity > 0 && player.balance >= item.price) {
                player.balance -= item.price;
                player.assets.add(item);
                transactions.add("Bought " + item.name + " for $" + item.price);
                item.quantity--;
                player.purchaseCount++;
                if (item instanceof Business) { // https://www.geeksforgeeks.org/instanceof-keyword-in-java/
                    item.price *= 1.1;
                }
                return "You bought " + item.name + "!";
            }
        }
        return "Transaction failed. Check balance or item availability.";
    }
}

class Player {
    double balance;
    ArrayList<Item> assets = new ArrayList<>();
    int purchaseCount = 0;
    public String getScore;

    public Player(double balance) {
        this.balance = balance;
        double num = 1234567.89;
        DecimalFormat df = new DecimalFormat("#,##0.00");
        System.out.println(df.format(num)); // https://www.geeksforgeeks.org/decimalformat-topattern-method-in-java/
    }

    public double calculateIncome() {
        double income = 0;
        for (Item asset : assets) {
            income += asset.getreturn();
        }
        return income;
    }
}

public class RealEstateGame implements GameWriteable {
    Player player = new Player(500000);

    public static void main(String[] args) {
        RealEstateGame game = new RealEstateGame();
        game.play();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store();
        Random random = new Random();

        store.addItem(new Apartment("Condo", 500000, 100));
        store.addItem(new Apartment("Studio", 750000, 100));
        store.addItem(new Apartment("2 Bedroom", 1000000, 100));
        store.addItem(new House("Modern Mansion", 1500000, random.nextInt(8) + 1, random.nextInt(7) + 1, 50));
        store.addItem(new House("Ski House", 2000000, random.nextInt(10) + 1, random.nextInt(9) + 1, 50));
        store.addItem(new House("Wood Cabin", 500000, random.nextInt(5) + 1, random.nextInt(4) + 1, 50));
        store.addItem(new Business("Medium rental space", 15000, 100));
        store.addItem(new Business("Large rental space", 20000, 100));
        store.addItem(new Business("Small rental space", 10000, 100));
        store.addItem(new Item("Save", 0, 100));

        System.out.println("Welcome to the Real Estate Game! You start with ,000. Reach public billion to win.");

        while (player.balance < 1000000000) {
            System.out.println("Your balance: $" + player.balance);
            System.out.println("Potential income: $" + player.calculateIncome());
            player.balance += player.calculateIncome();
            System.out.println("Available properties:");
            for (Item item : store.items) {
                System.out.println(item.name + " - $" + item.price + " (Quantity: " + item.quantity + ")");
            }

            System.out.print("Enter the name of the property to buy (or type 'exit' to quit): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for playing! Your final balance: $" + player.balance);
                break;
            }

            String result = store.buyItem(choice, player);
            System.out.println(result);
        }

        if (player.balance >= 1000000000) {
            player.getScore = String.valueOf(player.purchaseCount);
            System.out.println("Congratulations! You reached a billion and won the game!");
            System.out.println("You reached 1 billion dollars in " + player.getScore + " tries");

            scanner.close();
        }
    }

    public String getGameName() {
        return "Real Estate Game";
    }

    @Override
    public String getScore() {
        return String.valueOf(player.purchaseCount);
    }

    @Override
    public boolean isHighScore(String score, String currentHighScore) {
        System.err.println("here");
        if (currentHighScore == null || score == null) {
            return true; // If there is no current high score, any score is a high score
        }
        try {
            return Integer.parseInt(score) < Integer.parseInt(currentHighScore);
        } catch (NumberFormatException e) {
            System.err.println("Invalid score format.");
            return false;
        } // copilot
    }
}
