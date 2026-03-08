// A Postcondition must be satisfied after a method is executed.
// Subclasses can strengthen the Postcondition but cannot weaken it.

// MENTAL MODEL TO UNDERSTAND THIS SHIT:
// SETTING PASSWORD RULES
// Parent provides a post condition ( lets say: after calling method -> BRAKE, the speed should naturally decrease )
// postCondition of LSP says, child can only strengthen the parent's post condition (IT CANNOT WEAKEN IT)
// so lets say child class(Electric vehicle's break condition) can only strengthen it (like speed will decrease but charge will increase as well)

class Car {
    protected int speed;

    public Car() {
        speed = 0;
    }

    public void accelerate() {
        System.out.println("Accelerating");
        speed += 20;
    }

    // PostCondition: Speed must reduce after brake
    public void brake() {
        System.out.println("Applying brakes");
        speed -= 20;
    }
}

// Subclass can strengthen postcondition - Does not violate LSP
class HybridCar extends Car {
    private int charge;

    public HybridCar() {
        super();
        charge = 0;
    }

    // STRENGTHENING THE POST CONDITION
    // PostCondition: Speed must reduce after brake
    // PostCondition: Charge must increase.
    @Override
    public void brake() {
        System.out.println("Applying brakes");
        speed -= 20;
        charge += 10;
    }
}

public class PostConditions {
    public static void main(String[] args) {
        Car hybridCar = new HybridCar();
        hybridCar.brake();  // Works fine: HybridCar reduces speed and also increases charge.
    
          //Client feels no difference in substituting Hybrid car in place of Car.
    }
}
