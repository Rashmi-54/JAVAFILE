import java.util.Scanner;

    // Class to represent the user's bank account
    class BankAccount {
        private double balance;
    
        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }
    
        public String deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                return "\n₹" + amount + " deposited successfully.";
            } else {
                return "\nInvalid deposit amount. Please enter a positive value.";
            }
        }
    
        public String withdraw(double amount) {
            if (amount > 0) {
                if (amount <= balance) {
                    balance -= amount;
                    return "\n₹" + amount + " withdrawn successfully.";
                } else {
                    return "\nInsufficient balance.";
                }
            } else {
                return "\nInvalid withdrawal amount. Please enter a positive value.";
            }
        }
    
        public String checkBalance() {
            return "\nYour current balance is ₹" + balance;
        }
    }
    
    // Class to represent the ATM machine
    class ATM {
        private BankAccount bankAccount;
    
        public ATM(BankAccount bankAccount) {
            this.bankAccount = bankAccount;
        }
    
        public void userInterface() {
            Scanner scanner = new Scanner(System.in);
    
            System.out.println("==================================");
            System.out.println("        WELCOME TO THE ATM        ");
            System.out.println("==================================");
    
            while (true) {
                System.out.println("\n----------------------------------");
                System.out.println("        ATM Main Menu");
                System.out.println("----------------------------------");
                System.out.println("1. Withdraw Money");
                System.out.println("2. Deposit Money");
                System.out.println("3. Check Balance");
                System.out.println("4. Exit");
                System.out.println("----------------------------------");
    
                System.out.print("Enter your choice (1-4): ");
                int choice = scanner.nextInt();
    
                switch (choice) {
                    case 1:
                        System.out.print("\nEnter the amount to withdraw: ₹");
                        double withdrawAmount = scanner.nextDouble();
                        System.out.println(bankAccount.withdraw(withdrawAmount));
                        break;
    
                    case 2:
                        System.out.print("\nEnter the amount to deposit: ₹");
                        double depositAmount = scanner.nextDouble();
                        System.out.println(bankAccount.deposit(depositAmount));
                        break;
    
                    case 3:
                        System.out.println(bankAccount.checkBalance());
                        break;
    
                    case 4:
                        System.out.println("\nThank you for using the ATM. Goodbye!");
                        scanner.close();
                        return;
    
                    default:
                        System.out.println("\nInvalid choice. Please try again.");
                }
            }
        }
    }
    
    // Main class to run the ATM interface
    public class ATMInterface {
        public static void main(String[] args) {
            // Create a bank account instance with an initial balance
            BankAccount userAccount = new BankAccount(5000);
    
            // Create an ATM instance and connect it with the user's bank account
            ATM atmMachine = new ATM(userAccount);
    
            // Run the ATM user interface
            atmMachine.userInterface();
        }
    }
    

