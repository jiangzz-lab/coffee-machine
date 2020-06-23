import java.util.Scanner;

public class CoffeeMachine {
    private int waterOwned = 400;
    private int milkOwned = 540;
    private int beansOwned = 120;
    private int cupsOwned = 9;
    private int moneyOwned = 550;
    private MachineState state;

    private boolean endOfOperation;

    // Constructor: the machine is at INITIAL state when created
    CoffeeMachine() {
        this.state = MachineState.INITIAL;
    }

    // getUserInput() takes input from the user; here we use System.in stream as an example
    private String getUserInput() {
        if (this.state == MachineState.INITIAL) {
            System.out.println("Write action (buy, fill, take, remaining, exit) ");
        }
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    // setState() sets the state of this machine to one of MachineState instances
    private void setState(MachineState state) {
        this.state = state;
    }

    // init() toggle the machine to the state indicated by user input
    private void init() {
        String input = getUserInput();
        if (input.equals("buy")) {
            setState(MachineState.BUY);
            return;
        }
        if (input.equals("fill")) {
            setState(MachineState.FIll);
            return;
        }
        if (input.equals("take")) {
            setState(MachineState.TAKE);
            return;
        }
        if (input.equals("remaining")) {
            setState(MachineState.SHOWREMAINS);
            return;
        }
        if (input.equals("exit")) {
            setState(MachineState.EXIT);
            return;
        }
    }

    // buy() makes the coffee indicated by user input and receives money
    public void buy() {
        int waterConsumed = 0;
        int milkConsumed = 0;
        int beansConsumed = 0;
        final int cupsConsumed = 1;
        int moneyEarned = 0;

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String input = getUserInput();

        setState(MachineState.INITIAL);

        if (input.equals("back")) {
            return;
        }

        int coffeeOption = Integer.parseInt(input);
        switch (coffeeOption) {
            case 1:
                waterConsumed = 250;
                milkConsumed = 0;
                beansConsumed = 16;
                moneyEarned = 4;
                break;
            case 2:
                waterConsumed = 350;
                milkConsumed = 75;
                beansConsumed = 20;
                moneyEarned = 7;
                break;
            case 3:
                waterConsumed = 200;
                milkConsumed = 100;
                beansConsumed = 12;
                moneyEarned = 6;
                break;
            default:
                System.out.println("We don't have this option!");
                break;
        }

        if (this.waterOwned < waterConsumed) {
            System.out.println("Sorry, not enough water!");
            return;
        }
        if (this.milkOwned < milkConsumed) {
            System.out.println("Sorry, not enough milk!");
            return;
        }
        if (this.beansOwned < beansConsumed) {
            System.out.println("Sorry, not enough coffee beans!");
            return;
        }
        if (this.cupsOwned < cupsConsumed) {
            System.out.println("Sorry, not enough disposable cups!");
            return;
        }
        this.waterOwned -= waterConsumed;
        this.milkOwned -= milkConsumed;
        this.beansOwned -= beansConsumed;
        this.cupsOwned -= cupsConsumed;
        this.moneyOwned += moneyEarned;
        System.out.println("I have enough resources, making you a coffee!");
    }

    // fill() adds resources to this machine whose quantities are indicated by user input
    public void fill() {
        System.out.println("Write how many ml of water do you want to add: ");
        int watertoAdd = Integer.parseInt(getUserInput());
        System.out.println("Write how many ml of milk do you want to add: ");
        int milktoAdd = Integer.parseInt(getUserInput());
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        int beanstoAdd = Integer.parseInt(getUserInput());
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int cupstoAdd = Integer.parseInt(getUserInput());

        this.waterOwned += watertoAdd;
        this.milkOwned += milktoAdd;
        this.beansOwned += beanstoAdd;
        this.cupsOwned += cupstoAdd;

        setState(MachineState.INITIAL);
    }

    // take() gives all money contained in this machine to a staff
    public void take() {
        System.out.println("I give you $" + this.moneyOwned);
        this.moneyOwned = 0;
        setState(MachineState.INITIAL);
    }

    // showRemains() shows the quantities of all resources
    public void showRemains() {
        System.out.println("The coffee machine has:");
        System.out.println(this.waterOwned + " of water");
        System.out.println(this.milkOwned + " of milk");
        System.out.println(this.beansOwned + " of coffee beans");
        System.out.println(this.cupsOwned + " of disposable cups");
        System.out.println("$" + this.moneyOwned + " of money");
        setState(MachineState.INITIAL);
    }

    // exit() sets this.endOfOperation to ture which is used to terminate the operation of this machine
    public void exit() {
        this.endOfOperation = true;
    }

    // operate() makes the machine operate at the current state
    public void operate() {
        this.endOfOperation = false;
        while (!endOfOperation) {
            switch (this.state) {
                case INITIAL :
                    init();
                    break;
                case BUY :
                    buy();
                    break;
                case FIll :
                    fill();
                    break;
                case TAKE :
                    take();
                    break;
                case SHOWREMAINS :
                    showRemains();
                    break;
                case EXIT :
                    exit();
                    break;
            }
        }
    }
}
