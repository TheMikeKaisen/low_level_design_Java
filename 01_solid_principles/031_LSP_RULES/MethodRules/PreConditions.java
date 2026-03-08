
// A Precondition must be satisfied before a method can be executed.
// Subclasses can weaken the precondition but cannot strengthen it.

// MENTAL MODEL TO UNDERSTAND THIS SHIT:
// SETTING PASSWORD RULES
// parent setPassword method says -> password must be at least 8 characters long
// according to pre-conditions in LSP, child class cannot strengthen this rule, but can only weaken it
// i.e, child cannot override setPassword method and update the least characters to 10 (cuz, it will strengthen it)
// child should only weaken it ( make least characters to 5 )

class User {
    // Precondition: Password must be at least 8 characters long
    public void setPassword(String password) {
        // this is the preCondition
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long!");
        }
        System.out.println("Password set successfully");
    }
}

class AdminUser extends User {
    // here, pre-condition is weakened
    // Precondition: Password must be at least 6 character
    @Override
    public void setPassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long!");
        }
        System.out.println("Password set successfully");
    }
}

public class PreConditions {
    public static void main(String[] args) {
        User user = new AdminUser();
        user.setPassword("Admin1");  // Works fine: AdminUser allows shorter passwords
    }
}

