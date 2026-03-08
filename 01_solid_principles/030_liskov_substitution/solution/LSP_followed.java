import java.util.ArrayList;
import java.util.List;

// DepositOnlyAccount interface represents accounts that only support deposits.
// This is useful for accounts like Fixed Deposits where withdrawal is not allowed.
interface DepositOnlyAccount {

    // Method to deposit money into the account
    void deposit(double amount);
}

// WithdrawableAccount represents accounts that support both deposit and withdrawal.
// It extends DepositOnlyAccount since withdrawable accounts can also deposit money.
interface WithdrawableAccount extends DepositOnlyAccount {

    // Method to withdraw money from the account
    void withdraw(double amount);
}

// Savings account supports both deposits and withdrawals
class SavingAccount implements WithdrawableAccount {

    // Balance maintained in the account
    private double balance;

    public SavingAccount() {
        balance = 0;
    }

    // Deposit money into the savings account
    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " in Savings Account. New Balance: " + balance);
    }

    // Withdraw money if sufficient balance is available
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " from Savings Account. New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds in Savings Account!");
        }
    }
}

// CurrentAccount also supports deposits and withdrawals
class CurrentAccount implements WithdrawableAccount {

    private double balance;

    public CurrentAccount() {
        balance = 0;
    }

    // Deposit money into current account
    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " in Current Account. New Balance: " + balance);
    }

    // Withdraw money if balance is sufficient
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " from Current Account. New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds in Current Account!");
        }
    }
}

// FixedTermAccount represents accounts like Fixed Deposits
// These accounts allow deposits but do NOT allow withdrawals before maturity.
// Therefore it only implements DepositOnlyAccount.
class FixedTermAccount implements DepositOnlyAccount {

    private double balance;

    public FixedTermAccount() {
        balance = 0;
    }

    // Deposit money into the fixed-term account
    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " in Fixed Term Account. New Balance: " + balance);
    }
}

// BankClient represents a system component that processes transactions for accounts
class BankClient {

    // Accounts that allow withdrawals
    private List<WithdrawableAccount> withdrawableAccounts;

    // Accounts that only allow deposits
    private List<DepositOnlyAccount> depositOnlyAccounts;

    public BankClient(List<WithdrawableAccount> withdrawableAccounts,
                      List<DepositOnlyAccount> depositOnlyAccounts) {
        this.withdrawableAccounts = withdrawableAccounts;
        this.depositOnlyAccounts = depositOnlyAccounts;
    }

    // Process transactions for all accounts
    public void processTransactions() {

        // For withdrawable accounts, perform both deposit and withdrawal
        for (WithdrawableAccount acc : withdrawableAccounts) {
            acc.deposit(1000);
            acc.withdraw(500);
        }

        // For deposit-only accounts, only perform deposits
        for (DepositOnlyAccount acc : depositOnlyAccounts) {
            acc.deposit(5000);
        }
    }
}

// Main class to demonstrate how Liskov Substitution Principle is followed
public class LSP_followed {

    public static void main(String[] args) {

        // List of accounts that support withdrawals
        List<WithdrawableAccount> withdrawableAccounts = new ArrayList<>();

        withdrawableAccounts.add(new SavingAccount());
        withdrawableAccounts.add(new CurrentAccount());

        // List of accounts that only support deposits
        List<DepositOnlyAccount> depositOnlyAccounts = new ArrayList<>();

        depositOnlyAccounts.add(new FixedTermAccount());

        // Create bank client with both types of accounts
        BankClient client = new BankClient(withdrawableAccounts, depositOnlyAccounts);

        // Process transactions safely
        client.processTransactions();
    }
}