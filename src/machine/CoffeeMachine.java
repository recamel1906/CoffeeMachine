/* Coffee Machine Simulator */
package machine;
import java.util.Scanner;

public class CoffeeMachine {

    /* Conversion Factors */
    public static final int cupsToWater = 200; // 1 cup = 200 mL water
    public static final int cupsToMilk = 50;   // 1 cup = 50 mL milk
    public static final int cupsToBeans = 15; // 1 cup = 15 g beans



    /* Start Making Coffee */
    public static void startMakingCoffee() {

        System.out.println("Starting to make a coffee");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        System.out.println("Pouring some milk into the cup");
        System.out.println("Coffee is ready!");
        System.out.println();
    }

    /* Extract Coffee Ingredients */
    public static void coffeeIngredients() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Write how many cups of coffee you will need: ");
        int numberOfCups = scanner.nextInt();

        System.out.println("For " + numberOfCups + " cups of coffee you will need:");
        System.out.println(numberOfCups * cupsToWater + " mL of water");
        System.out.println(numberOfCups * cupsToMilk  + " mL of milk");
        System.out.println(numberOfCups * cupsToBeans + " g of coffee beans");
    }

    /* Request if there is enough coffee for everyone */
    public static void requestCoffeeAmount() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Write how many mL of water the coffee machine has: ");
        int waterMl = scanner.nextInt();
        System.out.print("Write how many mL of milk the coffee machine has: ");
        int milkMl = scanner.nextInt();
        System.out.print("Write how many grams of coffee beans the coffee machine has: ");
        int beansGrams = scanner.nextInt();
        System.out.print("Write how many cups of coffee you will need: ");
        int cupsAvail = scanner.nextInt();

        // Calculate cups requested
        int cupsRequested = Math.min(waterMl / cupsToWater, Math.min(milkMl / cupsToMilk, beansGrams / cupsToBeans));

        if (cupsRequested == cupsAvail) {
            System.out.println("Yes I can make that amount of coffee.");
        }
        else if (cupsRequested > cupsAvail) {
            System.out.println("No I can only make " + cupsAvail + " cups(s) of coffee.");
        }
        else {
            System.out.println("Yes, I can make that amount of coffee (and even " +
                    (cupsAvail - cupsRequested) + " more than that)");
        }
    }

    /* Store array of coffee ingredients for different types of coffee */
    public static int[] coffeeIngredients(int typeCoffee) {

        int[] coffeeIngredients = new int[4];

        // espresso
        if (typeCoffee == 1) {

            coffeeIngredients[0] = 250; // mL water
            coffeeIngredients[1] = 0;   // mL milk
            coffeeIngredients[2] = 16;  // grams beans
            coffeeIngredients[3] = 4; // price
        }
        else if (typeCoffee == 2) {

            coffeeIngredients[0] = 350; // mL water
            coffeeIngredients[1] = 75;   // mL milk
            coffeeIngredients[2] = 20;  // grams beans
            coffeeIngredients[3] = 7; // price
        }
        else if (typeCoffee == 3) {

            coffeeIngredients[0] = 200; // mL water
            coffeeIngredients[1] = 100;   // mL milk
            coffeeIngredients[2] = 12;  // grams beans
            coffeeIngredients[3] = 6; // price
        }
        return coffeeIngredients;

    }


    /* Print coffee contents */
    public static void updateCoffeeMachine(int[] contents) {

        System.out.println("The coffee machine has:");
        System.out.println(contents[0] + " mL of water");
        System.out.println(contents[1] + " mL of milk");
        System.out.println(contents[2] + " grams of beans");
        System.out.println(contents[3] + " disposable cups");
        System.out.println("$" + contents[4] + " available");
    }

    /* Detect if contents of coffee are sufficient */
    public static void checkForResources(int[] actualContents, int[] requestContents) {

        String[] elements = {"water", "milk", "beans", "cups", "money"};

        for (int i = 0; i < elements.length; i++) {

            if (actualContents[i] < requestContents[i]) {
                System.out.println("Sorry, not enough " + elements[i] + "!");
            }
            else {
                System.out.println("I have enough resources, making you a coffee!");
            }
        }
    }

    /* Coffee Machine Simulator */
    public static void machineSimulator() {

        // Initial contents in coffee machine
        int[] contentsMachine = {1200, 540, 120, 9, 550};
        updateCoffeeMachine(contentsMachine);
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write action (buy, fill, take): ");
        String action = scanner.next();

        if (action.equals("buy")) {

            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
            int purchaseChoice = scanner.nextInt();

            // Extract ingredients for chosen coffee
            int[] coffee = coffeeIngredients(purchaseChoice);

            // Update coffee machine
            int[] newMachine = {contentsMachine[0] - coffee[0],
                    contentsMachine[1] - coffee[1], contentsMachine[2] - coffee[2],
                    contentsMachine[3]--, contentsMachine[4] - coffee[3]};

            updateCoffeeMachine(newMachine);
        }
        else if (action.equals("fill")) {

            System.out.println("Write how many mL of water do you want to add: ");
            int waterReq = scanner.nextInt();
            System.out.println("Write how many ml of milk do you want to add: ");
            int milkReq = scanner.nextInt();
            System.out.println("Write how many grams of coffee beans do you want to add: ");
            int beansReq = scanner.nextInt();
            System.out.println("Write how many disposable cups of coffee do you want to add: ");
            int cups = scanner.nextInt();
            int[] requestContents = {waterReq, milkReq, beansReq, cups};

            // Check for resources
            checkForResources(contentsMachine, requestContents);

            int[] newMachine = new int[contentsMachine.length];
            for (int i = 0; i < newMachine.length - 1; i++) {
                newMachine[i] = contentsMachine[i] + requestContents[i];
            }
            newMachine[contentsMachine.length - 1] = contentsMachine[contentsMachine.length - 1];

            // Print updated coffee machine contents
            System.out.println();
            updateCoffeeMachine(newMachine);
        }
        else if (action.equals("take")) {

            System.out.println("I gave you $" + contentsMachine[4]);
            int[] newMachine = {contentsMachine[0], contentsMachine[1],
                                contentsMachine[2], contentsMachine[3], 0};

            updateCoffeeMachine(newMachine);
        }
    }


    /* Test Program */
    public static void main(String[] args) {

        // Start process
        System.out.println("Start Making Coffee...");
        startMakingCoffee();
        System.out.println(); // keep spacing between each action

        // Extract coffee ingredients
        System.out.println("Requesting coffee ingredients....");
        coffeeIngredients();
        System.out.println(); // keep spacing between each action

        // Request coffee
        System.out.println("Requesting coffee....");
        requestCoffeeAmount();
        System.out.println(); // keep spacing between each action

        // Coffee Machine Simulator
        System.out.println("Simulating a Coffee Machine...");
        machineSimulator();
        System.out.println(); // keep spacing between each action


    }

}
