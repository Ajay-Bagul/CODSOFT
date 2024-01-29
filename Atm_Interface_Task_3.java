package codSoft;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    String user_id;
    String pin;
    double balance;
    StringBuilder transactionHistory;

    public User(String user_id, String pin) {
        this.user_id = user_id;
        this.pin = pin;
        this.balance = 0;
        this.transactionHistory = new StringBuilder();
    }
}

interface AtmInterface {
    void create_user(String user_id, String pin);
    boolean authenticate_user(String user_id, String pin);
    void display_menu();
    void perform_transaction(int choice);
    void display_transaction_history();
    void withdraw(double amount);
    void deposit(double amount);
    void transfer();
}

public class Atm_Interface_Task_3 implements AtmInterface {
    Map<String, User> users;
    User current_user;

    public Atm_Interface_Task_3() {
        this.users = new HashMap<>();
        this.current_user = null;
    }

    @Override
    public void create_user(String user_id, String pin) {
        User user = new User(user_id, pin);
        users.put(user_id, user);
    }

    @Override
    public boolean authenticate_user(String user_id, String pin) {
        if (users.containsKey(user_id) && users.get(user_id).pin.equals(pin)) {
            current_user = users.get(user_id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void display_menu() {
    	System.out.println("\n++++++++++++++++++++++++++++++++++");
        System.out.println("********* ATM INTERFACE **********");
        System.out.println("++++++++++++++++++++++++++++++++++\n");
        System.out.println("1) Transactions History");
        System.out.println("2) Withdraw");
        System.out.println("3) Deposit");
        System.out.println("4) Transfer");
        System.out.println("5) Exit");
    }

    @Override
    public void perform_transaction(int choice) {
        if (choice == 1) {
            display_transaction_history();
        } else if (choice == 2) {
            double amount = getDoubleInput("Enter withdrawal amount: ");
            withdraw(amount);
        } else if (choice == 3) {
            double amount = getDoubleInput("Enter deposit amount: ");
            deposit(amount);
        } else if (choice == 4) {
            transfer();
        } else if (choice == 5) {
            System.out.println("Quitting... Thank you vist again....");
            System.exit(0);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    @Override
    public void display_transaction_history() {
        System.out.println("Transaction History:");
        System.out.println(current_user.transactionHistory);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= current_user.balance) {
            current_user.balance -= amount;
            current_user.transactionHistory.append("Withdrawal: ₹" + amount).append("\n");
            System.out.println("Withdrawal successful. Remaining balance: ₹" + current_user.balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            current_user.balance += amount;
            current_user.transactionHistory.append("Deposit: ₹" + amount).append("\n");
            System.out.println("Deposit successful. New balance: ₹" + current_user.balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void transfer() {
        String target_user_id = getStringInput("Enter the recipient's user ID: ");

        if (users.containsKey(target_user_id)) {
            User target_user = users.get(target_user_id);
            double amount = getDoubleInput("Enter the transfer amount: ");

            if (amount > 0 && amount <= current_user.balance) {
                current_user.balance -= amount;
                target_user.balance += amount;

                current_user.transactionHistory.append("Transfer to " + target_user_id + ": ₹" + amount).append("\n");
                target_user.transactionHistory.append("Transfer from " + current_user.user_id + ": ₹" + amount).append("\n");

                System.out.println("Transfer successful. Remaining balance: ₹" + current_user.balance);
            } else {
                System.out.println("Transfer amount not valid or insufficient funds.");
            }
        } else {
            System.out.println("User with ID " + target_user_id + " not found.");
        }
    }

    private double getDoubleInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        double input = 0;

        while (true) {
            try {
                System.out.print(prompt);
                input = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return input;
    }

    private String getStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
    	Atm_Interface_Task_3 atm = new Atm_Interface_Task_3();
        atm.create_user("user123", "1234");
        atm.create_user("user456", "5678");
        atm.create_user("user789", "9012");
        System.out.println("###############################");
        System.out.println("######## HDFC BANK ATM ########");
        System.out.println("###############################\n");
        String user_id = atm.getStringInput("Enter user ID: ");
        String pin = atm.getStringInput    ("Enter PIN    : ");

        if (atm.authenticate_user(user_id, pin)) {
            while (true) {
                atm.display_menu();
                int choice = (int) atm.getDoubleInput("\nEnter your choice : ");
                atm.perform_transaction(choice);
            }
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }
}
