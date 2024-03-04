import java.util.Scanner;

// User class to represent ATM users
class User {
    private String userID;
    private String userPIN;
    private double accountBalance;

    public User(String userID, String userPIN, double accountBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.accountBalance = accountBalance;
    }

    // Getters and setters
    public String getUserID() {
        return userID;
    }

    public String getUserPIN() {
        return userPIN;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}

// ATM class to encapsulate ATM functionalities
class ATM {
    private Scanner scanner;
    private User currentUser;

    public ATM() {
        this.scanner = new Scanner(System.in);
        //this.currentUser = user;
    }

    // Method to authenticate user
    public void authenticateUser(User user) {
      this.currentUser=user;
        System.out.println("Welcome to the ATM Interface Program");
        System.out.print("Enter your user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String userPIN = scanner.nextLine();

        // Dummy authentication logic - replace with database authentication
        if (userID.equals(currentUser.getUserID()) && userPIN.equals(currentUser.getUserPIN())) {
           
            System.out.println("Authentication successful.");
            showMenu();
        } else {
            System.out.println("Invalid user ID or PIN. Please try again.");
        }
    }

    // Method to display ATM menu
    private void showMenu() {
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");

            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Your account balance is: $" + currentUser.getAccountBalance());
                    break;
                case 2:
                    withdrawMoney(currentUser);
                    break;
                case 3:
                    depositMoney(currentUser);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to withdraw money
   public void withdrawMoney(User user) {
        System.out.print("Enter the amount to withdraw: $");
        double withdrawAmount = Double.parseDouble(scanner.nextLine());
        if (withdrawAmount <= currentUser.getAccountBalance()) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() - withdrawAmount);
            System.out.println("Amount withdrawn successfully.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    // Method to deposit money
    public  void depositMoney(User user) {
        System.out.print("Enter the amount to deposit: $");
        double depositAmount = Double.parseDouble(scanner.nextLine());
        currentUser.setAccountBalance(currentUser.getAccountBalance() + depositAmount);
        System.out.println("Amount deposited successfully.");
    }
}
public class App {
    public static void main(String[] args) throws Exception {
        User user=new User("4", "0410", 30000);
        ATM atm = new ATM();
       
        atm.authenticateUser(user);
    }
}
