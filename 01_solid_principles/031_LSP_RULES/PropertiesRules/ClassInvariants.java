// Class Invariant of a parent class Object should not be broken by child class Object.
// Hence child class can either maintain or strengthen the invariant but never narrows it down.

// Invariant: Balance cannot be negative
class BankAccount {
    protected double balance;

    public BankAccount(double b) {
        // this tells the balance can never be negative.
        // according to LSP, this is a condition that should be true everywhere
        // basically: always check if balance is negative or not, whereever an operation takes place using balance
        if (b < 0) throw new IllegalArgumentException("Balance can't be negative");
        balance = b;
    }

    public void withdraw(double amount) {
        // this respects the rule (balance shouldn't be negative)
        if (balance - amount < 0) throw new RuntimeException("Insufficient funds");
        balance -= amount;
        System.out.println("Amount withdrawn. Remaining balance is " + balance);
    }
}

// Breaks invariant: Should not be allowed.
class CheatAccount extends BankAccount {
    public CheatAccount(double b) {
        super(b);
    }

    @Override
    public void withdraw(double amount) {
        // VIOLATION
        // Here, the rule that balance should never be negative is ignored
        // WHY? there are no checks ( if balance<0 )
        // therefore, this breaks LSP
        balance -= amount; // LSP break! Negative balance allowed
        System.out.println("Amount withdrawn. Remaining balance is " + balance);
    }
}

public class ClassInvariants {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(100);
        bankAccount.withdraw(100);
    }
}

