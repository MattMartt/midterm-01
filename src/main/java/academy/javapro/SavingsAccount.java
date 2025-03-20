package academy.javapro;

/**
 * SavingsAccount class extending the abstract Account class.
 * Features interest rate and minimum balance requirement.
 */
public class SavingsAccount extends Account {
    private final double interestRate;
    private static final double MIN_BALANCE = 100.0; // Minimum balance requirement

    /**
     * Constructor for creating a new savings account.
     *
     * @param accountNumber The account number
     * @param customerName The name of the account holder
     * @param initialBalance The initial balance
     * @param interestRate The annual interest rate (%)
     */
    public SavingsAccount(String accountNumber, String customerName, double initialBalance, double interestRate) {
        super(accountNumber, customerName, initialBalance); // Call to the parent constructor
        this.interestRate = interestRate;
    }

    /**
     * Calculates the interest amount based on the current balance.
     *
     * @return The calculated interest amount
     */
    //@Override
    public double calculateInterest() {
        return getBalance() * (interestRate / 100);
    }

    /**
     * Applies the calculated interest to the account balance.
     */
    public void applyInterest() {
        double interest = calculateInterest();
        setBalance(getBalance() + interest); // Update balance

        logTransaction("INTEREST", interest); // Log the interest transaction

        System.out.printf("Interest applied: $%.2f%n", interest);
    }

    /**
     * Overrides the withdraw method with savings account-specific rules.
     * Ensures minimum balance is maintained.
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }

        if (getBalance() - amount < MIN_BALANCE) {
            System.out.printf("Cannot withdraw $%.2f. Minimum balance of $%.2f must be maintained.%n", amount, MIN_BALANCE);
            return;
        }

        setBalance(getBalance() - amount); //Update balance

        logTransaction("WITHDRAWAL", amount); // Log the withdrawal transaction
        
        System.out.printf("Withdrew $%.2f from savings account%n", amount);
    }

    /**
     * Overrides the displayInfo method to include savings account-specific information.
     */
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call to the parent method
        System.out.println("Account Type: Savings Account");
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Minimum Balance Requirement: $" + MIN_BALANCE);
    }
}
